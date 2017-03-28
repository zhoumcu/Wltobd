package com.xiaoan.obd.obdproject.entity;

/**
 * author：Administrator on 2017/1/17 14:15
 * company: xxxx
 * email：1032324589@qq.com
 */

public class FaultCodeBean {
    private int id;
    private String contentZh;
    private String contentEn;
    private String code;
    private String scope;
    private String status;
    private String userRange;
    private String cdefine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCdefine() {
        return cdefine;
    }

    public void setCdefine(String cdefine) {
        this.cdefine = cdefine;
    }
    public String getContentZh() {
        return contentZh;
    }

    public void setContentZh(String contentZh) {
        this.contentZh = contentZh;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserRange() {
        return userRange;
    }

    public void setUserRange(String userRange) {
        this.userRange = userRange;
    }

    @Override
    public String toString() {
        return "FaultCodeBean{" +
                "id=" + id +
                ", contentZh='" + contentZh + '\'' +
                ", contentEn='" + contentEn + '\'' +
                ", code='" + code + '\'' +
                ", scope='" + scope + '\'' +
                ", status='" + status + '\'' +
                ", userRange='" + userRange + '\'' +
                '}';
    }
}
