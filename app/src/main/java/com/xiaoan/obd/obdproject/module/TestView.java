package com.xiaoan.obd.obdproject.module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.server.bluetooth.ObdData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：Administrator on 2017/3/27 11:53
 * company: xxxx
 * email：1032324589@qq.com
 */

public class TestView extends AppCompatActivity {
    @BindView(R.id.debug_log)
    TextView debugLog;
    private BluetoothLeService mBluetoothLeService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_test_obd);
        ButterKnife.bind(this);
        mBluetoothLeService = APP.getInstances().mBluetoothLeService;
        registerReceiver(broadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_DATA_AVAILABLE));
    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra(BluetoothLeService.EXTRA_DATA);
            if(ObdData.RT !=null){
                refreshLogView("接收数据："+ "左前："+ObdData.RT.getFltirePsi()+ "右前："+ObdData.RT.getFrtirePsi()+ "左后："+ObdData.RT.getBltirePsi()+ "右后："+ObdData.RT.getBrtirePsi()+"\n");
            }
        }
    };
    void refreshLogView(String msg) {
        debugLog.append(msg);
        int offset = debugLog.getLineCount() * debugLog.getLineHeight();
        if (offset > debugLog.getHeight()) {
            debugLog.scrollTo(0, offset - debugLog.getHeight());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
    @OnClick(R.id.btn_start)
    public void onClick() {
        mBluetoothLeService.writeChar6("AT600\r\n");
    }
}
