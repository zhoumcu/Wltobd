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
import android.widget.Button;
import android.widget.TextView;

import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.untils.FileUtils;
import com.xiaoan.obd.obdproject.untils.Logger;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/1/19 10:47
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ObdConfigActivity extends BeamBaseActivity {
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.debug_log)
    TextView debugLog;
    private BluetoothLeService mBluetoothLeService;
    private int index = 0;
    private List<byte[]> bytes;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_config);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mBluetoothLeService = APP.getInstances().mBluetoothLeService;
        if (APP.getInstances().mBluetoothLeService.isConnected)
            tvState.append("--已连接--");
        registerReceiver(broadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_DATA_AVAILABLE));
        bytes = FileUtils.readFileForBin(FileUtils.getInnerSDCardPath() + "/Download/v20160610.bin");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStop){
                    isStop = false;
                    return;
                }
                new Thread(runnable).start();
            }
        });
    }

    private boolean isStop = false;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (!isStop){
                    mBluetoothLeService.writeChar6("&&BOOT,00"+"\r"+"\n");
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra(BluetoothLeService.EXTRA_DATA);
            refreshLogView("接收数据："+Arrays.toString(data.getBytes())+"\n");
            Logger.e("ObdConfigActivity", "下发成功..." + Arrays.toString(data.getBytes()) + "--" + index + "%");
            if (data.getBytes()[0] == 0x15) {
                isStop = true;
                String by = FileUtils.toData(index, bytes);
                mBluetoothLeService.writeChar6(by);
                index++;
                refreshLogView(by+"\n");
            } else if (data.getBytes()[0] == 0x06) {
//                isStop = true;
                String by = FileUtils.toData(index, bytes);
                mBluetoothLeService.writeChar6(by);
                index++;
                refreshLogView(by+"\n");
            }
        }
    };
    void refreshLogView(String msg){
        debugLog.append(msg);
        int offset=debugLog.getLineCount()*debugLog.getLineHeight();
        if(offset>debugLog.getHeight()){
            debugLog.scrollTo(0,offset-debugLog.getHeight());
        }
     }
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
}
