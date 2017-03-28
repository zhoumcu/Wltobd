package com.xiaoan.obd.obdproject.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/1/11.
 *
 */
@Entity
public class ObdRT {

    @Id(autoincrement = true)
    @Unique
    private Long id;

    private String conten;//原数据
    private String obdType;//数据头
    private int sncode;//sn码
    private int strokeId;//行程id
    private int identifiers;//命令符
    private int dataBytes;//数据字节数
    private int occTime;//当前时间戳
    private int dataNum;//数据流编号
    private double bvatteryVol;//电瓶电压
    private int rpm;//转速
    private double throttle;//节气门开度
    private double engineLoad;//发动机负荷
    private int waterTemp;//水温
    private int drivingSpeed;//行驶速度
    private int avgSpeed;//平均速度
    private double instantFuel;//瞬时油耗
    private double avgFuel;//平均油耗
    private double drivingFuel;//本次油耗
    private double travelMileage;//本次行驶里程
    private int acceleration;///本次急加速次数
    private int deceleration;//本次急减速次数
    private int travelTimes;//本次行驶时长
    private int idlingTimes;//本次怠速时长
    private int faultCodeNum;//当前故障码数量
    private String fltireStatus;//左前轮胎状态位
    private double fltirePsi;//左前轮胎压力
    private double fltireTemp;//左前轮胎温度
    private String frtireStatus;//右前轮胎状态位
    private double frtirePsi;//右前轮胎压力
    private double frtireTemp;//右前轮胎温度
    private String bltireStatus;//左后轮胎状态位
    private double bltirePsi;//左后轮胎压力
    private double bltireTemp;////左后轮胎温度
    private String brtireStatus;//右后轮胎状态位
    private double brtirePsi;//右后轮胎压力
    private double brtireTemp;//右后轮胎温度
    private String gps;//定位数据
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCarID() {
        return userCarID;
    }

    public void setUserCarID(String userCarID) {
        this.userCarID = userCarID;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    private String userCarID;//车ID
    private String userCode;//用户账号

    @Generated(hash = 240094428)
    public ObdRT(Long id, String conten, String obdType, int sncode, int strokeId,
            int identifiers, int dataBytes, int occTime, int dataNum,
            double bvatteryVol, int rpm, double throttle, double engineLoad,
            int waterTemp, int drivingSpeed, int avgSpeed, double instantFuel,
            double avgFuel, double drivingFuel, double travelMileage,
            int acceleration, int deceleration, int travelTimes, int idlingTimes,
            int faultCodeNum, String fltireStatus, double fltirePsi,
            double fltireTemp, String frtireStatus, double frtirePsi,
            double frtireTemp, String bltireStatus, double bltirePsi,
            double bltireTemp, String brtireStatus, double brtirePsi,
            double brtireTemp, String gps, String token, String userCarID,
            String userCode) {
        this.id = id;
        this.conten = conten;
        this.obdType = obdType;
        this.sncode = sncode;
        this.strokeId = strokeId;
        this.identifiers = identifiers;
        this.dataBytes = dataBytes;
        this.occTime = occTime;
        this.dataNum = dataNum;
        this.bvatteryVol = bvatteryVol;
        this.rpm = rpm;
        this.throttle = throttle;
        this.engineLoad = engineLoad;
        this.waterTemp = waterTemp;
        this.drivingSpeed = drivingSpeed;
        this.avgSpeed = avgSpeed;
        this.instantFuel = instantFuel;
        this.avgFuel = avgFuel;
        this.drivingFuel = drivingFuel;
        this.travelMileage = travelMileage;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.travelTimes = travelTimes;
        this.idlingTimes = idlingTimes;
        this.faultCodeNum = faultCodeNum;
        this.fltireStatus = fltireStatus;
        this.fltirePsi = fltirePsi;
        this.fltireTemp = fltireTemp;
        this.frtireStatus = frtireStatus;
        this.frtirePsi = frtirePsi;
        this.frtireTemp = frtireTemp;
        this.bltireStatus = bltireStatus;
        this.bltirePsi = bltirePsi;
        this.bltireTemp = bltireTemp;
        this.brtireStatus = brtireStatus;
        this.brtirePsi = brtirePsi;
        this.brtireTemp = brtireTemp;
        this.gps = gps;
        this.token = token;
        this.userCarID = userCarID;
        this.userCode = userCode;
    }

    @Generated(hash = 1567476689)
    public ObdRT() {
    }


    public String getObdType() {
        return obdType;
    }

    public void setObdType(String obdType) {
        this.obdType = obdType;
    }

    public int getSncode() {
        return sncode;
    }

    public void setSncode(int sncode) {
        this.sncode = sncode;
    }

    public int getStrokeId() {
        return strokeId;
    }

    public void setStrokeId(int strokeId) {
        this.strokeId = strokeId;
    }

    public int getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(int identifiers) {
        this.identifiers = identifiers;
    }

    public int getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(int dataBytes) {
        this.dataBytes = dataBytes;
    }

    public int getOccTime() {
        return occTime;
    }

    public void setOccTime(int occTime) {
        this.occTime = occTime;
    }

    public int getDataNum() {
        return dataNum;
    }

    public void setDataNum(int dataNum) {
        this.dataNum = dataNum;
    }

    public double getBvatteryVol() {
        return bvatteryVol;
    }

    public void setBvatteryVol(double bvatteryVol) {
        this.bvatteryVol = bvatteryVol;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public double getThrottle() {
        return throttle;
    }

    public void setThrottle(double throttle) {
        this.throttle = throttle;
    }

    public double getEngineLoad() {
        return engineLoad;
    }

    public void setEngineLoad(double engineLoad) {
        this.engineLoad = engineLoad;
    }

    public int getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(int waterTemp) {
        this.waterTemp = waterTemp;
    }

    public int getDrivingSpeed() {
        return drivingSpeed;
    }

    public void setDrivingSpeed(int drivingSpeed) {
        this.drivingSpeed = drivingSpeed;
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getInstantFuel() {
        return instantFuel;
    }

    public void setInstantFuel(double instantFuel) {
        this.instantFuel = instantFuel;
    }

    public double getAvgFuel() {
        return avgFuel;
    }

    public void setAvgFuel(double avgFuel) {
        this.avgFuel = avgFuel;
    }

    public double getDrivingFuel() {
        return drivingFuel;
    }

    public void setDrivingFuel(double drivingFuel) {
        this.drivingFuel = drivingFuel;
    }

    public double getTravelMileage() {
        return travelMileage;
    }

    public void setTravelMileage(double travelMileage) {
        this.travelMileage = travelMileage;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(int deceleration) {
        this.deceleration = deceleration;
    }

    public int getTravelTimes() {
        return travelTimes;
    }

    public void setTravelTimes(int travelTimes) {
        this.travelTimes = travelTimes;
    }

    public int getIdlingTimes() {
        return idlingTimes;
    }

    public void setIdlingTimes(int idlingTimes) {
        this.idlingTimes = idlingTimes;
    }

    public int getFaultCodeNum() {
        return faultCodeNum;
    }

    public void setFaultCodeNum(int faultCodeNum) {
        this.faultCodeNum = faultCodeNum;
    }

    public String getFltireStatus() {
        return fltireStatus;
    }

    public void setFltireStatus(String fltireStatus) {
        this.fltireStatus = fltireStatus;
    }

    public double getFltirePsi() {
        return fltirePsi;
    }

    public void setFltirePsi(double fltirePsi) {
        this.fltirePsi = fltirePsi;
    }

    public double getFltireTemp() {
        return fltireTemp;
    }

    public void setFltireTemp(double fltireTemp) {
        this.fltireTemp = fltireTemp;
    }

    public String getFrtireStatus() {
        return frtireStatus;
    }

    public void setFrtireStatus(String frtireStatus) {
        this.frtireStatus = frtireStatus;
    }

    public double getFrtirePsi() {
        return frtirePsi;
    }

    public void setFrtirePsi(double frtirePsi) {
        this.frtirePsi = frtirePsi;
    }

    public double getFrtireTemp() {
        return frtireTemp;
    }

    public void setFrtireTemp(double frtireTemp) {
        this.frtireTemp = frtireTemp;
    }

    public String getBltireStatus() {
        return bltireStatus;
    }

    public void setBltireStatus(String bltireStatus) {
        this.bltireStatus = bltireStatus;
    }

    public double getBltirePsi() {
        return bltirePsi;
    }

    public void setBltirePsi(double bltirePsi) {
        this.bltirePsi = bltirePsi;
    }

    public double getBltireTemp() {
        return bltireTemp;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public void setBltireTemp(double bltireTemp) {
        this.bltireTemp = bltireTemp;
    }

    public String getBrtireStatus() {
        return brtireStatus;
    }

    public void setBrtireStatus(String brtireStatus) {
        this.brtireStatus = brtireStatus;
    }

    public double getBrtirePsi() {
        return brtirePsi;
    }

    public void setBrtirePsi(double brtirePsi) {
        this.brtirePsi = brtirePsi;
    }

    public double getBrtireTemp() {
        return brtireTemp;
    }

    public void setBrtireTemp(double brtireTemp) {
        this.brtireTemp = brtireTemp;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "ObdRT{" +
                "conten='" + conten + '\'' +
                ", obdType='" + obdType + '\'' +
                ", sncode=" + sncode +
                ", strokeId=" + strokeId +
                ", identifiers=" + identifiers +
                ", dataBytes=" + dataBytes +
                ", occTime=" + occTime +
                ", dataNum=" + dataNum +
                ", bvatteryVol=" + bvatteryVol +
                ", rpm=" + rpm +
                ", throttle=" + throttle +
                ", engineLoad=" + engineLoad +
                ", waterTemp=" + waterTemp +
                ", drivingSpeed=" + drivingSpeed +
                ", avgSpeed=" + avgSpeed +
                ", instantFuel=" + instantFuel +
                ", avgFuel=" + avgFuel +
                ", drivingFuel=" + drivingFuel +
                ", travelMileage=" + travelMileage +
                ", acceleration=" + acceleration +
                ", deceleration=" + deceleration +
                ", travelTimes=" + travelTimes +
                ", idlingTimes=" + idlingTimes +
                ", faultCodeNum=" + faultCodeNum +
                ", fltireStatus='" + fltireStatus + '\'' +
                ", fltirePsi=" + fltirePsi +
                ", fltireTemp=" + fltireTemp +
                ", frtireStatus='" + frtireStatus + '\'' +
                ", frtirePsi=" + frtirePsi +
                ", frtireTemp=" + frtireTemp +
                ", bltireStatus='" + bltireStatus + '\'' +
                ", bltirePsi=" + bltirePsi +
                ", bltireTemp=" + bltireTemp +
                ", brtireStatus='" + brtireStatus + '\'' +
                ", brtirePsi=" + brtirePsi +
                ", brtireTemp=" + brtireTemp +
                ", gps='" + gps + '\'' +
                '}';
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
