package com.huatec.hiot_cloud.ui.gpsdatastreamhistory;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.data.bean.UpDataStreamGpsBean;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * GPS 数据可视化Presenter层类
 */
class GpsDataStreamHistoryPresenter extends BasePresenter<GpsDataStreamHistoryView> {


    @Inject
    DataManager dataManager;

    @Inject
    public GpsDataStreamHistoryPresenter() {
    }

    /**
     * 加载GPS数据
     *
     * @param upDataStreamId
     */
    public void loadGpsDataStreamHistory(String upDataStreamId) {
        subscrib(dataManager.getGpsUpDataStreamHistory(upDataStreamId), new RequestCallback<ResultBase<List<UpDataStreamGpsBean>>>() {
            @Override
            public void onNext(ResultBase<List<UpDataStreamGpsBean>> ResultBase) {
                super.onNext(ResultBase);
                if (ResultBase.getData() != null) {
                    getView().showData(ResultBase.getData());
                }
            }
        });
    }
}
