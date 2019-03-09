package com.sinano.http;

import com.sinano.base.BaseResultBean;
import com.sinano.devices.model.ConfigDetailBean;
import com.sinano.devices.model.ConfigListBean;
import com.sinano.devices.model.DeviceInfoBean;
import com.sinano.devices.model.DeviceListBean;
import com.sinano.devices.model.TypeBean;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.ClothDescForMd5Bean;
import com.sinano.result.model.DeviceResultForConfigBean;
import com.sinano.result.model.ResultBean;
import com.sinano.user.model.AppVersionBean;
import com.sinano.user.model.ChildUserBean;
import com.sinano.user.model.LoginBean;
import com.sinano.user.model.UserInfoBean;

import java.util.Map;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface NetworkInterface {

    /**
     * 设备列表
     *
     * @return
     */
    @GET("device/")
    Observable<DeviceListBean> devicesList(@Query("id") String id);

    /**
     * 模型列表
     *
     * @param map
     * @return
     */
    @GET("user/model")
    Observable<DeviceListBean> modelList(@QueryMap Map<String, Object> map);

    /**
     * 登录
     *
     * @return
     */
    @POST("user/login")
    Observable<LoginBean> login(@Query("username") String userName, @Query("password") String passowrd);

    /**
     * 退出登陆
     *
     * @return
     */
    @GET("user/logout")
    Observable<BaseResultBean> logout();

    /**
     * 修改密码
     *
     * @return
     */
    @PUT("user/password")
    Observable<BaseResultBean> updataPassword(@Query("newPwd") String newPassword, @Query("oldPwd") String oldPassword);

    /**
     * 修改用户信息
     *
     * @return
     */
    @PUT("user")
    Observable<BaseResultBean> updataUserInfo(@Query("nickname ") String name, @Query("phone") String phone);

    /**
     * 注册
     *
     * @param map
     * @return
     */
    @POST("user/register")
    Observable<BaseResultBean> register(@QueryMap Map<String, Object> map);

    /**
     * 查询子账号
     */
    @GET("user/child")
    Observable<ChildUserBean> getChildUser();

    /**
     * 获取个人信息
     */
    @GET("user/{uid}")
    Observable<UserInfoBean> getUserInfo(@Path("uid") int id);

    /**
     * 设备详情
     *
     * @param map
     * @return
     */
    @GET("device/{deviceId}")
    Observable<DeviceListBean> devicesDetails(@QueryMap Map<String, Object> map);

    /**
     * 配置列表
     *
     * @param map
     * @return
     */
    @GET("config")
    Observable<ConfigListBean> configList(@QueryMap Map<String, Object> map);


    /**
     * 编辑配置
     *
     * @param map
     * @return
     */
    @PUT("config")
    Observable<DeviceListBean> updataConfig(@QueryMap Map<String, Object> map);

    /**
     * 配置详情
     *
     * @param map
     * @return
     */
    @GET("config/{id}")
    Observable<DeviceListBean> configDetails(@QueryMap Map<String, Object> map);

    /**
     * 配置版本列表
     */
    @GET("config/version/{id}")
    Observable<ConfigDetailBean> configVersionDetail(@Path("id") String id);

    /**
     * 删除配置
     *
     * @param map
     * @return
     */
    @DELETE("config/{id}")
    Observable<DeviceListBean> deleteConfig(@QueryMap Map<String, Object> map);

    /**
     * 切换配置
     *
     * @param map
     * @return
     */
    @POST("config/switch")
    Observable<DeviceListBean> switchModel(@QueryMap Map<String, Object> map);

    /**
     * 同步配置
     *
     * @return
     */
    @POST("config/synchronize")
    Observable<BaseResultBean> synchronizeConfig(@Query("mac") String mac);

    /**
     * 配置更新
     *
     * @param map
     * @return
     */
    @POST("config/update")
    Observable<DeviceListBean> configUpdata(@QueryMap Map<String, Object> map);


    /**
     * 工艺类型
     *
     * @return
     */
    @GET("comm/classification")
    Observable<TypeBean> typeData();

    /**
     * 获取设备状态列表
     */
    @GET("device/online")
    Observable<DeviceListBean> getDeviceStatusList();


    /**
     * 获取全部检测结果
     */
    @GET("cr/count")
    Observable<ResultBean> getAllCheckResult();

    /**
     * 根据配置获取设备结果
     */
    @GET("cr/count_device/{configId}")
    Observable<DeviceResultForConfigBean> getCheckResultForConfig(@Path("configId") String id);

    /**
     * 根据设备查对应的配置
     */
    @GET("cr/count_config/{mac}")
    Observable<DeviceResultForConfigBean> getCheckResultForDevice(@Path("mac") String id);

    /**
     * 获取检测结果详情
     */
    @GET("cr")
    Observable<CheckResultDetailBean> getCheckResultDetail(@QueryMap Map<String, Object> map);

    /**
     * 获取最新的版本号
     */
    @GET("apk/latest/phone")
    Observable<AppVersionBean> getLastVersionInfo();


    /**
     * 根据md5查询结果
     *
     * @return
     */
    @GET("cr/md5/{md5}")
    Observable<ClothDescForMd5Bean> getCheckResultForMd5(@Path("md5") String md5);

    /**
     * 注册设备
     *
     * @return
     */
    @POST("device/register")
    Observable<BaseResultBean> registerDevice(@Query("diskSeq") String seq);


    /**
     * 获取最新的设备信息
     *
     * @return
     */
    @GET("device/getDeviceInfo")
    Observable<DeviceInfoBean> getDeviceInfo(@Query("mac") String mac);

    /**
     * 提醒设备更新信息
     *
     * @return
     */
    @GET("device/publishMac")
    Observable<BaseResultBean> publishDevice(@Query("mac") String mac);

    /**
     * 绑定公司
     *
     * @return
     */
    @POST("user/bindCompany")
    Observable<BaseResultBean> bindCompany(@Query("companyId") String companyId);


}
