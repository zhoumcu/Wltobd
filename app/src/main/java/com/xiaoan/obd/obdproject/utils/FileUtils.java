package com.xiaoan.obd.obdproject.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author：Administrator on 2017/2/20 17:29
 * company: xxxx
 * email：1032324589@qq.com
 */

public class FileUtils {

    public static List<byte[]> readFileForBin(String path){
        byte[] b = new byte[256];
        List<byte[]> lis = new ArrayList<byte[]>();//用于保存每秒数据块集合
        try {
            File file = new File(path);
            if(file!=null){
                DataInputStream read = new DataInputStream(new FileInputStream(file));
                while (read.read(b)!=-1){
                    lis.add(Arrays.copyOf(b,256));
                    Arrays.toString(b);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lis;
    }
    public static byte[] readRaw(Context context){
        byte[] b = null;
        AssetManager assetManager = context.getResources().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("v20160609.bin");
            inputStream.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
    /**
     * 获取内置SD卡路径
     * @return
     */
    public static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }
    public static String toData(int index, List<byte[]> bytes){
        byte[] bt = new byte[261];
        byte[] data = bytes.get(index);
        byte sum = 0;
        if(index==bytes.size()){
            bt[0] = 0x04;
        }else{
            bt[0] = 0x01;
        }
        bt[1] = intToBytes2(index)[1];
        bt[2] = intToBytes2(index)[0];
        bt[3] = (byte) ~bt[2];
        for(int i=0;i<data.length;i++){
            bt[i+4] = data[i];
            sum += data[i];
        }
        bt[260] = sum;
        return Arrays.toString(bt);
    }
    /**
     * 将int类型的数据转换为byte数组
     * 原理：将int数据中的四个byte取出，分别存储
     * @param n int数据
     * @return 生成的byte数组
     */
    public static byte[] intToBytes2(int n){
        byte[] b = new byte[2];
        for(int i = 0;i < 2;i++){
            b[i] = (byte)(n >> (i * 8));
        }
        return b;
    }

    /**
     * 将int类型的数据转换为byte数组
     * @param n int数据
     * @return 生成的byte数组
     */
    public static byte[] intToBytes(int n){
        String s = String.valueOf(n);
        byte[] b = new byte[2];
        b =s.getBytes();
        return b;
    }

}
