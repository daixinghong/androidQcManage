package com.sinano.result.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.sinano.result.model.DeviceResultForConfigBean;
import com.sinano.result.view.activity.ProductListActivity;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class RcyChartListAdapter extends RecyclerView.Adapter<RcyChartListAdapter.ChartHolder> {


    private Context mContext;
    private List<DeviceResultForConfigBean.DataBean> mList;
    private OnItemClickListener mOnItemClickListener;
    private boolean mStatus;
    private String mMac;
    private String mConfigId;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyChartListAdapter(Context context, List<DeviceResultForConfigBean.DataBean> list, boolean status, String mac, String configId) {
        this.mContext = context;
        this.mList = list;
        this.mStatus = status;
        this.mMac = mac;
        this.mConfigId = configId;

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

        if (mStatus) {
            holder.mTvTitle.setText(UiUtils.findStringBuId(R.string.config_name));
            holder.mTvName.setText(mList.get(position).getConfig_name());
        } else {
            holder.mTvTitle.setText(UiUtils.findStringBuId(R.string.device_seq));
            holder.mTvName.setText(mList.get(position).getDevice_name());
        }

        List<PieEntry> listPie = new ArrayList<>();

        int counts = 0;
        counts = mList.get(position).getNo() + mList.get(position).getYes();


        double qty = mList.get(position).getYes();
        float number = (float) (qty / counts);
        float count = (number * 100);
        PieEntry pieEntry = new PieEntry(count, "良品( " + ((int) qty) + " )", position);
        listPie.add(pieEntry);

        double qty1 = mList.get(position).getNo();
        float number1 = (float) (qty1 / counts);
        float count1 = (number1 * 100);

        PieEntry pieEntry1 = new PieEntry(count1, "不良品( " + ((int) qty1) + " )", position);
        listPie.add(pieEntry1);

        setData(listPie, holder);

        holder.mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Bundle bundle = new Bundle();
                if (mMac == null) {
                    bundle.putString("mac", mList.get((int) e.getData()).getMac());
                } else {
                    bundle.putString("mac", mMac);
                }
                if (mConfigId == null) {
                    bundle.putString("configId", mList.get((int) e.getData()).getConfig_id());
                } else {
                    bundle.putString("configId", mConfigId);
                }
                IntentUtils.startActivityForParms(mContext, ProductListActivity.class, bundle);
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
        colors.add(mContext.getResources().getColor(R.color.color_9BD504));
        colors.add(mContext.getResources().getColor(R.color.color_F45637));

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
        private final TextView mTvTitle;
        private final TextView mTvName;

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
            mTvTitle = itemView.findViewById(R.id.tv_titles);
            mTvName = itemView.findViewById(R.id.tv_name);


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
