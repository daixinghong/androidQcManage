package com.sinano.result.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.devices.model.ChartDevicesBean;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClothBadTypeCountDetailActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.pic_chart)
    PieChart mPicChart;
    private List<ChartDevicesBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        mTvTitle.setText(UiUtils.findStringBuId(R.string.bad_cloth_detail));


        Description description = new Description();
        description.setText("");
        mPicChart.setDescription(description);
        mPicChart.setHoleRadius(0f);
        Legend l = mPicChart.getLegend();
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        mPicChart.setTransparentCircleRadius(0f);
        mPicChart.setDrawEntryLabels(false);

        List<PieEntry> listPie = new ArrayList<>();

        int counts = 0;
        for (int i = 1; i < 6; i++) {
            ChartDevicesBean chartDevicesBean = new ChartDevicesBean();
            chartDevicesBean.setName("水墨" + i);
            counts += i * 20;
            chartDevicesBean.setCount(i * 20);
            mList.add(chartDevicesBean);
        }


        for (int i = 0; i < mList.size(); i++) {
            double qty = mList.get(i).getCount();
            float number = (float) (qty / counts);
            float count = (number * 100);
            PieEntry pieEntry = new PieEntry(count, mList.get(i).getName() + "( " + ((int) qty) + " )");
            listPie.add(pieEntry);
        }
        setData(listPie);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_cloth_bad_type_count_detail;
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    private void setData(List<PieEntry> entries) {

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.color_F97319));
        colors.add(getResources().getColor(R.color.color_9BD504));
        colors.add(getResources().getColor(R.color.color_OBCA4E));
        colors.add(getResources().getColor(R.color.color_F45637));
        colors.add(getResources().getColor(R.color.color_34BCC6));

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        mPicChart.setData(data);
        mPicChart.highlightValues(null);
        //刷新
        mPicChart.invalidate();
    }
}
