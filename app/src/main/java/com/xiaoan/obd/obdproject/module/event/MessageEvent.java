package com.xiaoan.obd.obdproject.module.event;

/**
 * author：Administrator on 2017/3/30 08:49
 * company: xxxx
 * email：1032324589@qq.com
 */
public class MessageEvent {

    private boolean isConnected;

    public MessageEvent() {

    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean getIsConnected() {
        return this.isConnected;
    }
}
