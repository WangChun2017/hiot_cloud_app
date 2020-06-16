package com.huatec.hiot_cloud.data.bean;

import java.io.Serializable;

/**
 * 开关通道对象
 */
public class SwitchBean implements Serializable {

    private String downDataStreamId;

    private String timing;

    private int status;

    public String getDownDataStreamId() {
        return downDataStreamId;
    }

    public void setDownDataStreamId(String downDataStreamId) {
        this.downDataStreamId = downDataStreamId;
    }

    /**
     * get and set 方法
     *
     * @return
     */
//    public String getDownDataStream() {
//        return downDataStreamId;
//    }
//
//    public void setDownDataStream(String downDataStream) {
//        this.downDataStreamId = downDataStream;
//    }
    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
