package com.xiaoan.obd.obdproject.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * author：Administrator on 2016/12/12 10:47
 * company: xxxx
 * email：1032324589@qq.com
 */
@Entity
public class CarBean{
    @Id(autoincrement = true)
    @Unique
    private Long id;

    private String userCarID; //用户id
    private String brandLogo; //图标
    private String carSeriesName; //系列
    private String carTypeName; //品牌名称
    private String displacement; //排量
    private String releaseTime; //发布年份
    private String weight;  //整车质量
    private String derailleur;  //变速类型
    private String gears;   //档位
    private String ftyre; //前轮胎型号
    private String btyre; //后轮胎型号
    private String fuelType; //燃油型号
    private String oilPrice; //燃油价钱
    private String tank; //油箱容量
    private boolean blend; //混合动力
    private boolean startStop; //启动车型
    private String carAttr; //备用
    private String userCode; //用户账号
    private String boxCode; //盒子编号
    private boolean isCurrent; //当前
    private boolean isDefault; //是否默认
    private int count; //车系个数
    private String brandCname;

    public String getUserCarID() {
        return userCarID;
    }

    public void setUserCarID(String userCarID) {
        this.userCarID = userCarID;
    }

    public String getCarSeriesName() {
        return carSeriesName;
    }

    public void setCarSeriesName(String carSeriesName) {
        this.carSeriesName = carSeriesName;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTank() {
        return tank;
    }

    public void setTank(String tank) {
        this.tank = tank;
    }

    public boolean isBlend() {
        return blend;
    }

    public void setBlend(boolean blend) {
        this.blend = blend;
    }

    public boolean isStartStop() {
        return startStop;
    }

    public void setStartStop(boolean startStop) {
        this.startStop = startStop;
    }

    public String getCarAttr() {
        return carAttr;
    }

    public void setCarAttr(String carAttr) {
        this.carAttr = carAttr;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getBrandCname() {
        return brandCname;
    }

    public void setBrandCname(String brandCname) {
        this.brandCname = brandCname;
    }

    public String getBrandEname() {
        return brandEname;
    }

    public void setBrandEname(String brandEname) {
        this.brandEname = brandEname;
    }

    private String brandEname;
    private String initial;
    private long carTypeId;

    @Generated(hash = 805554528)
    public CarBean(Long id, String userCarID, String brandLogo,
            String carSeriesName, String carTypeName, String displacement,
            String releaseTime, String weight, String derailleur, String gears,
            String ftyre, String btyre, String fuelType, String oilPrice,
            String tank, boolean blend, boolean startStop, String carAttr,
            String userCode, String boxCode, boolean isCurrent, boolean isDefault,
            int count, String brandCname, String brandEname, String initial,
            long carTypeId) {
        this.id = id;
        this.userCarID = userCarID;
        this.brandLogo = brandLogo;
        this.carSeriesName = carSeriesName;
        this.carTypeName = carTypeName;
        this.displacement = displacement;
        this.releaseTime = releaseTime;
        this.weight = weight;
        this.derailleur = derailleur;
        this.gears = gears;
        this.ftyre = ftyre;
        this.btyre = btyre;
        this.fuelType = fuelType;
        this.oilPrice = oilPrice;
        this.tank = tank;
        this.blend = blend;
        this.startStop = startStop;
        this.carAttr = carAttr;
        this.userCode = userCode;
        this.boxCode = boxCode;
        this.isCurrent = isCurrent;
        this.isDefault = isDefault;
        this.count = count;
        this.brandCname = brandCname;
        this.brandEname = brandEname;
        this.initial = initial;
        this.carTypeId = carTypeId;
    }

    @Generated(hash = 618925768)
    public CarBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDerailleur() {
        return derailleur;
    }

    public void setDerailleur(String derailleur) {
        this.derailleur = derailleur;
    }

    public String getGears() {
        return gears;
    }

    public void setGears(String gears) {
        this.gears = gears;
    }

    public String getFtyre() {
        return ftyre;
    }

    public void setFtyre(String ftyre) {
        this.ftyre = ftyre;
    }

    public String getBtyre() {
        return btyre;
    }

    public void setBtyre(String btyre) {
        this.btyre = btyre;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }


    public String getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(String oilPrice) {
        this.oilPrice = oilPrice;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public boolean getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean getIsCurrent() {
        return this.isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }


    public void setCount(int count) {
        this.count  =count;
    }

    public int getCount() {
        return count;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }
    public String getInitial() {
        return initial;
    }

    public void setCarTypeId(long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public long getCarTypeId() {
        return carTypeId;
    }

    public boolean getStartStop() {
        return this.startStop;
    }

    public boolean getBlend() {
        return this.blend;
    }
    @Override
    public String toString() {
        return "CarBean{" +
                "id=" + id +
                ", userCarID=" + userCarID +
                ", brandLogo='" + brandLogo + '\'' +
                ", carSeriesName='" + carSeriesName + '\'' +
                ", carTypeName='" + carTypeName + '\'' +
                ", displacement='" + displacement + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", weight='" + weight + '\'' +
                ", derailleur='" + derailleur + '\'' +
                ", gears='" + gears + '\'' +
                ", ftyre='" + ftyre + '\'' +
                ", btyre='" + btyre + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", oilPrice='" + oilPrice + '\'' +
                ", tank='" + tank + '\'' +
                ", blend=" + blend +
                ", startStop=" + startStop +
                ", carAttr='" + carAttr + '\'' +
                ", userCode='" + userCode + '\'' +
                ", boxCode='" + boxCode + '\'' +
                ", isCurrent=" + isCurrent +
                ", isDefault=" + isDefault +
                ", count=" + count +
                ", brandCname='" + brandCname + '\'' +
                ", brandEname='" + brandEname + '\'' +
                ", initial='" + initial + '\'' +
                ", carTypeId=" + carTypeId +
                '}';
    }

    public String toJSON(){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("userCarID", getUserCarID());
            jsonObject.put("brandLogo", getBrandLogo());
            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }

}
