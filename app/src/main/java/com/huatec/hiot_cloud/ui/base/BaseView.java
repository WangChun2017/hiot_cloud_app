package com.huatec.hiot_cloud.ui.base;
/*
    MVP架构图层接口
 */
//实验2.2
public interface BaseView {
    /**
     * 弹出消息
     *
     * @param message
     */
    void showMessage(String message);

    /***
     * token失效的处理
     */
    void tokenOut();
}
