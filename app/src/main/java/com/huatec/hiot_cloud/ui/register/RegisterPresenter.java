package com.huatec.hiot_cloud.ui.register;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.data.bean.UserBean;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

/**
 * 注册模块presenter类
 */
class RegisterPresenter extends BasePresenter<RegisterView> {

    @Inject
    DataManager dataManager;

    /**
     * 构造方法
     */
    @Inject
    public RegisterPresenter() {

    }

    /**
     * 注册
     */
    public void register(String userName, String password, String email) {
        subscrib(dataManager.register(userName, password, email), new ResquestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> resultBase) {
                //判断成功，弹出注册成功，自动登录
                if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //正确,弹出登录成功，跳转到主要界面

                    if (resultBase != null && resultBase.getData() != null) {
                        getView().showMessage("注册成功");

                        getView().registerSucc(email, password);
                    }
                } else {
                    //错误
                    if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                        getView().showMessage(resultBase.getMsg());
                    }
                }
                //注册失败，返回错误信息

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMessage("当前网络无法访问，请稍后再试");
            }
        });

    }

    /**
     * 登录
     *
     * @param email
     * @param password
     */
    public void login(String email, String password) {
        subscrib(dataManager.login(email, password), new ResquestCallback<ResultBase<LoginResultDTO>>() {
            @Override
            public void onNext(ResultBase<LoginResultDTO> resultBase) {

                if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //正确,弹出登录成功，跳转到主要界面

                    if (resultBase != null && resultBase.getData() != null) {
//                        getView().showMessage("注册成功");

                        getView().loginSucc();
                    }
                } else {
                    //错误
                    if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                        getView().showMessage(resultBase.getMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMessage("当前网路无法访问，请稍后再试");
            }
        });
    }
}
