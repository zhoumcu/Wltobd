package com.xiaoan.obd.obdproject.entity;

/**
 * author：Administrator on 2017/1/10 10:02
 * company: xxxx
 * email：1032324589@qq.com
 *
 *     Vehicle real time data
 *
 */

public class VehicleDataFlow {
    private Long id;
    private String snCode;//SN码
    private String travelID;//行程ID
    private String commandCode;//命令符（01）
    private String dataStreamCount;//数据字节数
    private String dataStreamCode;//数据流编号
    private String currentDate;//当前时间戳
    private String batteryVoltage;//电瓶电压
    private String engineSpeed;//发动机转速
    private String trottleAngle;//节气门开度
    private String engineLoad;//发动机负荷
    private String waterTemperature;//冷却液温度（水温）
    private String runningSpeed;//行驶车速
    private String averageSpeed;//本次平均车速
    private String instantaneousFuelConsumption;//瞬时油耗
    private String averageFuelConsumption;//本次平均油耗
    private String theFuelCOnsumption;//本次耗油量
    private String theDrivingRange;//本次行驶里程
    private String timesOfRapidAcceleration;//本次急加速次数
    private String timesOfRapidDcceleration;//本次急减速次数
    private String theDrivingTime;//本次行驶时长
    private String theIdleTime;//本次怠速时长
    private String faultCodeNums;//当前故障码数量
    private String leftFromTireStatus;//左前轮胎状态位
    private String leftFromTirePressure;//左前轮胎压力
    private String leftFromTireTemperature;//左前轮胎温度
    private String rightFromTireStatus;//右前轮胎状态位
    private String rightFromTirePressure;//右前轮胎压力
    private String rightFromTireTemperature;//右前轮胎温度
    private String leftBackTireStatus;//左后轮胎状态位
    private String leftBackTirePressure;//左后轮胎压力
    private String leftBackTireTemperature;//左后轮胎温度
    private String rightBackTireStatus;//右后轮胎状态位
    private String rightBackTirePressure;//右后轮胎压力
    private String rightBackTireTemperature;//右后轮胎温度
}
