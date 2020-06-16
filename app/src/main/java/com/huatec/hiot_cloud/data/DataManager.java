package com.huatec.hiot_cloud.data;


import android.util.Log;

import com.huatec.hiot_cloud.data.bean.DeviceBean;
import com.huatec.hiot_cloud.data.bean.DeviceDetailBean;
import com.huatec.hiot_cloud.data.bean.UserBean;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.utils.Constants;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * 网络请求封装类
 */
public class DataManager {


    private NetworkService service;
    SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public DataManager(NetworkService service, SharedPreferencesHelper sharedPreferencesHelper) {
        this.service = service;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    //登录
    public Observable<ResultBase<LoginResultDTO>> login(String userName, String password) {
        return service.login(userName, password, Constants.LOGIN_CODE_APP)
                .doOnNext(new Consumer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void accept(ResultBase<LoginResultDTO> resultBase) throws Exception {
                        if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                            if (resultBase != null && resultBase.getData() != null) {
                                sharedPreferencesHelper.setUserToken(resultBase.getData().getTokenValue());
                            }
                        }
                    }
                });
    }

    //获取信息

    public Observable<ResultBase<UserBean>> getUserInfo() {
        return service.getUserInfo(sharedPreferencesHelper.getUserToken());
    }


    //更改邮箱

    public Observable<ResultBase<String>> updateEmail(String email) {
        return service.updateEmail(sharedPreferencesHelper.getUserToken(), email);
    }

    //注册

    public Observable<ResultBase<UserBean>> register(String userName, String password, String email) {
        UserBean userBean = new UserBean();

        userBean.setUsername(userName);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setUserType(Constants.REGISTER_TYPE_NORMAL);
        return service.register(userBean);
    }


    /**
     * 上传图片
     *
     * @param filePath
     * @return
     */
    public Observable<ResultBase<String>> uploadImage(String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.MULTIPART_FORM_DATA), file);
        MultipartBody.Part multipartFile = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return service.uploadImage(multipartFile, sharedPreferencesHelper.getUserToken());
    }

    public Observable<ResultBase> logout() {
        return service.logout(sharedPreferencesHelper.getUserToken())
                .doOnNext(new Consumer<ResultBase>() {
                    @Override
                    public void accept(ResultBase resultBase) throws Exception {
                        sharedPreferencesHelper.setUserToken("");
                    }
                });
    }

    /**
     * 绑定设备
     *
     * @param deviceId
     * @return
     */
    public Observable<ResultBase> bindDevice(String deviceId) {
        Log.d(TAG, "bindDevice: " + deviceId);
        Log.d(TAG, "bindDevice: " + sharedPreferencesHelper.getUserToken());
        return service.bindDevice(deviceId, sharedPreferencesHelper.getUserToken());

    }


    /**
     * 获取指定绑定状态的设备列表
     *
     * @param bonding
     * @return
     */
    public Observable<ResultBase<List<DeviceBean>>> listBindedDevice(int bonding) {
        return service.listBindedDevice(bonding, sharedPreferencesHelper.getUserToken());
    }

    /**
     * 获取设备详情
     *
     * @param deviceId
     * @return
     */
    public Observable<ResultBase<DeviceDetailBean>> getDeviceDetail(String deviceId) {
        return service.getDeviceDetail(deviceId, sharedPreferencesHelper.getUserToken());
    }

    /**
     * 控制通道开关状态
     *
     * @param dataStreamId
     * @param status
     * @return
     */
    public Observable<ResultBase> changeSwitch(String dataStreamId, int status) {
        return service.changeSwitch(dataStreamId, status, sharedPreferencesHelper.getUserToken());
    }
}
