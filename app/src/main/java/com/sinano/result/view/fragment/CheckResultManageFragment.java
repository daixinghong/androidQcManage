package com.sinano.result.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import com.sinano.devices.view.activity.DeviceResultForConfigActivity;
import com.sinano.result.model.ChartDataBean;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.ClothDescForMd5Bean;
import com.sinano.result.model.DeviceResultForConfigBean;
import com.sinano.result.model.ResultBean;
import com.sinano.result.presenter.ResultInterface;
import com.sinano.result.presenter.ResultPresenter;
import com.sinano.result.view.activity.DeviceChothListActivity;
import com.sinano.result.view.activity.TerminalResultDetailActivity;
import com.sinano.result.view.adapter.RcyCheckResultAdapter;
import com.sinano.result.view.adapter.RcyTerminalListAdapter;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheckResultManageFragment extends BaseFragment implements RcyTerminalListAdapter.OnItemClickListener, View.OnClickListener, ResultInterface {

    private TextView mTvAllCount;
    private RecyclerView mRcyProjectList;
    private RcyCheckResultAdapter mAdapter;
    private RecyclerView mRcyDeviceList;
    private RcyTerminalListAdapter mTerminalListAdapter;
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
    private TextView mTvDeviceCount;
    private TextView mTvGoodCount;
    private TextView mTvBadCount;

    private List<ResultBean.DataBean.ConfigBean> mConfigList = new ArrayList<>();
    private List<ResultBean.DataBean.DeviceBean> mDeviceList = new ArrayList<>();

    private Map<Integer, List<ChartDataBean>> mPageConfigNameMap = new HashMap<>();
    private Map<Integer, LinkedHashMap<String, List<Float>>> mPageConfigLinkMap = new HashMap<>();

    private Map<Integer, List<ChartDataBean>> mPageDeviceNameMap = new HashMap<>();
    private Map<Integer, LinkedHashMap<String, List<Float>>> mPageDeviceLinkMap = new HashMap<>();

    private List<Integer> colors = Arrays.asList(
            UiUtils.findColorBuId(R.color.color_12507E), UiUtils.findColorBuId(R.color.clolor_CDC18D)
    );

    private int mConfigPageIndex;
    private int mDevicePageIndex;
    private int mapIndex;
    private int deviceMapIndex;
    private ResultPresenter mPresenter;
    private String mConfigId;
    private LinearLayout mLlDeviceContainer;
    private LinearLayout mLlConfigCheck;


    @Override
    public View getContentView() {

        View view = View.inflate(getActivity(), R.layout.fragment_result, null);

        initView(view);

        initData();

        initEvent();

        return view;
    }

    private void initEvent() {
        mRlPartUpPage.setOnClickListener(this);
        mRlPartNextPage.setOnClickListener(this);
        mRlProjectUpPage.setOnClickListener(this);
        mRlProjectNextPage.setOnClickListener(this);
        mRlDeviceUpPage.setOnClickListener(this);
        mRlDeviceNextPage.setOnClickListener(this);

        mBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                mConfigId = ((ChartDataBean) e.getData()).getConfigId();
                Bundle bundle = new Bundle();
                bundle.putString("configId", mConfigId);
                IntentUtils.startActivityForParms(getActivity(), DeviceResultForConfigActivity.class, bundle);

            }

            @Override
            public void onNothingSelected() {

            }
        });

        mDeviceChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                String mac = ((ChartDataBean) e.getData()).getConfigId();
                String type = ((ChartDataBean) e.getData()).getType();
                Bundle bundle = new Bundle();
                bundle.putString("mac", mac);

                if (type == null) {
                    IntentUtils.startActivityForParms(getActivity(), TerminalResultDetailActivity.class, bundle);
                    return;
                }
                if (type.equals("phone")) {
                    IntentUtils.startActivityForParms(getActivity(), TerminalResultDetailActivity.class, bundle);
                } else {
                    IntentUtils.startActivityForParms(getActivity(), DeviceChothListActivity.class, bundle);
                }

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void initData() {

        mAdapter = new RcyCheckResultAdapter(getActivity(), null);
        mRcyProjectList.setAdapter(mAdapter);

//        mTerminalListAdapter = new RcyTerminalListAdapter(getActivity(), null);
//        mRcyDeviceList.setAdapter(mTerminalListAdapter);


        initBarChart(mBarChart);
        initBarChart(mDeviceChart);

        mPresenter = new ResultPresenter(this);
        mPresenter.getAllCheckResult(getActivity());

    }

    private void initView(View view) {

        mTvDeviceCount = view.findViewById(R.id.tv_device_count);
        mTvGoodCount = view.findViewById(R.id.tv_good_count);
        mTvBadCount = view.findViewById(R.id.tv_bad_count);
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
        mLlDeviceContainer = view.findViewById(R.id.ll_device_container);
        mLlConfigCheck = view.findViewById(R.id.ll_config_check);

        mRlDeviceUpPage.setVisibility(View.VISIBLE);
        mRlPartUpPage.setVisibility(View.VISIBLE);
        mRlProjectUpPage.setVisibility(View.VISIBLE);


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
    public void showBarChart(BarChart barChart, final List<ChartDataBean> xValues, LinkedHashMap<String, List<Float>> dataLists,
                             @ColorRes List<Integer> colors, int index) throws Exception {

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
                return xValues.get((int) value % xValues.size()).getName();
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

        //设置柱状图宽度
        data.setBarWidth(0.5f);

        barChart.setData(data);
        barChart.invalidate();
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.rl_part_up_page:

                    break;
                case R.id.rl_part_next_page:

                    break;
                case R.id.rl_project_up_page:
                    if (mConfigPageIndex == 0) {
                        ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.is_first_page));
                        return;
                    } else {
                        mConfigPageIndex--;
                        showBarChart(mBarChart, mPageConfigNameMap.get(mConfigPageIndex), mPageConfigLinkMap.get(mConfigPageIndex), colors, 0);
                        mTvProjectPage.setText(mConfigPageIndex + 1 + "/" + mPageConfigLinkMap.size());
                    }

                    break;
                case R.id.rl_project_next_page:
                    if (mConfigPageIndex == mPageConfigLinkMap.size() - 1) {
                        ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.is_last_page));
                        return;
                    } else {
                        mConfigPageIndex++;
                        showBarChart(mBarChart, mPageConfigNameMap.get(mConfigPageIndex), mPageConfigLinkMap.get(mConfigPageIndex), colors, 0);
                        mTvProjectPage.setText(mConfigPageIndex + 1 + "/" + mPageConfigLinkMap.size());
                    }

                    break;
                case R.id.rl_device_up_page:
                    if (mDevicePageIndex == 0) {
                        ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.is_first_page));
                        return;
                    } else {
                        mDevicePageIndex--;
                        showBarChart(mDeviceChart, mPageDeviceNameMap.get(mDevicePageIndex), mPageDeviceLinkMap.get(mDevicePageIndex), colors, 1);
                        mTvDevicePage.setText(mDevicePageIndex + 1 + "/" + mPageDeviceNameMap.size());
                    }

                    break;
                case R.id.rl_device_next_page:
                    if (mDevicePageIndex == mPageDeviceNameMap.size() - 1) {
                        ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.is_last_page));
                        return;
                    } else {
                        mDevicePageIndex++;
                        showBarChart(mDeviceChart, mPageDeviceNameMap.get(mDevicePageIndex), mPageDeviceLinkMap.get(mDevicePageIndex), colors, 1);
                        mTvDevicePage.setText(mDevicePageIndex + 1 + "/" + mPageDeviceNameMap.size());

                    }
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "onClick: ");
        }


    }

    @Override
    public void getResultSuccess(ResultBean resultBean) {

        switch (resultBean.getCode()) {
            case 200:

                long l = System.currentTimeMillis();

                Log.e(TAG, "getResultSuccess: " + l);

                ResultBean.DataBean data = resultBean.getData();
                List<ResultBean.DataBean.ConfigBean> config = data.getConfig();
                List<ResultBean.DataBean.DeviceBean> device = data.getDevice();
                mConfigList.clear();
                mConfigList.addAll(config);

                if (mConfigList.size() == 0) {
                    mLlConfigCheck.setVisibility(View.GONE);
                }


                mDeviceList.clear();
                mDeviceList.addAll(device);

                if (mDeviceList.size() == 0) {
                    mLlDeviceContainer.setVisibility(View.GONE);
                }

                mBarChart.animateY(1000, Easing.EasingOption.Linear);
                mBarChart.animateX(1000, Easing.EasingOption.Linear);

                mDeviceChart.animateY(1000, Easing.EasingOption.Linear);
                mDeviceChart.animateX(1000, Easing.EasingOption.Linear);

                int goodCount = 0;
                int badCount = 0;

                List<ChartDataBean> xValues = new ArrayList<>();
                List<Float> yValue1 = new ArrayList<>();
                List<Float> yValue2 = new ArrayList<>();

                int configIndex = 0;
                mapIndex = 0;
                for (int i = 0; i < mConfigList.size(); i++) {
                    goodCount += mConfigList.get(i).getYes();
                    badCount += mConfigList.get(i).getNo();
                    ChartDataBean chartDataBean = new ChartDataBean();
                    chartDataBean.setName(mConfigList.get(i).getName());
                    chartDataBean.setConfigId(mConfigList.get(i).getCid());
                    xValues.add(chartDataBean);
                    yValue1.add((float) mConfigList.get(i).getYes());
                    yValue2.add((float) mConfigList.get(i).getNo());

                    if (i == mConfigList.size() - 1 && i < 5) {
                        LinkedHashMap<String, List<Float>> mConfigDataMap = new LinkedHashMap<>();
                        mPageConfigNameMap.put(mapIndex, xValues);
                        mConfigDataMap.put(UiUtils.findStringBuId(R.string.good_product_count), yValue1);
                        mConfigDataMap.put(UiUtils.findStringBuId(R.string.bad_product_count), yValue2);
                        mPageConfigLinkMap.put(mapIndex, mConfigDataMap);
                        break;
                    }

                    configIndex++;
                    if (configIndex == 5 || i == mConfigList.size() - 1) {
                        LinkedHashMap<String, List<Float>> mConfigDataMap = new LinkedHashMap<>();
                        mPageConfigNameMap.put(mapIndex, xValues);
                        mConfigDataMap.put(UiUtils.findStringBuId(R.string.good_product_count), yValue1);
                        mConfigDataMap.put(UiUtils.findStringBuId(R.string.bad_product_count), yValue2);
                        mPageConfigLinkMap.put(mapIndex, mConfigDataMap);
                        mapIndex++;
                        xValues = new ArrayList<>();
                        yValue1 = new ArrayList<>();
                        yValue2 = new ArrayList<>();
                        configIndex = 0;
                    }
                }

                mTvProjectPage.setText("1/" + mPageConfigLinkMap.size());


                List<ChartDataBean> deviceValues = new ArrayList<>();
                List<Float> DeviceValue1 = new ArrayList<>();
                List<Float> DeviceValue2 = new ArrayList<>();


                int deviceIndex = 0;
                deviceMapIndex = 0;
                for (int i = 0; i < mDeviceList.size(); i++) {
                    goodCount += mDeviceList.get(i).getYes();
                    badCount += mDeviceList.get(i).getNo();

                    ChartDataBean chartDataBean = new ChartDataBean();
                    chartDataBean.setName(mDeviceList.get(i).getName());
                    chartDataBean.setConfigId(mDeviceList.get(i).getMac());
                    chartDataBean.setType(mDeviceList.get(i).getType());
                    deviceValues.add(chartDataBean);


                    DeviceValue1.add((float) mDeviceList.get(i).getYes());
                    DeviceValue2.add((float) mDeviceList.get(i).getNo());

                    if (i == mDeviceList.size() - 1 && i < 5) {
                        LinkedHashMap<String, List<Float>> mDeviceDataMap = new LinkedHashMap<>();
                        mPageDeviceNameMap.put(deviceMapIndex, deviceValues);
                        mDeviceDataMap.put(UiUtils.findStringBuId(R.string.good_product_count), DeviceValue1);
                        mDeviceDataMap.put(UiUtils.findStringBuId(R.string.bad_product_count), DeviceValue2);
                        mPageDeviceLinkMap.put(deviceMapIndex, mDeviceDataMap);
                        break;
                    }
                    deviceIndex++;
                    if (deviceIndex == 5 || i == mDeviceList.size() - 1) {
                        LinkedHashMap<String, List<Float>> mDeviceDataMap = new LinkedHashMap<>();
                        mPageDeviceNameMap.put(deviceMapIndex, deviceValues);
                        mDeviceDataMap.put(UiUtils.findStringBuId(R.string.good_product_count), DeviceValue1);
                        mDeviceDataMap.put(UiUtils.findStringBuId(R.string.bad_product_count), DeviceValue2);
                        mPageDeviceLinkMap.put(deviceMapIndex, mDeviceDataMap);
                        deviceMapIndex++;
                        deviceValues = new ArrayList<>();
                        DeviceValue1 = new ArrayList<>();
                        DeviceValue2 = new ArrayList<>();
                        deviceIndex = 0;
                    }
                }
                mTvDevicePage.setText("1/" + mPageDeviceLinkMap.size());

                mTvDeviceCount.setText(mDeviceList.size() + "");
                mTvBadCount.setText(badCount + "");
                mTvGoodCount.setText(goodCount + "");
                mTvAllCount.setText(badCount + goodCount + "");

                long l1 = System.currentTimeMillis();

                Log.e(TAG, "getResultSuccess: " + (l1 - l));
                try {
                    if (mPageConfigNameMap.size() != 0 && mPageConfigLinkMap.size() != 0) {
                        showBarChart(mBarChart, mPageConfigNameMap.get(0), mPageConfigLinkMap.get(0), colors, 0);
                    }

                    if (mPageDeviceNameMap.size() != 0 && mPageDeviceLinkMap.size() != 0) {
                        showBarChart(mDeviceChart, mPageDeviceNameMap.get(0), mPageDeviceLinkMap.get(0), colors, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            default:
                ToastUtils.showTextToast(resultBean.getMsg());
                break;
        }
    }

    @Override
    public void getResultForConfigSuccess(DeviceResultForConfigBean resultBean) {

    }

    @Override
    public void getResultForDeviceSuccess(DeviceResultForConfigBean resultBean) {

    }

    @Override
    public void getCheckResultDetailSuccess(CheckResultDetailBean checkResultDetailBean) {

    }

    @Override
    public void getClothResultDetailSuccess(ClothDescForMd5Bean clothDescForMd5Bean) {

    }

    @Override
    public String getConfigId() {

        return mConfigId;
    }

    @Override
    public Map<String, Object> getParms() {
        return null;
    }

    @Override
    public void onNetChange(int netMobile) {
        super.onNetChange(netMobile);
//        switch (netMobile) {
//            case 1:
//                mPresenter.getAllCheckResult(getActivity());
//                break;
//            case 0:
//                mPresenter.getAllCheckResult(getActivity());
//                break;
//        }
    }

}
