package com.sinano.result.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.sinano.R;
import com.sinano.base.BaseFragment;
import com.sinano.devices.model.ChartDevicesBean;
import com.sinano.result.view.activity.TerminalResultDetailActivity;
import com.sinano.result.view.adapter.RcyCheckResultAdapter;
import com.sinano.result.view.adapter.RcyTerminalListAdapter;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class CheckResultManageFragment extends BaseFragment implements RcyTerminalListAdapter.OnItemClickListener, View.OnClickListener {

    private TextView mTvAllCount;
    private TextView mTvType;
    private TextView mTvProjectCount;
    private RecyclerView mRcyProjectList;
    private RcyCheckResultAdapter mAdapter;
    private RecyclerView mRcyDeviceList;
    private RcyTerminalListAdapter mTerminalListAdapter;
    private TextView mTvServerCount;
    private BarChart mBarChart;
    private List<XAxis> mXAxisList = new ArrayList<>();
    private YAxis leftAxis;
    private YAxis rightAxis;
    private Legend legend;
    LinkedHashMap<String, List<Float>> chartDataMap = new LinkedHashMap<>();
    private BarChart mDeviceChart;
    private BarChart mChartResultPart;
    private RelativeLayout mRlPartUpPage;
    private RelativeLayout mRlPartNextPage;
    private TextView mTvPartPage;
    private RelativeLayout mRlProjectUpPage;
    private RelativeLayout mRlProjectNextPage;
    private TextView mTvProjectPage;
    private RelativeLayout mRlDeviceUpPage;
    private RelativeLayout mRlDeviceNextPage;
    private TextView mTvDevicePage;

    @Override
    public View getContentView() {

        View view = View.inflate(getActivity(), R.layout.fragment_result, null);

        initView(view);

        initData();

        initEvent();

        return view;
    }

    private void initEvent() {
        mTerminalListAdapter.setOnItemClickListener(this);
        mRlPartUpPage.setOnClickListener(this);
        mRlPartNextPage.setOnClickListener(this);
        mRlProjectUpPage.setOnClickListener(this);
        mRlProjectNextPage.setOnClickListener(this);
        mRlDeviceUpPage.setOnClickListener(this);
        mRlDeviceNextPage.setOnClickListener(this);

        mBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.e("daixinhong", "onValueSelected: " + e.getData());
            }

            @Override
            public void onNothingSelected() {

            }
        });

        mDeviceChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(getActivity(), TerminalResultDetailActivity.class, bundle);

//                Bundle bundle = new Bundle();
//                IntentUtils.startActivityForParms(getActivity(), DeviceChothListActivity.class, bundle);

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void initData() {

        mAdapter = new RcyCheckResultAdapter(getActivity(), null);
        mRcyProjectList.setAdapter(mAdapter);

        mTerminalListAdapter = new RcyTerminalListAdapter(getActivity(), null);
        mRcyDeviceList.setAdapter(mTerminalListAdapter);

        List<Integer> colors = Arrays.asList(
                UiUtils.findColorBuId(R.color.color_12507E), UiUtils.findColorBuId(R.color.clolor_CDC18D)
        );

        List<String> xValues = new ArrayList<>();
        List<Float> yValue1 = new ArrayList<>();
        List<Float> yValue2 = new ArrayList<>();

        List<ChartDevicesBean> valueList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ChartDevicesBean chartDevicesBean = new ChartDevicesBean();
            chartDevicesBean.setCount((i + 1) * 100);
            chartDevicesBean.setName("ip" + (4 + i));
            valueList.add(chartDevicesBean);
        }
        List<ChartDevicesBean> avgValueList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ChartDevicesBean chartDevicesBean = new ChartDevicesBean();
            chartDevicesBean.setCount((i + 1) * 3);
            avgValueList.add(chartDevicesBean);
        }

        for (ChartDevicesBean valueBean : valueList) {
            xValues.add(valueBean.getName());
            yValue1.add((float) valueBean.getCount());
        }
        for (ChartDevicesBean valueAvgBean : avgValueList) {
            yValue2.add((float) valueAvgBean.getCount());
        }
        chartDataMap.put(UiUtils.findStringBuId(R.string.good_product_count), yValue1);
        chartDataMap.put(UiUtils.findStringBuId(R.string.bad_product_count), yValue2);

        showBarChart(mBarChart, xValues, chartDataMap, colors, 0);

        showBarChart(mDeviceChart, xValues, chartDataMap, colors, 1);

        showBarChart(mChartResultPart, xValues, chartDataMap, colors, 2);

    }

    private void initView(View view) {

        mTvProjectCount = view.findViewById(R.id.tv_terminal_count);
        mTvServerCount = view.findViewById(R.id.tv_server_count);
        mTvType = view.findViewById(R.id.tv_type);
        mTvAllCount = view.findViewById(R.id.tv_all_count);
        mRcyProjectList = view.findViewById(R.id.rcy_project_list);
        mRcyDeviceList = view.findViewById(R.id.rcy_device_list);
        mBarChart = view.findViewById(R.id.chart_result);
        mDeviceChart = view.findViewById(R.id.chart_result_device);
        mChartResultPart = view.findViewById(R.id.chart_result_part);
        mRlPartUpPage = view.findViewById(R.id.rl_part_up_page);
        mRlPartNextPage = view.findViewById(R.id.rl_part_next_page);
        mTvPartPage = view.findViewById(R.id.tv_part_page);
        mRlProjectUpPage = view.findViewById(R.id.rl_project_up_page);
        mRlProjectNextPage = view.findViewById(R.id.rl_project_next_page);
        mTvProjectPage = view.findViewById(R.id.tv_project_page);
        mRlDeviceUpPage = view.findViewById(R.id.rl_device_up_page);
        mRlDeviceNextPage = view.findViewById(R.id.rl_device_next_page);
        mTvDevicePage = view.findViewById(R.id.tv_device_page);

        mRlDeviceUpPage.setVisibility(View.VISIBLE);
        mRlPartUpPage.setVisibility(View.VISIBLE);
        mRlProjectUpPage.setVisibility(View.VISIBLE);


        initBarChart(mBarChart);

        initBarChart(mDeviceChart);

        initBarChart(mChartResultPart);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyDeviceList.setLayoutManager(gridLayoutManager);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyProjectList.setLayoutManager(manager);
    }


    @Override
    public void setOnItemClickListener(View view, int position) {
        Bundle bundle = new Bundle();
        IntentUtils.startActivityForParms(getActivity(), TerminalResultDetailActivity.class, bundle);
    }

    private void initBarChart(BarChart barChart) {

        /***图表设置***/
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);

        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);
        //显示边框
        barChart.setDrawBorders(false);
        //设置动画效果
        barChart.animateY(1000, Easing.EasingOption.Linear);
        barChart.animateX(1000, Easing.EasingOption.Linear);

        /***XY轴的设置***/
        //X轴设置显示位置在底部
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        mXAxisList.add(xAxis);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        //保证Y轴从0开始，不然会上移一点
//        leftAxis.setAxisMinimum(0f);
//        rightAxis.setAxisMinimum(0f);

        /***折线图例 标签 设置***/
        legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);

        barChart.setDoubleTapToZoomEnabled(false);
        //禁止拖拽
        barChart.setDragEnabled(false);
        //X轴或Y轴禁止缩放
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setScaleEnabled(false);
        //禁止所有事件
        barChart.setTouchEnabled(true);

    }

    /**
     * @param xValues   X轴的值
     * @param dataLists LinkedHashMap<String, List<Float>>
     *                  key对应柱状图名字  List<Float> 对应每类柱状图的Y值
     * @param colors
     */
    public void showBarChart(BarChart barChart, final List<String> xValues, LinkedHashMap<String, List<Float>> dataLists,
                             @ColorRes List<Integer> colors, int index) {

        List<IBarDataSet> dataSets = new ArrayList<>();
        int currentPosition = 0;//用于柱状图颜色集合的index

        for (LinkedHashMap.Entry<String, List<Float>> entry : dataLists.entrySet()) {
            String name = entry.getKey();
            List<Float> yValueList = entry.getValue();

            List<BarEntry> entries = new ArrayList<>();

            for (int i = 0; i < yValueList.size(); i++) {
                entries.add(new BarEntry(i, yValueList.get(i), xValues.get(i)));
            }
            // 每一个BarDataSet代表一类柱状图
            BarDataSet barDataSet = new BarDataSet(entries, name);
            initBarDataSet(barDataSet, colors.get(currentPosition));
            dataSets.add(barDataSet);

            currentPosition++;
        }

        //X轴自定义值
        mXAxisList.get(index).setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int) value % xValues.size());
            }
        });
        //右侧Y轴自定义值
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) (value) + "";
            }
        });

        BarData data = new BarData(dataSets);
        barChart.setData(data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_part_up_page:

                break;
            case R.id.rl_part_next_page:

                break;
            case R.id.rl_project_up_page:

                break;
            case R.id.rl_project_next_page:

                break;
            case R.id.rl_device_up_page:

                break;
            case R.id.rl_device_next_page:

                break;
        }

    }
}
