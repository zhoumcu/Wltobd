package com.xiaoan.obd.obdproject.module.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.Presenter;
import com.xiaoan.obd.obdproject.app.APP;


/**
 * author：Administrator on 2017/2/18 16:12
 * company: xxxx
 * email：1032324589@qq.com
 */

public abstract class ZhouBaseFragment<T extends Presenter> extends BeamFragment<T> {

    private ProgressDialog mProgressDialog;

    public void showDialog(){
        new AlertDialog.Builder(getContext()).setTitle("系统提示")//设置对话框标题
                .setMessage("OBD未连接，是否重新扫描！")//设置显示的内容
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub
                        APP.getInstances().mBluetoothLeService.scanLeDevice(true);
                        showProgress(false,"OBD搜索连接中...");
                    }
                }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                // TODO Auto-generated method stub
                Log.i("alertdialog"," 请保存数据！");
            }
        }).show();//在按键响应事件中显示此对话框
    }
    /**
     * 显示单选对话框
     *
     * @param title           标题
     * @param message         提示信息
     * @param strings         选项数组
     * @param checkedItem     默认选中
     * @param onClickListener 点击事件的监听
     */
    public void showRadioButtonDialog(String title, String message, String[] strings, int checkedItem, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        builder.setSingleChoiceItems(strings, checkedItem, onClickListener);
        builder.create();
        builder.show();
    }
    public void showProgress(boolean flag, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.show();
    }
    public void hideProgress() {
        if (mProgressDialog == null)
            return;
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
