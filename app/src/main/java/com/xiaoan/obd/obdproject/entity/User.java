package com.xiaoan.obd.obdproject.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：Administrator on 2016/12/7 13:48
 * company: xxxx
 * email：1032324589@qq.com
 */
@Entity
public class User {

    /**
     * img : null
     * userName : 13480562458
     * phone : null
     * userCode : 13480562458
     * isDefault : 0
     */
    @Id
    @Unique
    private Long id;

    private String img;
    private String userName;
    private String phone;
    private String userCode;
    private String isDefault;

    @Generated(hash = 335331117)
    public User(Long id, String img, String userName, String phone,
            String userCode, String isDefault) {
        this.id = id;
        this.img = img;
        this.userName = userName;
        this.phone = phone;
        this.userCode = userCode;
        this.isDefault = isDefault;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
