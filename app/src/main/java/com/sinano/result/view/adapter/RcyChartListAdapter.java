package com.sinano.result.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.sinano.R;
import com.sinano.devices.model.ChartDevicesBean;
import com.sinano.result.view.activity.ProductListActivity;
import com.sinano.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

public class RcyChartListAdapter extends RecyclerView.Adapter<RcyChartListAdapter.ChartHolder> {


    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyChartListAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ChartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChartHolder holder = new ChartHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_chart_list_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChartHolder holder, final int position) {

        holder.setIsRecyclable(false);
        List<PieEntry> listPie = new ArrayList<>();
        List<ChartDevicesBean> mLists = new ArrayList<>();

        int counts = 0;
        for (int i = 1; i < 3; i++) {
            ChartDevicesBean chartDevicesBean = new ChartDevicesBean();
            if (i == 1) {
                chartDevicesBean.setName("不良品");
            } else {
                chartDevicesBean.setName("良品");
            }
            counts += i * 200;
            chartDevicesBean.setCount(i * 200);
            mLists.add(chartDevicesBean);
        }

        for (int i = 0; i < mLists.size(); i++) {
            double qty = mLists.get(i).getCount();
            float number = (float) (qty / counts);
            float count = (number * 100);
            PieEntry pieEntry = new PieEntry(count, mLists.get(i).getName() + "( " + ((int) qty) + " )", position);
            listPie.add(pieEntry);
        }
        setData(listPie, holder);

        holder.mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(mContext, ProductListActivity.class, bundle);
                Log.e("vivi", "onValueSelected: " + e.getData());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }


    @Override
    public int getItemCount() {
        return mList == null ? 5 : mList.size();
    }

    private void setData(List<PieEntry> entries, ChartHolder holder) {

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(mContext.getResources().getColor(R.color.color_F97319));
        colors.add(mContext.getResources().getColor(R.color.color_9BD504));
        colors.add(mContext.getResources().getColor(R.color.color_OBCA4E));
        colors.add(mContext.getResources().getColor(R.color.color_F45637));
        colors.add(mContext.getResources().getColor(R.color.color_34BCC6));

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        holder.mPieChart.setData(data);
        holder.mPieChart.highlightValues(null);
        //刷新
        holder.mPieChart.invalidate();
    }


    class ChartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final PieChart mPieChart;

        public ChartHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mPieChart = itemView.findViewById(R.id.pic_chart);

            Description description = new Description();
            description.setText("");
            mPieChart.setDescription(description);
            mPieChart.setHoleRadius(0f);
            Legend l = mPieChart.getLegend();
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            mPieChart.setTransparentCircleRadius(0f);
            mPieChart.setDrawEntryLabels(false);


            this.mOnItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null)
                mOnItemClickListener.setOnItemClickListener(view, getPosition());
        }
    }


}
