package com.xiaoan.obd.obdproject.entity;

/**
 * author：Administrator on 2016/12/8 11:52
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ItineraryBean {
    private String startTime;  //起始时间 12-8 9：55
    private String stopTime;   //结束时间 12-8 11：55
    private float averageEconomy; //平均油耗
    private float speed;    //速度
    private float travelTime; //驾驶时间
    private int distance;   //行驶路程
    private float  oilWear; //油耗
    private double money;   //耗费金额

    public ItineraryBean(String s, String s1) {
        this.startTime = s;
        this.stopTime = s1;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public float getAverageEconomy() {
        return averageEconomy;
    }

    public void setAverageEconomy(float averageEconomy) {
        this.averageEconomy = averageEconomy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(float travelTime) {
        this.travelTime = travelTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getOilWear() {
        return oilWear;
    }

    public void setOilWear(float oilWear) {
        this.oilWear = oilWear;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
