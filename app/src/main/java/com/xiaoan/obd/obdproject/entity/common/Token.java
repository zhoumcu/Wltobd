package com.xiaoan.obd.obdproject.entity.common;

/**
 * author：Administrator on 2017/1/10 11:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public class Token {

    /**
     * token : c3a795361701acd2f298fae39bb8c998
     * validtime : 1484123056233
     */

    private String token;
    private long validtime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getValidtime() {
        return validtime;
    }

    public void setValidtime(long validtime) {
        this.validtime = validtime;
    }
}
