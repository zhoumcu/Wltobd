package com.xiaoan.obd.obdproject.module.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;

import com.jude.beam.bijection.Presenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;


/**
 * author：Administrator on 2017/2/18 16:12
 * company: xxxx
 * email：1032324589@qq.com
 */

public abstract class ZhouBaseActivity<T extends Presenter> extends BeamBaseActivity<T>{
    private ViewStub importPanel;
    private ProgressBar loadPressBar;
    public abstract int onCreateView();
    public abstract void onDelayCreate(ViewStub viewStub);

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123) {
                loadPressBar.setVisibility(View.GONE);
                onDelayCreate(importPanel);
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onCreateView());
        importPanel  = ((ViewStub) findViewById(R.id.viewStub));
        loadPressBar  = ((ProgressBar) findViewById(R.id.load_progress));
        Message msg = new Message();
        msg.what=0x123;
        handler.sendMessageDelayed(msg,200);
    }

    public void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("系统提醒");
        normalDialog.setMessage("是否结束行程?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        onFinish();
                        finish();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    protected abstract void onFinish();
}
