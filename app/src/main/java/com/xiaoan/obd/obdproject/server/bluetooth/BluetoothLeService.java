/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xiaoan.obd.obdproject.server.bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.xiaoan.obd.obdproject.module.event.MessageEvent;
import com.xiaoan.obd.obdproject.untils.Logger;
import com.xiaoan.obd.obdproject.untils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Service for managing connection and data communication with a GATT server hosted on a
 * given Bluetooth LE device.
 */
public class BluetoothLeService extends Service {
    private final static String TAG = BluetoothLeService.class.getSimpleName();
    public static final String ACTION_RETURN_OK = "send_msg_to_ble_return_ok";
    public static final String ACTION_SEND_OK = "send_msg_ok";
    public static final String ACTION_NAME_RSSI = "action_name_rssi";
    public static final String ACTION_DISCONNECT_SCAN = "aciton_disconnect_scan";
    private static final long TIME = 10000;
    public static String ACTION_CHANGE_RESULT = "action_change_result";

    private Thread serviceDiscoveryThread = null;
    private BluetoothManager mBluetoothManager = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private ArrayList<BluetoothGatt> connectionQueue = new ArrayList<BluetoothGatt>();
    private ArrayList<BluetoothGattCallback> callbacks = new ArrayList<BluetoothGattCallback>();

    public final static String ACTION_GATT_CONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";

    public final static UUID UUID_NOTIFY =
            //UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
            UUID.fromString("0000fff6-0000-1000-8000-00805f9b34fb");
    public final static UUID UUID_SERVICE =
            //UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
            UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    public BluetoothGattCharacteristic mNotifyCharacteristic;
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID =
            UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public final static UUID UUID_HEART_RATE_MEASUREMENT       =
            UUID.fromString(SampleGattAttributes.HEART_RATE_MEASUREMENT);
    public final static UUID UUID_CHAR =
            //UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
            UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
    public static String UUID_KEY_DATA = "0000ffe1-0000-1000-8000-00805f9b34fb";
    public static String UUID_CHAR1 = "0000fff1-0000-1000-8000-00805f9b34fb";
    public static String UUID_CHAR2 = "0000fff2-0000-1000-8000-00805f9b34fb";
    public static String UUID_CHAR3 = "0000fff3-0000-1000-8000-00805f9b34fb";
    public static String UUID_CHAR4 = "0000fff4-0000-1000-8000-00805f9b34fb";
    public static String UUID_CHAR5 = "0000fff5-0000-1000-8000-00805f9b34fb";
    public static String UUID_CHAR6 = "0000fff6-0000-1000-8000-00805f9b34fb";
    public static String UUID_CHAR7 = "0000fff7-0000-1000-8000-00805f9b34fb";
    public static String UUID_HERATRATE = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String UUID_TEMPERATURE = "00002a1c-0000-1000-8000-00805f9b34fb";
    public static String UUID_0XFFA6 = "0000ffa6-0000-1000-8000-00805f9b34fb";

    //    BluetoothGatt bluetoothGatt;
    private MyBluetoothDevice bluetoothDevice;
    static BluetoothGattCharacteristic gattCharacteristic_char6 = null;
    private ArrayList<MyBluetoothDevice> connectDevice = new ArrayList<>();
    private BluetoothGatt bluetoothGatt;
    private boolean mScanning;
    private Handler mHandler = new Handler();
    private BleResultCallBack callBack;

    public BluetoothLeService getService() {
        return service;
    }
    public BluetoothLeService service;

    public void findService(BluetoothGatt gatt) {
//        List<BluetoothGattService> gattServices = gatt.getServices();
//        displayGattServices(gattServices);
        BluetoothGattCharacteristic gattCharacteristic = gatt.getService(UUID_SERVICE).getCharacteristic(UUID_CHAR);
        // 把char1 保存起来以方便后面读写数据时使用
        for (MyBluetoothDevice bluetoothDevice : connectDevice) {
            if(bluetoothDevice.getDevice().getAddress().equals(gatt.getDevice().getAddress())) {
                bluetoothDevice.setGattCharacteristic_char6(gattCharacteristic);
            }
        }
        gattCharacteristic_char6 = gattCharacteristic;
//        Characteristic_cur = gattCharacteristic;
        if(setCharacteristicNotification(gatt,gattCharacteristic, true))
            broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED, gatt);
    }

    public boolean isConnected = false;
    // Implements callback methods for GATT events that the app cares about.  For example,
    // connection change and services discovered.
    private  BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            String intentAction;
            Logger.i(TAG, "oldStatus=" + status + " NewStates=" + newState);
            if(status == BluetoothGatt.GATT_SUCCESS) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    gatt.discoverServices();
                    intentAction = ACTION_GATT_CONNECTED;
                    broadcastUpdate(intentAction,gatt);
                    Logger.i(TAG, "Connected to GATT server.");
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED&&isConnected) {
                    intentAction = ACTION_GATT_DISCONNECTED;
                    listClose(gatt);
                    isConnected = false;
                    MessageEvent event = new MessageEvent();
                    event.setIsConnected(isConnected);
                    EventBus.getDefault().post(event);
                    Logger.i(TAG, "Disconnected from GATT server.");
                    broadcastUpdate(intentAction, gatt);
                }
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Logger.e(TAG, "onServicesDiscovered received: " + status);
//                findService(gatt);
                displayGattServices(gatt.getServices());
                isConnected = true;
                MessageEvent event = new MessageEvent();
                event.setIsConnected(isConnected);
                EventBus.getDefault().post(event);
            } else {
                if(gatt.getDevice().getUuids() == null)
                    Logger.e(TAG, "onServicesDiscovered received: " + status);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt,
                                         BluetoothGattCharacteristic characteristic,
                                         int status) {
            Logger.e(TAG, "onread " + status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic,gatt);
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt,
                                            BluetoothGattCharacteristic characteristic) {
            Log.w(TAG, "onchange ");
            broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic,gatt);
        }
        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            // TODO Auto-generated method stub
            Logger.e(TAG,"--onReadRemoteRssi--: " + status + ",   rssi:" + rssi
                    + "----------------------------------");
            // broadcastUpdate(ACTION_DATA_AVAILABLE, rssi);
            super.onReadRemoteRssi(gatt, rssi, status);
            Intent mIntent = new Intent(ACTION_NAME_RSSI);
            mIntent.putExtra("DEVICE_ADDRESS", gatt.getDevice());
            mIntent.putExtra("RSSI", rssi);
            //发送广播
            sendBroadcast(mIntent);

//            updateRssiBroadcast(rssi);
        }
    };

    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    private void broadcastUpdate(final String action, final BluetoothGatt gatt) {
        final Intent intent = new Intent(action);
        intent.putExtra("DEVICE_ADDRESS", gatt.getDevice());
        sendBroadcast(intent);
    }

    private void broadcastUpdate( String action, BluetoothGattCharacteristic characteristic,BluetoothGatt gatt) {
        Intent intent = new Intent(action);
        byte[] data = characteristic.getValue();
        Logger.i(TAG,"字节大小："+data.length+"内容："+ Arrays.toString(data));
        if (data != null && data.length > 0) {
            long current = System.currentTimeMillis();
            if(ObdData.execute(new String(data))){
                intent.putExtra(EXTRA_DATA,new String(data));
                intent.putExtra("DEVICE_ADDRESS", gatt.getDevice());
                Logger.i(TAG, "时间差："+String.valueOf(System.currentTimeMillis()-current));
                sendBroadcast(intent);
            }
        }
        if(callBack!=null)
            callBack.setBleResult(gatt.getDevice(),data);
    }

    public class LocalBinder extends Binder {
        public BluetoothLeService getService() {
            return BluetoothLeService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // After using a given device, you should make sure that BluetoothGatt.close() is called
        // such that resources are cleaned up properly.  In this particular example, close() is
        // invoked when the UI is disconnected from the Service.
        close();
        return super.onUnbind(intent);
    }

    private final IBinder mBinder = new LocalBinder();

    /**
     * Initializes a reference to the local Bluetooth adapter.
     *
     * @return Return true if the initialization is successful.
     */
    public boolean initialize() {
        // For API level 18 and above, get a reference to BluetoothAdapter through
        // BluetoothManager.
        service = this;
        if (mBluetoothManager == null) {
            mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (mBluetoothManager == null) {
                Logger.e(TAG, "Unable to initialize BluetoothManager.");
                return false;
            }
        }
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (mBluetoothAdapter == null) {
            Logger.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }
        iniBle();
        return true;
    }
    public void iniBle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.enable();
                }
                scanLeDevice(true);
            }
        }).start();
    }

    public void scanLeDevice(boolean enable) {
        if (enable) {
            Logger.i(TAG,"start scan ble!");
            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }
    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
                Logger.i(TAG,"后台接收广播："+device.getName()+":"+device.getAddress());
                if(TextUtils.isEmpty(device.getName())) return;
                if(device.getName().equals("OBDII iOS")||device.getName().equals("OBDII"))
                    connect(device.getAddress());
            };
    };
    /**
     * Connects to the GATT server hosted on the Bluetooth LE device.
     *
     * @param address The device address of the destination device.
     *
     * @return Return true if the connection is initiated successfully. The connection result
     *         is reported asynchronously through the
     *         {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     *         callback.
     */
    public boolean connect(String address) {
        if (mBluetoothAdapter == null || address == null) {
            Logger.i("BluetoothAdapter not initialized or unspecified address.");
//            Logger.i(TAG, "Device not found.  Unable to connect.");
            return false;
        }

        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        if (device == null) {
            Logger.i("Device not found.  Unable to connect.");
            return false;
        }
        bluetoothGatt = device.connectGatt(this, false, mGattCallback);
//        bluetoothGatt.connect();
        Logger.i("Trying to create a new connection.");
        return true;
    }
    public boolean connect(String address, MyBluetoothDevice blueDevice) {
        if (mBluetoothAdapter == null || address == null) {
            Logger.i( "BluetoothAdapter not initialized or unspecified address.");
//            Logger.i(TAG, "Device not found.  Unable to connect.");
            return false;
        }
        this.bluetoothDevice = blueDevice;
        Logger.i("the object is:"+bluetoothDevice.getDevice().getAddress());
        if(checkGatt(blueDevice)&&blueDevice!=null){
            connectDevice.add(blueDevice);
            Logger.i("connect device num:"+connectDevice.size());
        }

        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        if (device == null) {
            Logger.i( "Device not found.  Unable to connect.");
            return false;
        }
        BluetoothGatt bluetoothGatt = device.connectGatt(this, false, mGattCallback);
//        bluetoothGatt.connect();
        Logger.i("Trying to create a new connection and connection sum:"+connectionQueue.size());
        if(checkGatt(bluetoothGatt)&&bluetoothGatt!=null){
            blueDevice.setBluetoothGatt(bluetoothGatt);
            connectionQueue.add(bluetoothGatt);
            Logger.i("no bluetoothGatt."+connectionQueue.size());
        }
        return true;
    }
    /**
     * Connects to the GATT server hosted on the Bluetooth LE device.
     *
     * @param device The device address of the destination device.
     *
     * @param size
     * @return Return true if the connection is initiated successfully. The connection result
     *         is reported asynchronously through the
     *         {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     *         callback.
     */
    public boolean connect(BluetoothDevice device, int size) {
        if (mBluetoothAdapter == null || device == null) {
            Logger.i(TAG, "BluetoothAdapter not initialized or unspecified address.");
            Logger.i(TAG, "Device not found.  Unable to connect.");
            return false;
        }
        BluetoothGatt bluetoothGatt = device.connectGatt(this, false, mGattCallback);
//        bluetoothGatt.connect();
        if(checkGatt(bluetoothGatt)&&bluetoothGatt!=null){
            connectionQueue.add(bluetoothGatt);
            Logger.i("no bluetoothGatt."+connectionQueue.size());
        }
        Logger.i("Trying to create a new connection.");
        return true;
    }

    private boolean checkGattForDevice(BluetoothDevice device) {
        for (int j=0;j<connectionQueue.size();j++) {
            if(connectionQueue.get(j).getDevice().getAddress().equals(device.getAddress())) {
                Logger.i("gatt存在，直接连"+device.getAddress());
                connectionQueue.get(j).connect();
                return true;
            }
        }
        return false;
    }
    private boolean checkGatt(BluetoothGatt bluetoothGatt) {
        if (!connectionQueue.isEmpty()&&bluetoothGatt!=null) {
            for(BluetoothGatt btg:connectionQueue){
                if(btg.getDevice().getAddress().equals(bluetoothGatt.getDevice().getAddress())){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkGatt(MyBluetoothDevice bluetoothDevice) {
        if (!connectDevice.isEmpty()&&bluetoothDevice!=null) {
            for(MyBluetoothDevice btg:connectDevice){
                if(btg.getDevice().getAddress().equals(bluetoothDevice.getDevice().getAddress())){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Disconnects an existing connection or cancel a pending connection. The disconnection result
     * is reported asynchronously through the
     * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     * callback.
     */
    public void disconnect() {
        if (mBluetoothAdapter == null ) {
            Logger.e(TAG, "BluetoothAdapter not initialized");
            return;
        }
//        mBluetoothGatt.disconnect();
        if(!MyBluetoothDevice.SINGLE_BLE) {
            for (final BluetoothGatt bluetoothGatt : connectionQueue) {
                //bluetoothGatt.disconnect();
                //bluetoothGatt.close();

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            bluetoothGatt.disconnect();
                            Thread.sleep(250);
                            bluetoothGatt.close();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        }else {
            if(bluetoothGatt ==null) return;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        bluetoothGatt.disconnect();
                        Thread.sleep(250);
                        bluetoothGatt.close();
                        Logger.e("bluetoothGatt close");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        service = null;
        if(!MyBluetoothDevice.SINGLE_BLE)
            connectionQueue.clear();
        Logger.e("service close!");
    }

    public void disconnect(final BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null) {
            Logger.e(TAG, "bluetoothGatt not initialized");
            return;
        }
//        mBluetoothGatt.disconnect();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(250);
                    bluetoothGatt.disconnect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * After using a given BLE device, the app must call this method to ensure resources are
     * released properly.
     */
    public void close() {
        if (connectionQueue.isEmpty()&&!MyBluetoothDevice.SINGLE_BLE) {
            return;
        }
        if(!MyBluetoothDevice.SINGLE_BLE) {
            for ( BluetoothGatt bluetoothGatt : connectionQueue) {
                bluetoothGatt.close();
            }
            connectionQueue.clear();
            connectDevice.clear();
        }else {
            if(bluetoothGatt!=null)
                bluetoothGatt.close();
        }
    }
    private synchronized void close(BluetoothGatt gatt) {
        if (gatt != null) {
            gatt.disconnect();
            gatt.close();
        }
    }
    private void listClose(BluetoothGatt gatt) {
        if (!connectionQueue.isEmpty()) {
            if (gatt != null) {
                for(final BluetoothGatt bluetoothGatt:connectionQueue){
                    if(bluetoothGatt.equals(gatt)){
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    bluetoothGatt.disconnect();
                                    Thread.sleep(250);
                                    //bluetoothGatt.close();
                                    connectionQueue.remove(bluetoothGatt);
                                    connectDevice.clear();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            }else{
                for ( BluetoothGatt bluetoothGatt : connectionQueue) {
                    bluetoothGatt.disconnect();
                }
                connectionQueue.clear();
                connectDevice.clear();
            }
        }else {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        bluetoothGatt.disconnect();
                        Thread.sleep(250);
                        bluetoothGatt.close();
                        //connectionQueue.remove(bluetoothGatt);
                        //connectDevice.clear();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public void removeConnectionQueue(BluetoothGatt gatt) {
        connectionQueue.remove(gatt);
//        connectDevice.remove(gatt.getDevice());
    }
    /**
     * Request a read on a given {@code BluetoothGattCharacteristic}. The read result is reported
     * asynchronously through the {@code BluetoothGattCallback#onCharacteristicRead(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, int)}
     * callback.
     *
     * @param characteristic The characteristic to read from.
     */
    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || connectionQueue.isEmpty() ) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        for(BluetoothGatt bluetoothGatt:connectionQueue){
            Log.e(TAG,"connectionQueue");
            bluetoothGatt.readCharacteristic(characteristic);
        }
    }
    public void writeCharacteristic(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic characteristic) {
        bluetoothGatt.writeCharacteristic(characteristic);
    }

    /**
     * Enables or disables notification on a give characteristic.
     *  @param characteristic
     *            Characteristic to act on.
     * @param enabled
     */
    public boolean setCharacteristicNotification(
            BluetoothGatt gatt,BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBluetoothAdapter == null || (connectionQueue.isEmpty()&& !MyBluetoothDevice.SINGLE_BLE)) {
            Logger.e( TAG,"BluetoothAdapter not initialized");
            return false;
        }
        Logger.e( TAG,"Enable Notification");
        gatt.setCharacteristicNotification(characteristic, true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
        if(descriptor!=null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            gatt.writeDescriptor(descriptor);
            Logger.e( TAG,"descriptor is not null!");
        }
        return true;
    }
    public void setCharacteristicNotification(
            BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBluetoothAdapter == null || (connectionQueue.isEmpty()&& !MyBluetoothDevice.SINGLE_BLE)) {
            Logger.e( TAG,"BluetoothAdapter not initialized");
            return;
        }
        if(!MyBluetoothDevice.SINGLE_BLE) {
            for(BluetoothGatt bluetoothGatt:connectionQueue) {
                Logger.e( TAG,"Enable Notification");
                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                BluetoothGattDescriptor descriptor = characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                bluetoothGatt.writeDescriptor(descriptor);
            }
        }else {
            Logger.e( TAG,"Enable Notification");
            bluetoothGatt.setCharacteristicNotification(characteristic, true);
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            bluetoothGatt.writeDescriptor(descriptor);
        }
    }
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null)
            return;
        BluetoothGattCharacteristic Characteristic_cur = null;

        for (BluetoothGattService gattService : gattServices) {
            // -----Service的字段信息----//
            int type = gattService.getType();
            Log.e(TAG, "-->service type:" + Utils.getServiceType(type));
            Log.e(TAG, "-->includedServices size:"
                    + gattService.getIncludedServices().size());
            Log.e(TAG, "-->service uuid:" + gattService.getUuid());
            // -----Characteristics的字段信息----//
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService
                    .getCharacteristics();
            for (final BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                Log.e(TAG, "---->char uuid:" + gattCharacteristic.getUuid());
                int permission = gattCharacteristic.getPermissions();
                Log.e(TAG, "---->char permission:"
                        + Utils.getCharPermission(permission));
                int property = gattCharacteristic.getProperties();
                Log.e(TAG, "---->char property:"
                                + Utils.getCharPropertie(property));

                byte[] data = gattCharacteristic.getValue();
                if (data != null && data.length > 0) {
                    /*Log.e(TAG, "---->char value:" + new String(data));*/
                }

                if (gattCharacteristic.getUuid().toString().equals(UUID_CHAR2)) {
                    bluetoothDevice.gattCharacteristic_char5 = gattCharacteristic;
                    gattCharacteristic_char6 = gattCharacteristic;
                    Log.i(TAG, "+++++++++UUID_CHAR2");
                }

                if (gattCharacteristic.getUuid().toString().equals(UUID_CHAR1)) {
                    // 把char1 保存起来以方便后面读写数据时使用
                    setCharacteristicNotification(gattCharacteristic, true);
                    Log.i(TAG, "+++++++++UUID_CHAR6");
                }

                if (gattCharacteristic.getUuid().toString()
                        .equals(UUID_HERATRATE)) {
                    // 把heartrate 保存起来以方便后面读写数据时使用
                    bluetoothDevice.gattCharacteristic_heartrate = gattCharacteristic;
                    Characteristic_cur = gattCharacteristic;
                    // 接受Characteristic被写的�?�?收到蓝牙模块的数据后会触发mOnDataAvailable.onCharacteristicWrite()
                    setCharacteristicNotification(gattCharacteristic, true);
                    Log.i(TAG, "+++++++++UUID_HERATRATE");
                }

                if (gattCharacteristic.getUuid().toString()
                        .equals(UUID_KEY_DATA)) {
                    // 把heartrate 保存起来以方便后面读写数据时使用
                    bluetoothDevice.gattCharacteristic_keydata = gattCharacteristic;
                    Characteristic_cur = gattCharacteristic;
                    // 接受Characteristic被写的�?�?收到蓝牙模块的数据后会触发mOnDataAvailable.onCharacteristicWrite()
                    setCharacteristicNotification(gattCharacteristic, true);
                    Log.i(TAG, "+++++++++UUID_KEY_DATA");
                }

                if (gattCharacteristic.getUuid().toString()
                        .equals(UUID_TEMPERATURE)) {
                    // 把heartrate 保存起来�?以方便后面读写数据时使用
                    bluetoothDevice.gattCharacteristic_temperature = gattCharacteristic;
                    Characteristic_cur = gattCharacteristic;
                    // 接受Characteristic被写的�?�?收到蓝牙模块的数据后会触发mOnDataAvailable.onCharacteristicWrite()
                    setCharacteristicNotification(gattCharacteristic, true);
                    Log.i(TAG, "+++++++++UUID_TEMPERATURE");
                }


                if (gattCharacteristic.getUuid().toString()
                        .equals(UUID_0XFFA6)) {
                    // 把heartrate 保存起来以方便后面读写数据时使用
                    bluetoothDevice.gattCharacteristic_0xffa6 = gattCharacteristic;
                    Characteristic_cur = gattCharacteristic;
                    Log.i(TAG, "+++++++++UUID_0XFFA6");
                }

                // -----Descriptors的字段信�?----//
                List<BluetoothGattDescriptor> gattDescriptors = gattCharacteristic
                        .getDescriptors();
                for (BluetoothGattDescriptor gattDescriptor : gattDescriptors) {
                    Log.e(TAG, "-------->desc uuid:" + gattDescriptor.getUuid());
                    int descPermission = gattDescriptor.getPermissions();
                    Log.e(TAG, "-------->desc permission:"
                                    + Utils.getDescPermission(descPermission));

                    byte[] desData = gattDescriptor.getValue();
                    if (desData != null && desData.length > 0) {
                        Log.e(TAG, "-------->desc value:" + new String(desData));
                    }
                }

            }
        }//
    }

    public ArrayList<BluetoothGatt> getBluethGatt()
    {
        return this.connectionQueue;
    }
    public void removeBluethGatt(BluetoothGatt gatt) {
        gatt.disconnect();
        connectionQueue.remove(gatt);
    }

    public void writeChar6(String string) {
        // byte[] writeValue = new byte[1];
//        Log.i(TAG, "gattCharacteristic_char6 = " + gattCharacteristic_char6);
        if (gattCharacteristic_char6 != null&&bluetoothGatt!=null) {
            // writeValue[0] = writeValue_char1++;
            // Log.i(TAG, "gattCharacteristic_char6.setValue writeValue[0] =" +
            // writeValue[0]);
            // byte[] writebyte = new byte[4];
            Logger.e(TAG,"发送数据： "+string+"测试");
            boolean bRet = gattCharacteristic_char6.setValue(string.getBytes());
            writeCharacteristic(bluetoothGatt, gattCharacteristic_char6);
        }else {
            Logger.e(TAG,"getGattCharacteristic_char6 is null");
        }
    }
    public void writeChar6(String string,BleResultCallBack callBack) {
        this.callBack = callBack;
        // byte[] writeValue = new byte[1];
//        Log.i(TAG, "gattCharacteristic_char6 = " + gattCharacteristic_char6);
        if (gattCharacteristic_char6 != null&&bluetoothGatt!=null) {
            // writeValue[0] = writeValue_char1++;
            // Log.i(TAG, "gattCharacteristic_char6.setValue writeValue[0] =" +
            // writeValue[0]);
            // byte[] writebyte = new byte[4];
            Logger.e(TAG,"发送数据： "+string+"测试");
            boolean bRet = gattCharacteristic_char6.setValue(string.getBytes());
            writeCharacteristic(bluetoothGatt, gattCharacteristic_char6);
        }else {
            Logger.e(TAG,"getGattCharacteristic_char6 is null");
        }
    }
    public void writeChar6(byte[] data,BleResultCallBack callBack) {
        this.callBack = callBack;
        if (gattCharacteristic_char6 != null&&bluetoothGatt!=null) {
            boolean bRet = gattCharacteristic_char6.setValue(data);
            writeCharacteristic(bluetoothGatt, gattCharacteristic_char6);
        }else {
            Logger.e(TAG,"getGattCharacteristic_char6 is null");
        }
    }
    public interface BleResultCallBack{
        void setBleResult(BluetoothDevice device, byte[] s);
    }
}
