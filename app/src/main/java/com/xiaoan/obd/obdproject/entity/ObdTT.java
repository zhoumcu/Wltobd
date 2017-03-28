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
public class ObdTT {
    @Id(autoincrement = true)
    @Unique
    private Long id;

    private String conten;//原数据
    private String obdType;//数据头
    private int sncode;//sn码
    private int strokeId;//行程id
    private int dataBytes;//数据字节数
    private int occTime;//当前时间戳
    private int hotCarTime;//本次热车时长
    private int travelTime;// 本次行驶时长
    private int idleSpeedTime;//本次怠速时长
    private double travelMileage;//本次行驶里程
    private double idleFuel;//本次怠速耗油
    private double drivingFuel;//本次行驶耗油
    private double averageFuel;//本次平均耗油
    private int maxRpm;//本次最高转速
    private int maxSpeed;//本次最高车速
    private int highestTemperature;//本次最高水温
    private int lowestTemperature;//本次最低水温
    private double maxAcceleration;//本次最大加速度
    private double minAcceleration;//本次最小加速度
    private double solarTermDoor;//本次最大节气门开度
    private int accelerationTimes;//本次急加速次数
    private int decelerationTimes;//本次急减速次数
    private int faultCodeNum;//本次故障码数量
    private int speedingTime;//本次超速行驶时长
    private int oneMinutesIdleTimes;//本次大于1分钟怠速次数
    private String maxTirePressure;//本次最高轮胎胎压
    private String minTirePressure;//本次最低轮胎胎压
    private String maxTireTemperature;//本次最高轮胎温度
    private String minTireTemperature;//本次最低轮胎温度
    private String tireState;//本次轮胎状态

    @Generated(hash = 113517895)
    public ObdTT(Long id, String conten, String obdType, int sncode, int strokeId,
            int dataBytes, int occTime, int hotCarTime, int travelTime,
            int idleSpeedTime, double travelMileage, double idleFuel,
            double drivingFuel, double averageFuel, int maxRpm, int maxSpeed,
            int highestTemperature, int lowestTemperature, double maxAcceleration,
            double minAcceleration, double solarTermDoor, int accelerationTimes,
            int decelerationTimes, int faultCodeNum, int speedingTime,
            int oneMinutesIdleTimes, String maxTirePressure,
            String minTirePressure, String maxTireTemperature,
            String minTireTemperature, String tireState) {
        this.id = id;
        this.conten = conten;
        this.obdType = obdType;
        this.sncode = sncode;
        this.strokeId = strokeId;
        this.dataBytes = dataBytes;
        this.occTime = occTime;
        this.hotCarTime = hotCarTime;
        this.travelTime = travelTime;
        this.idleSpeedTime = idleSpeedTime;
        this.travelMileage = travelMileage;
        this.idleFuel = idleFuel;
        this.drivingFuel = drivingFuel;
        this.averageFuel = averageFuel;
        this.maxRpm = maxRpm;
        this.maxSpeed = maxSpeed;
        this.highestTemperature = highestTemperature;
        this.lowestTemperature = lowestTemperature;
        this.maxAcceleration = maxAcceleration;
        this.minAcceleration = minAcceleration;
        this.solarTermDoor = solarTermDoor;
        this.accelerationTimes = accelerationTimes;
        this.decelerationTimes = decelerationTimes;
        this.faultCodeNum = faultCodeNum;
        this.speedingTime = speedingTime;
        this.oneMinutesIdleTimes = oneMinutesIdleTimes;
        this.maxTirePressure = maxTirePressure;
        this.minTirePressure = minTirePressure;
        this.maxTireTemperature = maxTireTemperature;
        this.minTireTemperature = minTireTemperature;
        this.tireState = tireState;
    }

    @Generated(hash = 1393151004)
    public ObdTT() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdleSpeedTime() {
        return idleSpeedTime;
    }

    public void setIdleSpeedTime(int idleSpeedTime) {
        this.idleSpeedTime = idleSpeedTime;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
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

    public int getHotCarTime() {
        return hotCarTime;
    }

    public void setHotCarTime(int hotCarTime) {
        this.hotCarTime = hotCarTime;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public double getTravelMileage() {
        return travelMileage;
    }

    public void setTravelMileage(double travelMileage) {
        this.travelMileage = travelMileage;
    }

    public double getIdleFuel() {
        return idleFuel;
    }

    public void setIdleFuel(double idleFuel) {
        this.idleFuel = idleFuel;
    }

    public double getDrivingFuel() {
        return drivingFuel;
    }

    public void setDrivingFuel(double drivingFuel) {
        this.drivingFuel = drivingFuel;
    }

    public double getAverageFuel() {
        return averageFuel;
    }

    public void setAverageFuel(double averageFuel) {
        this.averageFuel = averageFuel;
    }

    public int getMaxRpm() {
        return maxRpm;
    }

    public void setMaxRpm(int maxRpm) {
        this.maxRpm = maxRpm;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getHighestTemperature() {
        return highestTemperature;
    }

    public void setHighestTemperature(int highestTemperature) {
        this.highestTemperature = highestTemperature;
    }

    public int getLowestTemperature() {
        return lowestTemperature;
    }

    public void setLowestTemperature(int lowestTemperature) {
        this.lowestTemperature = lowestTemperature;
    }

    public double getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(double maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public double getMinAcceleration() {
        return minAcceleration;
    }

    public void setMinAcceleration(double minAcceleration) {
        this.minAcceleration = minAcceleration;
    }

    public double getSolarTermDoor() {
        return solarTermDoor;
    }

    public void setSolarTermDoor(double solarTermDoor) {
        this.solarTermDoor = solarTermDoor;
    }

    public int getAccelerationTimes() {
        return accelerationTimes;
    }

    public void setAccelerationTimes(int accelerationTimes) {
        this.accelerationTimes = accelerationTimes;
    }

    public int getDecelerationTimes() {
        return decelerationTimes;
    }

    public void setDecelerationTimes(int decelerationTimes) {
        this.decelerationTimes = decelerationTimes;
    }

    public int getFaultCodeNum() {
        return faultCodeNum;
    }

    public void setFaultCodeNum(int faultCodeNum) {
        this.faultCodeNum = faultCodeNum;
    }

    public int getSpeedingTime() {
        return speedingTime;
    }

    public void setSpeedingTime(int speedingTime) {
        this.speedingTime = speedingTime;
    }

    public int getOneMinutesIdleTimes() {
        return oneMinutesIdleTimes;
    }

    public void setOneMinutesIdleTimes(int oneMinutesIdleTimes) {
        this.oneMinutesIdleTimes = oneMinutesIdleTimes;
    }

    public String getMaxTirePressure() {
        return maxTirePressure;
    }

    public void setMaxTirePressure(String maxTirePressure) {
        this.maxTirePressure = maxTirePressure;
    }

    public String getMinTirePressure() {
        return minTirePressure;
    }

    public void setMinTirePressure(String minTirePressure) {
        this.minTirePressure = minTirePressure;
    }

    public String getMaxTireTemperature() {
        return maxTireTemperature;
    }

    public void setMaxTireTemperature(String maxTireTemperature) {
        this.maxTireTemperature = maxTireTemperature;
    }

    public String getMinTireTemperature() {
        return minTireTemperature;
    }

    public void setMinTireTemperature(String minTireTemperature) {
        this.minTireTemperature = minTireTemperature;
    }

    public String getTireState() {
        return tireState;
    }

    public void setTireState(String tireState) {
        this.tireState = tireState;
    }
}
