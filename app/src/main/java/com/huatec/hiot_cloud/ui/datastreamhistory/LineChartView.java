package com.huatec.hiot_cloud.ui.datastreamhistory;

import com.huatec.hiot_cloud.data.bean.UpDataStreamSwitchBean;
import com.huatec.hiot_cloud.ui.base.BaseView;

import java.util.List;

/**
 * 通道历史数据View层接口
 */
interface LineChartView extends BaseView {

    /**
     * 把返回的历史通道数据显示套图标中
     *
     * @param dataList
     */
    void showDataHistory(List<UpDataStreamSwitchBean> dataList);
}
