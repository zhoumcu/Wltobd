package com.xiaoan.obd.obdproject.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.xiaoan.obd.obdproject.entity.ObdRT;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.server.bluetooth.ObdData;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.Logger;
import com.xiaoan.obd.obdproject.utils.SharedPreferences;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author：Administrator on 2017/1/21 13:40
 * company: xxxx
 * email：1032324589@qq.com
 */

public class UploadDataServer extends Service{
    private static final long TIME = 5000;
    private Handler mHandler = new Handler();
    private boolean isPosting = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private BroadcastReceiver broadcastRecevice = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(BluetoothLeService.ACTION_DATA_AVAILABLE)&&!isPosting){
                isPosting = true;
                mHandler.postDelayed(runnable, TIME);
            }
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
//        mHandler.postDelayed(runnable, TIME); //每隔1s执行
        registerReceiver(broadcastRecevice,new IntentFilter(BluetoothLeService.ACTION_DATA_AVAILABLE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastRecevice);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
//                List<ObdRT> obdRT = APP.getInstances().getDaoSession().getObdRTDao().loadAll();
//                for (ObdRT b: obdRT) {
//                    serviceAPI.uploadRT(SharedPreferences.getInstance().getString(Constants.TOKEN,""),new Gson().toJson(b))
//                            .compose(new SchedulerTransform<>()).unsafeSubscribe(getUploadRTSubscriber);
//                }
//                Logger.e(TAG,"数据库同步！");
                if(ObdData.RT!=null&&!"".equals(ObdData.RT.getConten())) {
                    ObdRT rt = ObdData.RT;
                    rt.setUserCode(SharedPreferences.getInstance().getUserInfo().getUserCode());
                    rt.setUserCarID(SharedPreferences.getInstance().getString(Constants.USER_CAR_ID, ""));
//                    serviceAPI.uploadRT(SharedPreferences.getInstance().getString(Constants.TOKEN,""),new Gson().toJson(ObdData.RT))
//                            .compose(new SchedulerTransform<>()).unsafeSubscribe(getUploadRTSubscriber);
                    rt.setToken(SharedPreferences.getInstance().getString(Constants.TOKEN, ""));
                    get("http://api.xiaoan360.com/api/obdstream/upload?token = "+SharedPreferences.getInstance().getString(Constants.TOKEN, "")+"&obdStream="+new Gson().toJson(ObdData.RT));
                    if (!TextUtils.isEmpty(ObdData.TT.getConten())) {
//                    serviceAPI.uploadTT(SharedPreferences.getInstance().getString(Constants.TOKEN,""),new Gson().toJson(ObdData.TT))
//                            .compose(new SchedulerTransform<>()).unsafeSubscribe(getUploadTTSubscriber);
                    }
                }
//                mHandler.postDelayed(this, TIME);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("exception...");
            }
        }
    };

    private void get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Logger.i("WY", "打印POST响应的数据：" + response.body().string());
                        isPosting = false;
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
