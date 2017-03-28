package com.xiaoan.obd.obdproject.server.bluetooth;

import com.xiaoan.obd.obdproject.entity.ObdRT;
import com.xiaoan.obd.obdproject.entity.ObdTT;
import com.xiaoan.obd.obdproject.untils.Logger;
import com.xiaoan.obd.obdproject.untils.NumberUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理obd蓝牙数据
 * 主要处理类型分
 * TR TT
 * <p>
 * Created by Administrator on 2017/1/11.
 */
public class ObdData {

    /**
     *数据头的类型
     */
    public static String DATA_HEAD_RT = "$OBD-RT";

    public static String DATA_HEAD_TT = "$OBD-TT";
    //AT400	读取故障码
    public static String AT400 = "$AT400";
    //获取设备信息
    public static String AT500 = "$AT500";
    //获取阀值信息
    //$502=超速阀值,电瓶低压阀值,水温高温阀值,碰撞震动感应等级,急加速阀值,急减速阀值,高胎压阀值,低胎压阀值,高胎温阀值
    public static String AT502 = "$AT502";
    //里程信息
    public static String AT300 = "$AT300";
    //AT开头最后会被处理
    public static String AT = "$AT";

    /**
     * 保存AT返回的内容。key为AT指令头如  $AT400
     * 对象以数组返回
     */
    public static Map<String,String[]> MAP = new HashMap<String,String[]>();

    /**
     * 保存最新的数据
     */
    public static ObdRT RT = new ObdRT();
    /**
     * 保存最新的数据
     */
    public static ObdTT TT = new ObdTT();
    /**
     * 完整 原数据，
     */
    public static String content;
    private static StringBuffer temData = new StringBuffer();
    private static int time;

    //完整內容
    //$OBD-RT,2016080600001,88,01,50,06,0000000000,11.9,8292,92.54,0.00,195,237,3,2.09,2.14,28.54,23.05,1,0,20257,0,6,1000,00.0,00,1000,00.0,00,1000,00.0,00,1000,00.0,00,,baiduLocation-
    public static boolean execute(String conten) {
        doConten(conten);

        //检查数据完整性
        int start = temData.toString().indexOf("$");
        int end = temData.toString().indexOf("\r\n");
        time++;
        if (start == 0 && end > 0 && end > start) {
            content = temData.toString();
            Logger.e("test","/"+time+"/"+temData.substring(start, end));
            dataToObdData(temData.substring(start, end));
            time = 0;
            //清除临时数据
            //temData.delete(0, end);
            temData.setLength(0);
            return true;
        }
        //如果长度过大没得到解释就清空一下,
        if (temData.length() > 1000 || time > 10) {
            time = 0;
            temData.setLength(0);
        }
        return false;
    }

    private static synchronized void dataToObdData(String str) {
        if (str.indexOf(DATA_HEAD_RT) == 0) {
            toRTDate(str);
            doTire();//处理胎压数据
        } else if (str.indexOf(DATA_HEAD_TT) == 0)
            toTTDate(str);
        else if (str.indexOf(AT500) == 0)//获取设备信息
            doAt500(str);
        else if (str.indexOf(AT502) == 0)////获取阀值信息
            doAt502(str);
        else if (str.indexOf(AT300) == 0)//里程信息
            doAt300(str);
        else if (str.indexOf(AT400) == 0)//读取故障码
            doAt400(str);
        else if (str.indexOf(AT) == 0)
            doAt(str);
        else
            do$(str);
    }

    /**
     * 保存最新未被处理的数据
     * @param str
     */
    private static void do$(String str) {
        MAP.put("$",new String[]{str});
    }

    /**
     * 指令头为 key  等号后的 原数据为 object
     * 保存未被人工处理的AT数据
     * @param str
     */
    private static void doAt(String str) {
        if(str.indexOf("=") > 1){
            String[] s = str.split("=");
            MAP.put(s[0],new String[]{s[1]});
        }
    }

    /**
     * 保存为数组
     * @param str
     */
    private static void doAt400(String str) {
        //$400=故障码数量, 故障码详情（竖线分隔如 P1001|P1002）
        if(str.indexOf("=") > 1) {
            String[] s = str.split("=");
            MAP.put(s[0], s[1].indexOf("|") > 0 ? s[1].split("|") : new String[]{s[1]});
        }
    }

    private static void doAt300(String str) {
        //$300=本次里程,累计里程,总里程（km）
        if(str.indexOf("=") > 1) {
            String[] s = str.split("=");
            MAP.put(s[0],s[1].split(","));
        }
    }

    private static void doAt502(String str) {
        //$502=超速阀值,电瓶低压阀值,水温高温阀值,碰撞震动感应等级,急加速阀值,急减速阀值,高胎压阀值,低胎压阀值,高胎温阀值
        if(str.indexOf("=") > 1) {
            String[] s = str.split("=");
            MAP.put(s[0],s[1].split(","));
        }
    }

    private static void doAt500(String str) {
        //$500=车架号,汽车协议名称,SN码,硬件版本,软件版本
        if(str.indexOf("=") > 1) {
            String[] s = str.split("=");
            MAP.put(s[0],s[1].split(","));
        }
    }

    /**
     * 转成TT对象
     *
     * @param str
     */
    private static void toTTDate(String str) {
        TT.setConten(str);//private String conten;//原数据
        String[] arr = str.split(",");
        if (arr.length < 30) {
            return;
        }
        int i = 0;
        TT.setObdType(arr[i++]);//private String obdType;//数据头
        TT.setSncode(NumberUtil.toInteger(arr[i++]));//private int sncode;//sn码
        TT.setStrokeId(NumberUtil.toInteger(arr[i++]));//private int strokeId;//行程id
        TT.setDataBytes(NumberUtil.toInteger(arr[i++]));//private int dataBytes;//数据字节数
        TT.setOccTime(NumberUtil.toInteger(arr[i++]));//private int occTime;//当前时间戳
        TT.setHotCarTime(NumberUtil.toInteger(arr[i++]));//private int hotCarTime;//本次热车时长
        TT.setTravelTime(NumberUtil.toInteger(arr[i++]));//private int travelTime;// 本次行驶时长
        TT.setIdleSpeedTime(NumberUtil.toInteger(arr[i++]));//private int idleSpeedTime;//本次怠速时长
        TT.setTravelMileage(NumberUtil.toDouble(arr[i++]));//private double travelMileage;//本次行驶里程
        TT.setIdleFuel(NumberUtil.toDouble(arr[i++]));//private double idleFuel;//本次怠速耗油
        TT.setDrivingFuel(NumberUtil.toDouble(arr[i++]));//private double drivingFuel;//本次行驶耗油
        TT.setAverageFuel(NumberUtil.toDouble(arr[i++]));//private double averageFuel;//本次平均耗油
        TT.setMaxRpm(NumberUtil.toInteger(arr[i++]));//private int maxRpm;//本次最高转速
        TT.setMaxRpm(NumberUtil.toInteger(arr[i++]));//private int maxSpeed;//本次最高车速
        TT.setHighestTemperature(NumberUtil.toInteger(arr[i++]));//private int highestTemperature;//本次最高水温
        TT.setLowestTemperature(NumberUtil.toInteger(arr[i++]));//private int lowestTemperature;//本次最低水温
        TT.setMaxAcceleration(NumberUtil.toDouble(arr[i++]));//private double maxAcceleration;//本次最大加速度
        TT.setMinAcceleration(NumberUtil.toDouble(arr[i++]));//private double minAcceleration;//本次最小加速度
        TT.setSolarTermDoor(NumberUtil.toDouble(arr[i++]));//private double solarTermDoor;//本次最大节气门开度
        TT.setAccelerationTimes(NumberUtil.toInteger(arr[i++]));//private int accelerationTimes;//本次急加速次数
        TT.setDecelerationTimes(NumberUtil.toInteger(arr[i++]));//private int decelerationTimes;//本次急减速次数
        TT.setFaultCodeNum(NumberUtil.toInteger(arr[i++]));//private int faultCodeNum;//本次故障码数量
        TT.setSpeedingTime(NumberUtil.toInteger(arr[i++]));//private int speedingTime;//本次超速行驶时长
        TT.setOneMinutesIdleTimes(NumberUtil.toInteger(arr[i++]));//private int oneMinutesIdleTimes;//本次大于1分钟怠速次数
        TT.setMaxTirePressure(arr[i++]);//private String maxTirePressure;//本次最高轮胎胎压
        TT.setMinTirePressure(arr[i++]);//private String minTirePressure;//本次最低轮胎胎压
        TT.setMaxTireTemperature(arr[i++]);//private String maxTireTemperature;//本次最高轮胎温度
        TT.setMinTireTemperature(arr[i++]);//private String minTireTemperature;//本次最低轮胎温度
        TT.setTireState(arr[i++]);//private String tireState;//本次轮胎状态
    }

    /**
     * 转成RT对象
     *
     * @param str
     */
    private static void toRTDate(String str) {
        RT.setConten(str);//private String conten;//原数据
        String[] arr = str.split(",");
        if (arr.length < 32) {
            return;
        }
        int i = 0;
        RT.setObdType(arr[i++]);//private String obdType;//数据头
        RT.setSncode(NumberUtil.toInteger(arr[i++]));//private int sncode;//sn码
        RT.setStrokeId(NumberUtil.toInteger(arr[i++]));//private int strokeId;//行程id
        RT.setIdentifiers(NumberUtil.toInteger(arr[i++]));//private int identifiers;//命令符
        RT.setDataBytes(NumberUtil.toInteger(arr[i++]));//private int dataBytes;//数据字节数
        RT.setOccTime(NumberUtil.toInteger(arr[i++]));//private int occTime;//当前时间戳
        RT.setDataNum(NumberUtil.toInteger(arr[i++]));//private int dataNum;//数据流编号
        RT.setBvatteryVol(NumberUtil.toDouble(arr[i++]));//private double bvatteryVol;//电瓶电压
        RT.setRpm(NumberUtil.toInteger(arr[i++]));//private int rpm;//转速
        RT.setThrottle(NumberUtil.toInteger(arr[i++]));//private double throttle;//节气门开度
        RT.setEngineLoad(NumberUtil.toInteger(arr[i++]));//private double engineLoad;//发动机负荷
        RT.setWaterTemp(NumberUtil.toInteger(arr[i++]));//private int waterTemp;//水温
        RT.setDrivingSpeed(NumberUtil.toInteger(arr[i++]));//private int drivingSpeed;//行驶速度
        RT.setAvgSpeed(NumberUtil.toInteger(arr[i++]));//private int avgSpeed;//平均速度
        RT.setInstantFuel(NumberUtil.toDouble(arr[i++]));//private double instantFuel;//瞬时油耗
        RT.setAvgFuel(NumberUtil.toDouble(arr[i++]));//private double avgFuel;//平均油耗
        RT.setDrivingFuel(NumberUtil.toDouble(arr[i++]));//private double drivingFuel;//本次油耗
        RT.setTravelMileage(NumberUtil.toDouble(arr[i++]));//private double travelMileage;//本次行驶里程
        RT.setAcceleration(NumberUtil.toInteger(arr[i++]));//private int acceleration;///本次急加速次数
        RT.setDeceleration(NumberUtil.toInteger(arr[i++]));//private int deceleration;//本次急减速次数
        RT.setTravelTimes(NumberUtil.toInteger(arr[i++]));//private int travelTimes;//本次行驶时长
        RT.setIdlingTimes(NumberUtil.toInteger(arr[i++]));//private int idlingTimes;//本次怠速时长
        RT.setFaultCodeNum(NumberUtil.toInteger(arr[i++]));//private int faultCodeNum;//当前故障码数量
        RT.setFltireStatus(arr[i++]);//private String fltireStatus;//左前轮胎状态位
        RT.setFltirePsi(NumberUtil.toDouble(arr[i++]));//private double fltirePsi;//左前轮胎压力
        RT.setFltireTemp(NumberUtil.toDouble(arr[i++]));//private double fltireTemp;//左前轮胎温度
        RT.setFrtireStatus(arr[i++]);//private String frtireStatus;//右前轮胎状态位
        RT.setFrtirePsi(NumberUtil.toDouble(arr[i++]));//private double frtirePsi;//右前轮胎压力
        RT.setFrtireTemp(NumberUtil.toDouble(arr[i++]));//private double frtireTemp;//右前轮胎温度
        RT.setBltireStatus(arr[i++]);//private String bltireStatus;//左后轮胎状态位
        RT.setBltirePsi(NumberUtil.toDouble(arr[i++]));//private double bltirePsi;//左后轮胎压力
        RT.setBltireTemp(NumberUtil.toDouble(arr[i++]));//private double bltireTemp;////左后轮胎温度
        RT.setBrtireStatus(arr[i++]);//private String brtireStatus;//右后轮胎状态位
        RT.setBrtirePsi(NumberUtil.toDouble(arr[i++]));//private double brtirePsi;//右后轮胎压力
        RT.setBrtireTemp(NumberUtil.toDouble(arr[i++]));//private double brtireTemp;//右后轮胎温度//

        //RT.setGps(arr[i++]);//private String gps;/* 定位数据 */
    }

    //拼接内容
    private static void doConten(String conten) {
        temData.append(conten);
//        temData.insert(0,conten);
    }




    private static double H_PSI = 43D;
    private static double L_PSI = 25D;
    private static double H_TEM = 70D;
    /**
     * 设置参数值
     * 不传值按默认值走
     * @param hPsi 胎压高 默认值为：3.0bar = 43psi
     * @param lPsi 胎压低 默认值为：1.7bar = 25psi
     * @param hTem  胎温高 默认值为：>70C
     *
     *              = 14.503 * x bar = psi
     */
    public static void setParameter (Double hPsi,Double lPsi,Double hTem){
        H_PSI = hPsi;
        L_PSI = lPsi;
        H_TEM = hTem;
    }

    public static String LB = "";
    public static String RB = "";
    public static String LA = "";
    public static String RA = "";

    private static StringBuilder msg = new StringBuilder();

    private static void doTire(){

        //取出当前的数据
        //左前
        msg.delete(0,msg.length());
        doPsi(ObdData.RT.getFltirePsi());
        doStatus(ObdData.RT.getFltireStatus());
        doTem(ObdData.RT.getFltireTemp());
        //付值
        LB = msg.toString();
        //右前
        msg.delete(0,msg.length());
        doPsi(ObdData.RT.getFrtirePsi());
        doStatus(ObdData.RT.getFrtireStatus());
        doTem(ObdData.RT.getFrtireTemp());
        RB = msg.toString();
        //左后
        msg.delete(0,msg.length());
        doPsi(ObdData.RT.getBltirePsi());
        doStatus(ObdData.RT.getBltireStatus());
        doTem(ObdData.RT.getBltireTemp());
        LA = msg.toString();
        //右后
        msg.delete(0,msg.length());
        doPsi(ObdData.RT.getBrtirePsi());
        doStatus(ObdData.RT.getBrtireStatus());
        doTem(ObdData.RT.getBrtireTemp());
        RB = msg.toString();
    }


    /**
     * 比较轮胎的压力值返回信息
     * @param
     * @return
     */
    private static void doPsi(double psi) {
        if(psi >= H_PSI)
            msg.append("胎压过高");
        else if (psi < L_PSI)
            msg.append("胎压过低");
        else
            msg.append("");
    }
    /**
     * 比较轮胎的压力值返回信息
     * @param
     * @return
     */
    private static void doStatus(String str) {
        if(str.length() != 4){
            return;
        }
        if(str.charAt(0) == '1'){
            msg.append("传感器失效");
        }
        if(str.charAt(1) == '1'){
            msg.append("传感器低电");
        }
        if(str.charAt(2) == '1'){
            msg.append("慢漏气");
        }
        if(str.charAt(3) == '1'){
            msg.append("快漏气");
        }
    }

    /**
     * 比较轮胎的温度返回信息
     * @param fltirePsi
     * @return
     */
    private static void doTem(double tem) {
        if(tem >= H_TEM)
            msg.append("温度过高");
        else
            msg.append("");
    }

    private ObdData() {
    }
}
