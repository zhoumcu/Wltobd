package com.xiaoan.obd.obdproject.server;

import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.entity.common.ComResult;
import com.xiaoan.obd.obdproject.entity.common.Token;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ServiceAPI {

    String BASEURL = "http://api.xiaoan360.com/";
//    String BASEURL = "http://apis.baidu.com/showapi_open_bus/";

    @GET("api/clientele/login")
    Observable<User> login(@Query("userCode") String userCode, @Query("pwd") String pwd, @Query("deviceInfo") String deviceInfo);

    @GET("api/clientele/find")
    Observable<ComResult> findPwd(@Query("type") String type, @Query("phone") String phone);

    @GET("api/clientele/find")
    Observable<User> findAccount(@Query("type") String type, @Query("phone") String phone);

    @GET("api/clientele/register")
    Observable<ComResult> register(@Query("userCode") String userCode, @Query("nickName") String nickName
            , @Query("mobile") String mobile,@Query("pwd") String pwd);

    @GET("api/sendSms/sendCode")
    Observable<ComResult> sendCode(@Query("code") String code, @Query("phone") String phone);

    @GET("api/token/myToken")
    Observable<Token>  requestToken(@Query("account") String phone);

    @POST("api/clientele/update")
    Observable<User> updateName(@Query("token") String token,@Query("type") String changeName,@Query("account") String account,@Query("name") String name);

    @POST("api/clientele/update")
    Observable<User> updatePhone(@Query("token") String token,@Query("type") String type,@Query("account") String account,@Query("phone") String phone);

    @POST("api/clientele/update")
    Observable<ComResult> updatePwd(@Query("token") String token,@Query("type") String type,@Query("account") String account
            ,@Query("oldPwd") String oldPwd,@Query("newPwd") String newPwd);

    @POST("api/boxinfo/bind")
    Observable<ComResult> bindBox(@Query("token") String token,@Query("boxCode") String boxCode,@Query("userCarID") String userCarID);

    @POST("api/boxinfo/unbind")
    Observable<ComResult> unBindBox(@Query("token") String token,@Query("userCarID") String userCarID);

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("api/carinfo/upload")
    Observable<Object> uploadCarInfo(@Query("carinfo") String carinfo);

    @POST("api/carinfo/delete")
    Observable<ComResult> deleteCarInfo(@Query("token") String token,@Query("userCarID") String userCarID);

    @GET("api/carinfo/download")
    Observable<List<CarBean>> downLoadCarInfo(@Query("token") String token, @Query("userCode") String userCode);

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("api/obdstream/upload")
    Observable<Object> uploadRT(@Query("token") String token, @Query("obdStream") String obdStream);

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("api/obdstrokestream/upload")
    Observable<Object> uploadTT(@Query("token") String token, @Query("obdStrokeStream") String obdStrokeStream);
}