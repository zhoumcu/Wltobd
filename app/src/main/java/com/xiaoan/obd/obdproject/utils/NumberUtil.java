package com.xiaoan.obd.obdproject.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/11.
 */

public class NumberUtil {

    /**
     * string obd使用转数字
     * @param str
     * @return
     */
    public static Integer toInteger(String str){
        if(str == null || str.trim() == "") return 0;
        try {
            return Integer.valueOf(str);
        }catch (Exception e){
            return 0;
        }
    }

    public static Double toDouble(String str){
        if(str == null || str.trim() == "") return 0.0;
        try {
            return Double.valueOf(str);
        }catch (Exception e){
            return 0.0;
        }
    }
    public static CharSequence getTimeMillis(long strTime) {
        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd HH:mm:ss", strTime);
        return sysTimeStr;
    }
    public static CharSequence getTime(long strTime) {
        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd", strTime);
        return sysTimeStr;
    }
    public static long getTimeMillis(String strTime) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
        }
        return returnMillis;
    }
    public static String getTimeExpend(String startTime, String endTime){
        //传入字串类型 2016/06/28 08:30
        long longStart = getTimeMillis(startTime); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差

        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数
        long longMs = (longExpend - longHours * (60 * 60 * 1000)-longMinutes*(60*1000)) / 1000;   //根据时间差来计算分钟数

        return longHours + ":" + longMinutes+":"+longMs;
    }

    public static String getTimeExpend(long startTime, long endTime){
        //传入字串类型 2016/06/28 08:30
        long longStart = startTime; //获取开始时间毫秒数
        long longEnd = endTime;  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差

        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数
        long longMs = (longExpend - longHours * (60 * 60 * 1000)-longMinutes*(60*1000)) / 1000;   //根据时间差来计算分钟数

        return longHours + ":" + longMinutes+":"+longMs;
    }
    public static String getWeekByDateStr(long strDate) {
        return getWeekByDateStr(getTime(strDate).toString());
    }
    /**
     * <pre>
     * 根据指定的日期字符串获取星期几
     * </pre>
     *
     * @param strDate 指定的日期字符串(yyyy-MM-dd 或 yyyy/MM/dd)
     * @return week
     *         星期几(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY)
     */
    public static String getWeekByDateStr(String strDate) {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);

        switch (weekIndex) {
            case 1:
                week = "日";
                break;
            case 2:
                week = "一";
                break;
            case 3:
                week = "二";
                break;
            case 4:
                week = "三";
                break;
            case 5:
                week = "四";
                break;
            case 6:
                week = "五";
                break;
            case 7:
                week = "六";
                break;
        }
        return year + "年" + month + "月" + day+"日"+" 星期"+week;
    }
}
