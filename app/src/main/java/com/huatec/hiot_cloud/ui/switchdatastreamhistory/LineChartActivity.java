package com.huatec.hiot_cloud.ui.switchdatastreamhistory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.bean.UpDataStreamSwitchBean;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.utils.Constants;
import com.huatec.hiot_cloud.utils.MPChartHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineChartActivity extends BaseActivity<LineChartView, LineChartPresenter> implements LineChartView {

    private static final String TAG = "LineChartActivity";
    @Inject
    LineChartPresenter presenter;

    @BindView(R.id.tv_data_history)
    TextView tvDataHistory;

    @BindView(R.id.toolbar_data_histoty)
    Toolbar toolbarDataHistoty;

    @BindView(R.id.line_chart_history)
    LineChart lineChartHistory;
    /**
     * 上行通道id
     */
    private String upDataStreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        ButterKnife.bind(this);
        upDataStreamId = getIntent().getStringExtra(Constants.INTENT_EXTRA_UP_DATASTREAM_ID);
        Log.d(TAG, "onCreate: " + upDataStreamId);
        initView();
    }

    @Override
    public LineChartPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadUpDataStreamHistory(upDataStreamId);
    }


    @Override
    public void showDataHistory(List<UpDataStreamSwitchBean> dataList) {
        //(LineChart lineChart, List<String> xAxisValue, List<Float> yAxisValue,
        //                                          List<String> timing, String title, boolean showSetValues, boolean isSwitch

        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        List<String> xAxisValue = new ArrayList<>();
        List<Float> yAxisValue = new ArrayList<>();
        List<String> timing = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            xAxisValue.add(String.valueOf(i));
            yAxisValue.add(Float.valueOf(dataList.get(i).getStatus()));
            timing.add(dataList.get(i).getTiming());
        }
        lineChartHistory.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                showMessage(timing.get((int) e.getX()));
            }

            @Override
            public void onNothingSelected() {

            }
        });
        MPChartHelper.setSingleLineChart(lineChartHistory, xAxisValue, yAxisValue, timing, "电平图 0：代表关 1：代表开", true, true);
    }

    /**
     * 初始化的控件
     */
    private void initView() {
        setSupportActionBar(toolbarDataHistoty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDataHistoty.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
