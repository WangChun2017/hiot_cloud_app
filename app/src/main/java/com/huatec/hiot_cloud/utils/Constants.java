package com.huatec.hiot_cloud.utils;

/*
 *   常量类
 */
public class Constants {
    public static final int MAIN_FRAGMENT_COUNT = 4;
    public static final int MAIN_VIEWPAGER_INDEX_MESAAGE = 0;
    public static final int MAIN_VIEWPAGER_INDEX_EQUIPMENT = 1;
    public static final int MAIN_VIEWPAGER_INDEX_SCENE = 2;
    public static final int MAIN_VIEWPAGER_INDEX_MINE = 3;
    /**
     * app登录的loginCode
     */
    public static final String LOGIN_CODE_APP = "app";
    /**
     * 设备已绑定状态
     */
    public static final int DEVICE_STATUS_BINDED = 1;
    /**
     * 服务端返回消息状态属性成功
     */
    public static final int MSG_STATUS_SUCCESS = 1;
    /**
     * 设备未绑定状态
     */
    public static final int DEVICE_STATUS_UNBINDED = 0;
    /**
     * 设备id
     */
    public static final String INTENT_EXTRA_DEVICE_ID = "DEVICE_ID ";
    /**
     * 已激活
     */
    public static final String DEVICE_STATUS_ACTIVITY = "1";
    /**
     * 已激活
     */
    public static final String DEVICE_STATUS_UN_ACTIVITY = "0";
    /**
     * 类型2
     */
    public static final String DATA_STREAM_TYPE_SWITCH = "2";
    /**
     * 类型1
     */
    public static final String DATA_STREAM_TYPE_VALUE = "1";
    /**
     * 类型3
     */
    public static final String DATA_STREAM_TYPE_GPS = "3";
    /**
     * 类型4
     */
    public static final String DATA_STREAM_TYPE_TEXT = "4";

    /**
     * 开关状态开
     */
    public static final int SWITCH_STATUS_ON = 1;
    /**
     * 开关状态关
     */
    public static final int SWITCH_STATUS_OFF = 0;
    /**
     * 通道id参数名称
     */
    public static final String INTENT_EXTRA_UP_DATASTREAM_ID = "INTENT_EXTRA_UP_DATASTREAM_ID";
    /**
     * 默认每页换回的数据
     */
    public static final int DEFAULT_DATASTREAM_LIMIT = 10;

    public static String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final int MSG_STATUS_TOKEN_OUT = -100;

    /**
     * APP的注册
     */

    public static final String REGISTER_TYPE_NORMAL = "1";
}
