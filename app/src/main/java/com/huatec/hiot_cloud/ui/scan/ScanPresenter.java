package com.huatec.hiot_cloud.ui.scan;

import android.util.Log;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 扫一扫模块Presenter类
 */
class ScanPresenter extends BasePresenter<ScanView> {

    private static final String TAG = "ScanPresenter";

    @Inject
    DataManager dataManager;

    @Inject
    public ScanPresenter() {
    }


    /**
     * 绑定设备
     *
     * @param deviceId
     */
    public void bindDevice(String deviceId) {
        Log.d(TAG, "bindDevice: " + deviceId);
        subscrib(dataManager.bindDevice(deviceId), new ResquestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                super.onNext(resultBase);
                getView().showMessage("绑定成功");
                getView().bindDeviceSucc();

            }
        });

    }
}
