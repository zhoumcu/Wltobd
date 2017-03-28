package com.xiaoan.obd.obdproject.entity.common;

/**
 * author：Administrator on 2016/12/12 16:55
 * company: xxxx
 * email：1032324589@qq.com
 */

public class ComResult {

    /**
     * status : 0
     * msg : msg
     */

    private StatusBean status;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public static class StatusBean {
        private int status;
        private String msg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
