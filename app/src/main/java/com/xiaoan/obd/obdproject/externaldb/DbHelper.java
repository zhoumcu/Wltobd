package com.xiaoan.obd.obdproject.externaldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.text.TextUtils;

import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.entity.FaultCodeBean;
import com.xiaoan.obd.obdproject.entity.common.RecordData;
import com.xiaoan.obd.obdproject.untils.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/6/28.
 */
public class DbHelper {
    public static final String DB_DIR1 = Environment.getExternalStorageDirectory().getAbsolutePath()!=null
            ? Environment.getExternalStorageDirectory().getAbsolutePath()+"/survey/":"/sdcard/xiaoan/survey/";

    public static final String DB_NAME = "survey.db";
    private final SQLiteDatabase database;
    private final String DB_DIR;

    public static DbHelper getInstance(Context context) {
        return new DbHelper(context);
    }

    private DbHelper(Context context, int rawId) {
        database = openDatabase(context,rawId);
        DB_DIR = null;
    }
    private DbHelper(Context context) {
        DB_DIR = context.getFilesDir()+"/databases/";
        database = openDatabase(context, R.raw.xianoan);
    }
    public SQLiteDatabase openDatabase(Context context, int rawId) {
        try {
            // 获得dictionary.db文件的绝对路径
            String databaseFilename = DB_DIR  + DB_NAME;
            File dir = new File(DB_DIR);
            // 如果/sdcard/dictionary目录中存在，创建这个目录
            if (!dir.exists())
                dir.mkdir();
            // 如果在/sdcard/dictionary目录中不存在
            // dictionary.db文件，则从res\raw目录中复制这个文件到
            // SD卡的目录（/sdcard/dictionary）
            if (!(new File(databaseFilename)).exists()) {
                // 获得封装dictionary.db文件的InputStream对象
                InputStream is = context.getResources().openRawResource(rawId);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                // 开始复制dictionary.db文件
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            // 打开/sdcard/dictionary目录中的dictionary.db文件
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
            return database;
        } catch (Exception e) {}
        return null;
    }

    /**
     * 根据品牌id获取车辆系列
     * @param id
     * @return
     */
    public List<CarBean> getCarSeries(long id) {
        // 查找单词的SQL语句
        String sql = "SELECT s.id as id,s.name as name,count(t.id) as num from car_series s LEFT JOIN car_type t ON s.id = t.series where s.brand = ? GROUP BY s.id";
        if(database==null) return new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});
        String cname = "未找到该记录.";
        Logger.e(cname);
        List<CarBean> carBrands = new ArrayList<>();
        // 必须使用moveToFirst方法将记录指针移动到第1条记录的位置
//        cursor.moveToFirst();
        //  如果查找单词，显示其中文信息
        while (cursor.moveToNext()) {
            if (cursor.getCount() > 0) {
                cname = cursor.getString(cursor.getColumnIndex("name"));
                int idS = cursor.getInt(cursor.getColumnIndex("id"));
                int count = cursor.getInt(cursor.getColumnIndex("num"));
                CarBean carBrand = new CarBean();
                carBrand.setBrandCname(cname);
//				carBrand.setEname(ename);
//				carBrand.setInitial(initial);
                carBrand.setId(Long.valueOf(idS));
                carBrand.setCount(count);
                carBrands.add(carBrand);
//                cursor.moveToNext();
            }
        }
        cursor.close();
        return carBrands;
    }

    /**
     * 根据id和seriesId 获取详细车辆信息
     * @param id
     * @return
     */
    public CarBean getCarInfo(long id) {
        // 查找单词的SQL语句
        String sql = "select t.*,d.cname as brandName,d.ename as logoName,s.name as seriesName from car_type t left join car_series s on t.series = s.id left join car_brand d on s.brand = d.id " +
                "where t.id = ?";
        if(database==null) return new CarBean();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});
        String cname = "未找到该记录.";
        CarBean carBrand = null;
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cname = cursor.getString(cursor.getColumnIndex("name"));
            String releaseTime = cursor.getString(cursor.getColumnIndex("releaseTime"));
            String displacement = cursor.getString(cursor.getColumnIndex("displacement"));
            String weight = cursor.getString(cursor.getColumnIndex("weight"));
            String derailleur = cursor.getString(cursor.getColumnIndex("derailleur"));
            String gears = cursor.getString(cursor.getColumnIndex("gears"));
            String seriesName = cursor.getString(cursor.getColumnIndex("seriesName"));
            String logoName = cursor.getString(cursor.getColumnIndex("logoName"));
            String brandName = cursor.getString(cursor.getColumnIndex("brandName"));
            int ids = cursor.getInt(cursor.getColumnIndex("id"));
            Logger.e(cname+"=="+releaseTime);
            carBrand = new CarBean();
            carBrand.setBrandCname(cname);
            carBrand.setDerailleur(derailleur);
            carBrand.setDisplacement(displacement);
            carBrand.setReleaseTime(releaseTime);
            carBrand.setWeight(weight);
            carBrand.setGears(gears);
            carBrand.setCarSeriesName(seriesName);
            carBrand.setBrandEname(logoName);
            carBrand.setId(Long.valueOf(ids));
            carBrand.setCarTypeName(brandName);
        }
        cursor.close();
        return carBrand;
    }

    public List<FaultCodeBean> searchTroubleCode(String code) {
        // 查找单词的SQL语句
        String sql = "select * from fault_code WHERE code =?";
        if(database==null) return new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, new String[]{code});
        List<FaultCodeBean> SourceDateList = new ArrayList<>();
        while (cursor.moveToNext()) {
            if (cursor.getCount() > 0) {
                String cname = cursor.getString(cursor.getColumnIndex("background"));
                String codeR = cursor.getString(cursor.getColumnIndex("code"));
                String cdefine = cursor.getString(cursor.getColumnIndex("cdefine"));
                String scope = cursor.getString(cursor.getColumnIndex("scope"));
                String status = cursor.getString(cursor.getColumnIndex("status"));
                String useRange = cursor.getString(cursor.getColumnIndex("use_range"));
                int ids = cursor.getInt(cursor.getColumnIndex("id"));
                FaultCodeBean codeBean = new FaultCodeBean();
                codeBean.setCode(codeR);
                codeBean.setContentZh(cname);
                codeBean.setCdefine(cdefine);
                codeBean.setScope(scope);
                codeBean.setStatus(status);
                codeBean.setUserRange(useRange);
                codeBean.setId(ids);
                SourceDateList.add(codeBean);
            }
        }
        cursor.close();
        return SourceDateList;
    }
    public FaultCodeBean searchTroubleCode(int id) {
        // 查找单词的SQL语句
        String sql = "select * from fault_code WHERE id =?";
        FaultCodeBean codeBean = new FaultCodeBean();
        if(database==null) return codeBean;
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String cname = cursor.getString(cursor.getColumnIndex("background"));
            String codeR = cursor.getString(cursor.getColumnIndex("code"));
            String cdefine = cursor.getString(cursor.getColumnIndex("cdefine"));
            String scope = cursor.getString(cursor.getColumnIndex("scope"));
            String status = cursor.getString(cursor.getColumnIndex("status"));
            String useRange = cursor.getString(cursor.getColumnIndex("use_range"));
            int ids = cursor.getInt(cursor.getColumnIndex("id"));
            codeBean.setCode(codeR);
            codeBean.setContentZh(cname);
            codeBean.setCdefine(cdefine);
            codeBean.setScope(scope);
            codeBean.setStatus(status);
            codeBean.setUserRange(useRange);
            codeBean.setId(ids);
        }
        cursor.close();
        return codeBean;
    }
    public List<CarBean> getCarType(long id) {
        // 查找单词的SQL语句
        String sql = "select * from car_type where series =?";
        if(database==null) return new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});
        String cname = "未找到该单词.";
        List<CarBean> SourceDateList = new ArrayList<>();
        //  如果查找单词，显示其中文信息
        while (cursor.moveToNext()) {
            if (cursor.getCount() > 0) {
                cname = cursor.getString(cursor.getColumnIndex("name"));
                String releaseTime = cursor.getString(cursor.getColumnIndex("releaseTime"));
                String displacement = cursor.getString(cursor.getColumnIndex("displacement"));
                String weight = cursor.getString(cursor.getColumnIndex("weight"));
                String derailleur = cursor.getString(cursor.getColumnIndex("derailleur"));
                String gears = cursor.getString(cursor.getColumnIndex("gears"));
                int ids = cursor.getInt(cursor.getColumnIndex("id"));
                CarBean carBrand = new CarBean();
                carBrand.setBrandCname(cname);
                carBrand.setDerailleur(derailleur);
                carBrand.setDisplacement(displacement);
                carBrand.setReleaseTime(releaseTime);
                carBrand.setWeight(weight);
                carBrand.setGears(gears);
                carBrand.setId(Long.valueOf(ids));
                SourceDateList.add(carBrand);
//                cursor.moveToNext();
            }
        }
        cursor.close();
        Logger.e(SourceDateList.size()+"");
        return SourceDateList;
    }
    public List<CarBean> getCarList() throws NullPointerException {
        // 查找单词的SQL语句
        String sql = "select * from car_brand ";
        if(database==null) return new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        String cname = "未找到该单词.";
        String ename = "未找到该单词.";
        List<CarBean> carBrands = new ArrayList<>();
        // 必须使用moveToFirst方法将记录指针移动到第1条记录的位置
//        cursor.moveToFirst();
        //  如果查找单词，显示其中文信息
        while (cursor.moveToNext()) {
            if (cursor.getCount() > 0) {
                cname = cursor.getString(cursor.getColumnIndex("cname"));
                ename = cursor.getString(cursor.getColumnIndex("ename"));
                String initial = cursor.getString(cursor.getColumnIndex("initial"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                CarBean carBrand = new CarBean();
                carBrand.setBrandCname(cname);
                carBrand.setBrandEname(ename);
                carBrand.setInitial(initial);
                carBrand.setId(Long.valueOf(id));
                carBrands.add(carBrand);
//                cursor.moveToNext();
            }
        }
        cursor.close();
        return carBrands;
    }

    public List<CarBean> getCarLunTaiTypeList() {
        // 查找单词的SQL语句
        String sql = "select * from car_tire ";
        if(database==null) return new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, new String[]{});
        String cname = "未找到该记录.";
        String ename = "未找到该记录.";
        List<CarBean> carBrands = new ArrayList<>();
        // 必须使用moveToFirst方法将记录指针移动到第1条记录的位置
//        cursor.moveToFirst();
        //  如果查找单词，显示其中文信息
        while (cursor.moveToNext()) {
            if (cursor.getCount() > 0) {
                cname = cursor.getString(cursor.getColumnIndex("name"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                CarBean carBrand = new CarBean();
                carBrand.setBrandCname(cname);
                carBrand.setId(Long.valueOf(id));
                carBrands.add(carBrand);
//                cursor.moveToNext();
            }
        }
        cursor.close();
        return carBrands;
    }

    public void updateCarInfo(CarBean carBrand, int id) {
        // 查找单词的SQL语句
        String sql = "update into  car_info (brandCname,brandEname,carTypeName,carSeriesName,releaseTime,userCode,weight,displacement,derailleur,gears) values (?,?,?,?,?,?,?,?,?,?)";
        ContentValues cv = new ContentValues();
        cv.put("ftyre", carBrand.getFtyre());
        cv.put("btyre", carBrand.getBtyre());
        database.update("car_info", cv,"id = ?",new String[]{String.valueOf(id)});
        getUserCarInfo(id);
    }

    public CarBean getUserCarInfo(int id) {
        // 查找单词的SQL语句
        String sql = "select * from car_info where id = ?";
        if(database==null) return new CarBean();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});
        // 必须使用moveToFirst方法将记录指针移动到第1条记录的位置
        cursor.moveToFirst();
        //  如果查找单词，显示其中文信息
        CarBean carBrand = null;
        if (cursor.getCount() > 0) {
            String releaseTime = cursor.getString(cursor.getColumnIndex("releaseTime"));
            String displacement = cursor.getString(cursor.getColumnIndex("displacement"));
            String weight = cursor.getString(cursor.getColumnIndex("weight"));
            String derailleur = cursor.getString(cursor.getColumnIndex("derailleur"));
            String gears = cursor.getString(cursor.getColumnIndex("gears"));
            String seriesName = cursor.getString(cursor.getColumnIndex("carSeriesName"));
            String logoName = cursor.getString(cursor.getColumnIndex("brandEname"));
            String brandName = cursor.getString(cursor.getColumnIndex("brandCname"));
            String ftyre = cursor.getString(cursor.getColumnIndex("ftyre"));
            String btyre = cursor.getString(cursor.getColumnIndex("btyre"));
            int ids = cursor.getInt(cursor.getColumnIndex("id"));
            String carTypeName = cursor.getString(cursor.getColumnIndex("carTypeName"));
            carBrand = new CarBean();
            carBrand.setDerailleur(derailleur);
            carBrand.setDisplacement(displacement);
            carBrand.setReleaseTime(releaseTime);
            carBrand.setBrandCname(carTypeName);
            carBrand.setWeight(weight);
            carBrand.setGears(gears);
            carBrand.setCarSeriesName(seriesName);
            carBrand.setBrandEname(logoName);
            carBrand.setId(Long.valueOf(ids));
            carBrand.setFtyre(ftyre);
            carBrand.setBtyre(btyre);
            carBrand.setCarTypeName(brandName);
        }
        cursor.close();
        return carBrand;
    }
    public void insertCarData(RecordData data) throws SQLiteCantOpenDatabaseException {
        // 查找单词的SQL语句
        String sql = "insert into  car_data (deviceId,name,data,createDate,state) values (?,?,?,?,?)";
        if(database==null) return;
        ContentValues cv = new ContentValues();
        cv.put("deviceId", data.getDeviceId());
        cv.put("name", data.getName());
        cv.put("data", data.getData());
        cv.put("state", data.getState());
        cv.put("createDate", data.getCreateDate());
        database.insert("car_data", null,cv);
//        Cursor cursor = database.rawQuery("select max(id) as uid from car_info", null);
//        cursor.moveToFirst();
//        return cursor.getInt(cursor.getColumnIndex("uid"));
    }
    public void updateCarData(int id, String name, RecordData data) throws SQLiteCantOpenDatabaseException {
        // 查找单词的SQL语句
        String sql = "update into  car_info (brandCname,brandEname,carTypeName,carSeriesName,releaseTime,userCode,weight,displacement,derailleur,gears) values (?,?,?,?,?,?,?,?,?,?)";
        if(database==null) return ;
        ContentValues cv = new ContentValues();
        cv.put("data", data.getData());
        cv.put("state", data.getState());
        cv.put("createDate", data.getCreateDate());
        database.update("car_data", cv,"deviceId = ? and name =?",new String[]{String.valueOf(id),name});
//        getUserCarInfo(id);
    }
    public boolean update(int deviceId, String name, RecordData data) {
        if(TextUtils.isEmpty(name)) return false;
        if(database==null) return false;
        try {
            String sql = "select * from car_data where deviceId = ? and name = ?";
            Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(deviceId),name});
            // 必须使用moveToFirst方法将记录指针移动到第1条记录的位置
            if(cursor.getCount()>0) {
                updateCarData(deviceId,name,data);
            }else {
                insertCarData(data);
            }
            cursor.close();
            return true;
        }catch (IllegalArgumentException e) {
        }catch (NullPointerException e1) {
        }catch (SQLiteCantOpenDatabaseException e2) {
        }catch (Exception e){
        }
        return false;
    }
    @Deprecated
    public Observable<Boolean> updateRecord(int deviceId, String name, RecordData data) {
        return Observable.just(update(deviceId,name,data));
    }
    public List<RecordData> getCarDataList(int deviceId) throws SQLiteException {
        // 查找单词的SQL语句
        String sql = "select * from car_data where deviceId = ?";
        if(database==null) return new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(deviceId)});
        String name = "未找到该单词.";
        String data = "未找到该单词.";
        List<RecordData> carBrands = new ArrayList<>();
        // 必须使用moveToFirst方法将记录指针移动到第1条记录的位置
//        cursor.moveToFirst();
        if(cursor.getCount()<=0) return carBrands;
        //  如果查找单词，显示其中文信息
        while (cursor.moveToNext()) {
            if (cursor.getCount() > 0) {
                name = cursor.getString(cursor.getColumnIndex("name"));
                data = cursor.getString(cursor.getColumnIndex("data"));
                String state = cursor.getString(cursor.getColumnIndex("state"));
                String createDate = cursor.getString(cursor.getColumnIndex("createDate"));
                RecordData carBrand = new RecordData();
                carBrand.setName(name);
                carBrand.setData(data);
                carBrand.setCreateDate(createDate);
                carBrand.setState(state);
                carBrands.add(carBrand);
//                cursor.moveToNext();
            }
        }
        cursor.close();
        return carBrands;
    }
}
