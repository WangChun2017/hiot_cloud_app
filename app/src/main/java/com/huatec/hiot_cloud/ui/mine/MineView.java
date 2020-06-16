package com.huatec.hiot_cloud.ui.mine;


import com.huatec.hiot_cloud.data.bean.UserBean;
import com.huatec.hiot_cloud.ui.base.BaseView;

public interface MineView extends BaseView {

    void refreshUserInfo(UserBean userBean);

    void refreshUserHead(String url);

    void tokenOut();
}
