package com.huatec.hiot_cloud.ui.login;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

/**
 * 登录模块presenter类
 */
class LoginPresenter extends BasePresenter<LoginView> {
    @Inject
    DataManager dataManager;

    @Inject
    public LoginPresenter() {

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
                        getView().showMessage("登录成功");

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
