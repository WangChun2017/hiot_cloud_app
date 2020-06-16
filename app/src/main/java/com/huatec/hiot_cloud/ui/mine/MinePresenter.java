package com.huatec.hiot_cloud.ui.mine;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.data.bean.UserBean;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

public class MinePresenter extends BasePresenter<MineView> {

    @Inject
    DataManager dataManager;

    @Inject
    public MinePresenter() {
    }

    /**
     * 加载用户信息
     */
    public void loadUserInfo() {
        subscrib(dataManager.getUserInfo(), new ResquestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> resultBase) {
                if (resultBase == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }
                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }
                if (resultBase.getData() == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }

                UserBean userBean = resultBase.getData();
                getView().refreshUserInfo(userBean);
            }
        });
        ;
    }

    public void uploadImage(String filePath) {
        subscrib(dataManager.uploadImage(filePath), new ResquestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> resultBase) {
                if (resultBase == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }

                //如果token失效
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }

                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }

                //获取相对路径
                String url = resultBase.getData();

                //刷新用户头像
                getView().refreshUserHead(url);
            }
        });
    }

    /***
     * 注销
     */
    public void logout() {
        subscrib(dataManager.logout(), new ResquestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                if (resultBase == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }
                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }
                //如果返回成功，打开登录界面
                getView().tokenOut();
            }
        });
    }
}
