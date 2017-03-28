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
import com.xiaoan.obd.obdproject.untils.DisplayUtil;
import com.xiaoan.obd.obdproject.untils.Logger;
import com.xiaoan.obd.obdproject.widget.ItemLongClickedPopWindow;

/**
 * author：Administrator on 2017/1/10 08:56
 * company: xxxx
 * email：1032324589@qq.com
 */
public class TpmsFragmentPresenter extends Presenter<TpmsFragment>{

    private static final String TAG = TpmsFragmentPresenter.class.getSimpleName();
    private int downX, downY;
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123) {
                if(msg.obj instanceof ObdRT)
                    setData((ObdRT) msg.obj);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getView().getActivity().unregisterReceiver(broadcastReceiver);
    }

    private void init(TpmsFragment view) {
        view.tvLeftFrom.setBackColor(getView().getActivity().getResources().getColor(R.color.white));
        view.tvLeftFrom.setPressText("1.8",getView().getActivity().getResources().getColor(R.color.white));
        view.tvLeftBack.setBackColor(getView().getActivity().getResources().getColor(R.color.white));
        view.tvLeftBack.setPressText("1.8",getView().getActivity().getResources().getColor(R.color.white));

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
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logger.e(TAG,"前端接收数据："+intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
//            ObdData.execute(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            Logger.e(TAG,"处理数据："+ObdData.RT.toString());
//            setData(ObdData.RT);
            Message msg = new Message();
            msg.what=0x123;
            msg.obj = ObdData.RT;
            handler.sendMessage(msg);
        }
    };
    private void setData(ObdRT RT){
        if(RT == null) return;
        int default1 = getView().getActivity().getResources().getColor(R.color.colorPrimaryDark);
        getView().tvLeftFrom.setPressText( String.format("%.1f",RT.getFltirePsi()/14.51),default1);
        getView().tvRightFrom.setPressText( String.format("%.1f",RT.getFrtirePsi()/14.51),default1);
        getView().tvLeftBack.setPressText( String.format("%.1f",RT.getBltirePsi()/14.51),default1);
        getView().tvRightBack.setPressText( String.format("%.1f",RT.getBrtirePsi()/14.51),default1);
//        getView().tvLeftFrom.setPressText(String.valueOf(RT.getFltirePsi()),default1);
    }
}
