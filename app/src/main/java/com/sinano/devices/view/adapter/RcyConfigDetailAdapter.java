package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinano.R;
import com.sinano.devices.model.ConfigListBean;

import java.util.List;

public class RcyConfigDetailAdapter extends RecyclerView.Adapter<RcyConfigDetailAdapter.ConfigDetailHolder> {

    private Context mContext;
    private List<ConfigListBean.DataBean.RecordsBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyConfigDetailAdapter(Context context, List<ConfigListBean.DataBean.RecordsBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ConfigDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConfigDetailHolder holder = new ConfigDetailHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_config_detail_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConfigDetailHolder holder, final int position) {

    }


    @Override
    public int getItemCount() {
        return mList == null ? 5 : mList.size();
    }


    class ConfigDetailHolder extends RecyclerView.ViewHolder {


        public ConfigDetailHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);


        }
    }

}
