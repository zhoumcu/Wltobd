package com.xiaoan.obd.obdproject.module.tire;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.bijection.Presenter;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.server.bluetooth.ObdData;

/**
 * author：Administrator on 2017/1/10 08:54
 * company: xxxx
 * email：1032324589@qq.com
 */
public class TireHomePresenter extends Presenter<TireHomeActivity>{
    private final static String TAG = TireHomePresenter.class.getSimpleName();
//    public BluetoothLeService mBluetoothLeService;
    private String tt = "$OBD-TT," +
            "8274722015013," +
            "1457585698," +
            "25," +
            "586," +
            "180," +
            "3600," +
            "," +
            "600," +
            "88," +
            "1.2," +
            "6.5," +
            "2862," +
            "135," +
            "75.5," +
            "26.3," +
            "0.8," +
            "0.1," +
            "28," +
            "3," +
            "5," +
            "0," +
            "356," +
            "1," +
            "32.5|31.3|32|32," +
            "29.3|29.3|30|29," +
            "40|38|39|38," +
            "30|29|28|30," +
            "0000|0100|0001|0000\r\n";
    @Override
    protected void onCreate(@NonNull TireHomeActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        saveTestObdTTData();
//        mBluetoothLeService = APP.getInstances().getService();

    }

    private void saveTestObdTTData() {
        ObdData.execute(tt);
        ObdData.TT.setId(null);
        APP.getInstances().getDaoSession().insert(ObdData.TT);
    }
}
