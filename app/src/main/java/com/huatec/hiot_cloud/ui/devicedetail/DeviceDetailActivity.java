package com.huatec.hiot_cloud.ui.devicedetail;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.bean.DeviceDetailBean;
import com.huatec.hiot_cloud.data.bean.SwitchBean;
import com.huatec.hiot_cloud.data.bean.UpdatastreamDataDto;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.utils.Constants;
import com.huatec.hiot_cloud.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceDetailActivity extends BaseActivity<DeviceDetailView, DeviceDetailPresenter> implements DeviceDetailView {


    @Inject
    DeviceDetailPresenter presenter;

    @BindView(R.id.iv_device_detail)
    ImageView ivDeviceDetail;

    @BindView(R.id.tv_device_title)
    TextView tvDeviceTitle;

    @BindView(R.id.tv_device_state)
    TextView tvDeviceState;

    @BindView(R.id.toolbar_device)
    Toolbar toolbarDevice;

    @BindView(R.id.iv_data_stream_history)
    ImageView ivDataStreamHistory;

    @BindView(R.id.tv_data_stream_type)
    TextView tvDataStreamType;

    @BindView(R.id.iv_switch)
    ImageView ivSwitch;

    @BindView(R.id.switch_data_stream)
    Switch switchDataStream;

    @BindView(R.id.srl_deivce_detail)
    SwipeRefreshLayout srlDeivceDetail;

    private String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        ButterKnife.bind(this);
        initView();
        deviceId = getIntent().getStringExtra(Constants.INTENT_EXTRA_DEVICE_ID);

    }

    @Override
    public DeviceDetailPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadDeviceDetail();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //下拉刷新
        srlDeivceDetail.setColorSchemeColors(getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_green_dark)
        );
        srlDeivceDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //加载设备详情
                loadDeviceDetail();

            }
        });

        //tooBar工具栏
        setSupportActionBar(toolbarDevice);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDevice.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 加载设备详情
     */
    private void loadDeviceDetail() {

        srlDeivceDetail.setRefreshing(true);
        presenter.loadDeviceDetail(deviceId);
        srlDeivceDetail.setRefreshing(false);


    }

    @Override
    public void setDeviceDetail(DeviceDetailBean data) {

        if (data == null) {
            return;
        }
        tvDataStreamType.setText(data.getTitle());
        tvDataStreamType.setText(Constants.DEVICE_STATUS_ACTIVITY.equals(data.getStatus()) ? "已激活" : "未激活");
        //显示图片
        ImageUtils.show(this, ivDeviceDetail, ImageUtils.getFullUrl(data.getDeviceimg()));


        //通道解析
        switchDataStream.setVisibility(View.GONE);
        if (data.getUpdatastreamDataDtoList() != null && !data.getUpdatastreamDataDtoList().isEmpty()) {
            UpdatastreamDataDto updatastreamDataDto = data.getUpdatastreamDataDtoList().get(0);

            if (updatastreamDataDto == null) {
                return;
            }
//            Constants.DATA_STREAM_TYPE_SWITCH.equals(updatastreamDataDto.getData_type())?"通道类型"

            if (Constants.DATA_STREAM_TYPE_SWITCH.equals(updatastreamDataDto.getData_type())) {
                tvDataStreamType.setText("开关通道");
                switchDataStream.setVisibility(View.VISIBLE);
                if (updatastreamDataDto.getDataList() != null && !updatastreamDataDto.getDataList().isEmpty()) {
                    SwitchBean switchBean = updatastreamDataDto.getDataList().get(0);
                    if (switchBean == null) {
                        return;
                    }
                    /**
                     * 如果开关状态开
                     */
                    if (Constants.SWITCH_STATUS_ON == switchBean.getStatus()) {
                        ivSwitch.setImageResource(R.drawable.icon_on);
                        switchDataStream.setChecked(true);
                    } else {
                        ivSwitch.setImageResource(R.drawable.icon_off);
                        switchDataStream.setChecked(false);
                    }

                    //设置switch事件
                    switchDataStream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            presenter.changeSwitch(switchBean.getDownDataStreamId(), isChecked);
                        }
                    });
                }
            }
        }
    }
}
