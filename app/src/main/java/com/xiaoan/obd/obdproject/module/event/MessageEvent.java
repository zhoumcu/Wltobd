package com.xiaoan.obd.obdproject.module.event;

/**
 * author：Administrator on 2017/3/30 08:49
 * company: xxxx
 * email：1032324589@qq.com
 */
public class MessageEvent {

    private boolean isConnected;
    private String currentID;

    public boolean isDataChange() {
        return isDataChange;
    }

    public void setDataChange(boolean dataChange) {
        isDataChange = dataChange;
    }

    private boolean isDataChange;

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    private boolean isStop;

    public MessageEvent() {

    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean getIsConnected() {
        return this.isConnected;
    }

    public void setCurrentID(String currentID) {
        this.currentID = currentID;
    }

    public String getCurrentID() {
        return currentID;
    }
}
