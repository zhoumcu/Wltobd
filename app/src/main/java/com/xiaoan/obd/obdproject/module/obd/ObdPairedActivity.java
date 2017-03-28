package com.xiaoan.obd.obdproject.module.obd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：Administrator on 2017/1/19 10:47
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ObdPairedActivity extends BeamBaseActivity {

    private BluetoothLeService mBluetoothLeService;
    private int index = 0;
    private List<byte[]> bytes;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_paired_obd);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mBluetoothLeService = APP.getInstances().mBluetoothLeService;
        registerReceiver(broadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_DATA_AVAILABLE));
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra(BluetoothLeService.EXTRA_DATA);
            //refreshLogView("接收数据：" + Arrays.toString(data.getBytes()) + "\n");
            if(data.contains("$8")){
                Toast.makeText(ObdPairedActivity.this,data.toString(),Toast.LENGTH_SHORT).show();
            }
//            Logger.e("ObdConfigActivity", "下发成功..." + Arrays.toString(data.getBytes()) + "--" + index + "%");
//            Logger.e("ObdConfigActivity","配对情况：" +Arrays.toString(data.getBytes()) + "\n");
        }
    };

//    void refreshLogView(String msg) {
//        debugLog.append(msg);
//        int offset = debugLog.getLineCount() * debugLog.getLineHeight();
//        if (offset > debugLog.getHeight()) {
//            debugLog.scrollTo(0, offset - debugLog.getHeight());
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                // 处理返回逻辑
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    /**
     * 将byte数组转换为int数据
     *
     * @param b 字节数组
     * @return 生成的int数据
     */
    public static int bytesToInt(byte[] b) {
        String s = new String(b);
        return Integer.parseInt(s);
    }

    @OnClick({R.id.btn_leftF, R.id.btn_rightF, R.id.btn_leftB, R.id.btn_rightB, R.id.btn_cancle, R.id.btn_answer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_leftF:
                mBluetoothLeService.writeChar6("AT800"+"\r"+"\n");
                break;
            case R.id.btn_rightF:
                mBluetoothLeService.writeChar6("AT801"+"\r"+"\n");
                break;
            case R.id.btn_leftB:
                mBluetoothLeService.writeChar6("AT802"+"\r"+"\n");
                break;
            case R.id.btn_rightB:
                mBluetoothLeService.writeChar6("AT803"+"\r"+"\n");
                break;
            case R.id.btn_cancle:
                mBluetoothLeService.writeChar6("AT804"+"\r"+"\n");
                break;
            case R.id.btn_answer:
                mBluetoothLeService.writeChar6("AT805"+"\r"+"\n");
                break;
        }
    }
}
