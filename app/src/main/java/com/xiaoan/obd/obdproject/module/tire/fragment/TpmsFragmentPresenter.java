package com.xiaoan.obd.obdproject.module.tire.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.jude.beam.bijection.Presenter;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.ObdRT;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.server.bluetooth.ObdData;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.DisplayUtil;
import com.xiaoan.obd.obdproject.utils.Logger;
import com.xiaoan.obd.obdproject.widget.ItemLongClickedPopWindow;

/**
 * author：Administrator on 2017/1/10 08:56
 * company: xxxx
 * email：1032324589@qq.com
 */
public class TpmsFragmentPresenter extends Presenter<TpmsFragment>{

    private static final String TAG = TpmsFragmentPresenter.class.getSimpleName();
    private int downX, downY;
    private int i = 0;
    private void broadcastUpdate(String action, String data) {
        Intent intent = new Intent(action);
        if(ObdData.execute(data)){
            intent.putExtra(BluetoothLeService.EXTRA_DATA,new String(data));
            if(getView()!=null&&getView().getContext()!=null)
                getView().getContext().sendBroadcast(intent);
        }
    }
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0x123) {
                if(msg.obj instanceof ObdRT)
                    setData((ObdRT) msg.obj);
            } else if(msg.what==0x122) {
                broadcastUpdate(BluetoothLeService.ACTION_DATA_AVAILABLE,Constants.test[i]);
                i++;
                if(i>=Constants.test.length){
                    i=0;
                }
                Message msg1 = new Message();
                msg1.what = 0x122;
                if (handler!=null)
                handler.sendMessageDelayed(msg1,1000);
            }
        };
    };

    @Override
    protected void onCreate(@NonNull TpmsFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        getView().getActivity().registerReceiver(broadcastReceiver,new IntentFilter(BluetoothLeService.ACTION_DATA_AVAILABLE));
    }

    @Override
    protected void onCreateView(@NonNull TpmsFragment view) {
        super.onCreateView(view);
        init(view);
        handler.sendEmptyMessage(0x122);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getView().getActivity().unregisterReceiver(broadcastReceiver);
        handler = null;
    }

    private void init(TpmsFragment view) {

        GestureDetector mGestureDetector = new GestureDetector(getView().getActivity(), new GISGestureListener(getView().getContext()));

        ItemLongClickedPopWindow itemLongClickedPopWindow =
                new ItemLongClickedPopWindow(getView().getActivity(),
                        ItemLongClickedPopWindow.IMAGE_VIEW_POPUPWINDOW,
                        DisplayUtil.dip2px(getView().getActivity(),120),
                        DisplayUtil.dip2px(getView().getActivity(),90));

        view.tvLeftBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemLongClickedPopWindow.showAtLocation(view, Gravity.CENTER,downX,downY+10);
                return false;
            }
        });
        view.tvLeftFrom.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemLongClickedPopWindow.showAsDropDown(view,50,-200);
                return false;
            }
        });
        view.tvRightBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemLongClickedPopWindow.showAsDropDown(view,50,-200);
                return false;
            }
        });
        view.tvRightFrom.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemLongClickedPopWindow.showAsDropDown(view,50,-200);
                return false;
            }
        });
    }

    private class GISGestureListener extends GestureDetector.SimpleOnGestureListener {

        private Context mContext;

        GISGestureListener(Context context) {
            mContext = context;
        }

        /**
         * 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Toast.makeText(mContext, "SHOW " +  e.getX()+"     "+e.getY(), Toast.LENGTH_SHORT).show();
            downX = (int) e.getX();
            downY = (int) e.getY();
            return false;

        }
    }

    private String RF = "";
    private String LB = "";
    private String LF = "";
    private String RB = "";

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logger.e(TAG,"前端接收数据："+intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            Logger.e(TAG,"处理数据："+ObdData.RT.toString());
            Message msg = new Message();
            msg.what=0x123;
            msg.obj = ObdData.RT;
            RF = ObdData.RF;
            LB = ObdData.LB;
            LF = ObdData.LF;
            RB = ObdData.RB;
            handler.sendMessage(msg);
        }
    };
    private void setData(ObdRT RT){
        if(RT == null||getView().getActivity()==null) return;
        int default1 = getView().getActivity().getResources().getColor(R.color.colorPrimaryDark);
        String leftFromVal =  String.format("%.1f",RT.getFltirePsi()/14.51);
        String rightFromVal =  String.format("%.1f",RT.getFrtirePsi()/14.51);
        String leftBackVal =  String.format("%.1f",RT.getBltirePsi()/14.51);
        String rightBackVal =  String.format("%.1f",RT.getBrtirePsi()/14.51);
        String leftFromTempVal =  String.valueOf(RT.getFltireTemp());
        String rightFromTempVal =  String.valueOf(RT.getFrtireTemp());
        String leftBackTempVal =  String.valueOf(RT.getBltireTemp());
        String rightBackTempVal =  String.valueOf(RT.getBrtireTemp());
        getView().tvLeftFrom.setPressText(leftFromVal,default1);
        getView().tvRightFrom.setPressText( rightFromVal,default1);
        getView().tvLeftBack.setPressText( leftBackVal,default1);
        getView().tvRightBack.setPressText( rightBackVal,default1);
        getView().tvLeftFrom.setTempText(leftFromTempVal,default1);
        getView().tvRightFrom.setTempText( rightFromTempVal,default1);
        getView().tvLeftBack.setTempText( leftBackTempVal,default1);
        getView().tvRightBack.setTempText( rightBackTempVal,default1);
        if(LF.contains(ObdData.tireH)){
            getView().tvLeftFrom.showPressHightView(leftFromVal);
            getView().tvLeftFrom.setNoteText(LF);
        }else  if(LF.contains(ObdData.tireL)){
            getView().tvLeftFrom.showPressLowView(leftFromVal);
            getView().tvLeftFrom.setNoteText(LF);
        }else  if(LF.contains(ObdData.tempH)){
            getView().tvLeftFrom.setNoteText(LF);
        }
        if(LB.contains(ObdData.tireH)){
            getView().tvLeftBack.showPressHightView(rightFromVal);
            getView().tvLeftBack.setNoteText(LB);
        }else  if(LB.contains(ObdData.tireL)){
            getView().tvLeftBack.showPressLowView(rightFromVal);
            getView().tvLeftBack.setNoteText(LB);
        }else  if(LB.contains(ObdData.tempH)){
            getView().tvLeftBack.setNoteText(LB);
        }
        if(RF.contains(ObdData.tireH)){
            getView().tvRightFrom.showPressHightView(leftBackVal);
            getView().tvRightFrom.setNoteText(RF);
        }else  if(RF.contains(ObdData.tireL)){
            getView().tvRightFrom.showPressLowView(leftBackVal);
            getView().tvRightFrom.setNoteText(RF);
        }else  if(RF.contains(ObdData.tempH)){
            getView().tvRightFrom.setNoteText(RF);
        }
        if(RB.contains(ObdData.tireH)){
            getView().tvRightBack.showPressLowView(rightBackVal);
            getView().tvRightBack.setNoteText(RB);
        }else  if(RB.contains(ObdData.tireL)){
            getView().tvRightBack.showPressLowView(rightBackVal);
            getView().tvRightBack.setNoteText(RB);
        }else  if(RB.contains(ObdData.tempH)){
            getView().tvRightBack.setNoteText(RB);
        }
    }
}
