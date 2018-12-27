package com.sinano.devices.view.fragment;

import android.graphics.Color;
import android.view.View;

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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.sinano.R;
import com.sinano.base.BaseFragment;
import com.sinano.devices.model.ChartDevicesBean;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;


public class SupperCompanyDeviceFragment extends BaseFragment {

    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private Legend legend;
    private List<ChartDevicesBean> mChartList = new ArrayList<>();
    private List<ChartDevicesBean> mCompanyList = new ArrayList<>();
    private BarChart mCompanyChart;
    private List<XAxis> mXAxisList = new ArrayList<>();

    @Override
    public View getContentView() {

        View view = View.inflate(getActivity(), R.layout.fragment_supper_company_device_view, null);

        init(view);

        return view;
    }

    private void init(View view) {
        mBarChart = view.findViewById(R.id.barChart);
        mCompanyChart = view.findViewById(R.id.company_barChart);

        initBarChart(mCompanyChart);
        initBarChart(mBarChart);

        for (int i = 0; i < 5; i++) {
            ChartDevicesBean chartDevicesBean = new ChartDevicesBean();
            chartDevicesBean.setCount((i + 1) * 10);
            switch (i) {
                case 0:
                    chartDevicesBean.setName("手机");
                    break;
                case 1:
                    chartDevicesBean.setName("服装");
                    break;
                case 2:
                    chartDevicesBean.setName("玩具");
                    break;
                case 3:
                    chartDevicesBean.setName("陶瓷");
                    break;
                case 4:
                    chartDevicesBean.setName("平板");
                    break;
            }
            mChartList.add(chartDevicesBean);
        }

        for (int i = 0; i < 5; i++) {
            ChartDevicesBean chartDevicesBean = new ChartDevicesBean();
            chartDevicesBean.setCount((i + 1) * 10);
            switch (i) {
                case 0:
                    chartDevicesBean.setName("硅纳");
                    break;
                case 1:
                    chartDevicesBean.setName("三木");
                    break;
                case 2:
                    chartDevicesBean.setName("爱帆");
                    break;
                case 3:
                    chartDevicesBean.setName("三美琦");
                    break;
                case 4:
                    chartDevicesBean.setName("advan");
                    break;
            }
            mCompanyList.add(chartDevicesBean);
        }

        showBarChart(mBarChart, mChartList, UiUtils.findStringBuId(R.string.sale_status), UiUtils.findColorBuId(R.color.color_12507E), 1);

        showBarChart(mCompanyChart, mCompanyList, UiUtils.findStringBuId(R.string.company_status), UiUtils.findColorBuId(R.color.color_12507E), 0);

    }

    private void showBarChart(BarChart barChart, List<ChartDevicesBean> chartList, String s, int colorBuId, int index) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < chartList.size(); i++) {
            /**
             * 此处还可传入Drawable对象 BarEntry(float x, float y, Drawable icon)
             * 即可设置柱状图顶部的 icon展示
             */
            BarEntry barEntry = new BarEntry(i, chartList.get(i).getCount());

            entries.add(barEntry);
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, s);
        initBarDataSet(barDataSet, colorBuId);

        //X轴自定义值
        XAxis xAxis = mXAxisList.get(index);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return chartList.get((int) value % chartList.size()).getName();
            }
        });
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return ((int) (value)) + "";
            }
        });

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
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

    }

}
