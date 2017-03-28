package com.xiaoan.obd.obdproject.untils;

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


}
