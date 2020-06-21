package com.huatec.hiot_cloud.data.bean;

import java.io.Serializable;

/**
 * 上行开关通道对象
 */
public class UpDataStreamSwitchBean implements Serializable {

    private String updatastreamId;

    private String timing;

    private int status;


    /**
     * get and set 方法
     *
     * @return
     */
    public String getUpdatastreamId() {
        return updatastreamId;
    }

    public void setUpdatastreamId(String updatastreamId) {
        this.updatastreamId = updatastreamId;
    }

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
