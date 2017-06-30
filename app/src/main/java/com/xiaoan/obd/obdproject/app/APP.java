package com.xiaoan.obd.obdproject.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;

import com.jude.beam.Beam;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.entity.DaoMaster;
import com.xiaoan.obd.obdproject.entity.DaoSession;
import com.xiaoan.obd.obdproject.externaldb.DbHelper;
import com.xiaoan.obd.obdproject.module.main.HomeActivity;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.utils.Logger;
import com.xiaoan.obd.obdproject.utils.TypefaceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Jude on 2015/8/20.
 */
public class APP extends Application implements Thread.UncaughtExceptionHandler{

    private static final String TAG = APP.class.getSimpleName();
    private static APP instances;
    private DaoSession mDaoSession;
    public DbHelper dbHelper;
    public BluetoothLeService mBluetoothLeService;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        TypefaceUtil.replaceSystemDefaultFont(this,"fonts/digital2.ttf");
        JUtils.initialize(this);
        JUtils.setDebug(true, "JoyLog");
        Beam.init(this);
        setDatabase();
        dbHelper = DbHelper.getInstance(this);
        bindService();
//        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    public static APP getInstances(){
        return instances;
    }
    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "note-db.db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public BluetoothLeService getService(){
        if(mBluetoothLeService ==null){
            bindService();
            return mBluetoothLeService;
        }
        //service 未连接
        if(!mBluetoothLeService.isConnected){
            mBluetoothLeService.iniBle();
        }
        return APP.getInstances().mBluetoothLeService;
    }
    public void bindService(){
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            try {
                Intent bindIntent = new Intent(this, BluetoothLeService.class);
                bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Code to manage Service lifecycle.
    public ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Logger.e(TAG, "Unable to initialize Bluetooth");
            }
            Logger.e(TAG, "mBluetoothLeService is okay");
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };
    /*
    * 获取正在运行的进程名称
    * @param context
    * @return
    */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    private void addActivity(Class<?> c){
        List<Class> list = new ArrayList<>();
        list.add(c);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("uncaughtException");
                Intent intent = new Intent(instances, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }).start();
    }
}
