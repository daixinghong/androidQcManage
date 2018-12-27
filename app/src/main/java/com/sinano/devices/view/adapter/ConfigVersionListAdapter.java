package com.sinano.devices.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.devices.model.ConfigDetailBean;
import com.sinano.devices.view.activity.ConfigDetailActivity;
import com.sinano.utils.IntentUtils;

import java.util.List;

public class ConfigVersionListAdapter extends RecyclerView.Adapter<ConfigVersionListAdapter.ConfigVersionHolder> {

    private Context mContext;
    private List<ConfigDetailBean.DataBean> mList;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ConfigVersionListAdapter(Context context, List<ConfigDetailBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ConfigVersionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConfigVersionHolder holder = new ConfigVersionHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_config_version_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConfigVersionHolder holder, final int position) {
        holder.mTvVersion.setText(mList.get(position).getVersion());
        holder.mTvSeq.setText(position + 1 + "");

        holder.mRlDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(mContext, ConfigDetailActivity.class, bundle);
            }
        });
        holder.mRlSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 5 : mList.size();
    }


    class ConfigVersionHolder extends RecyclerView.ViewHolder {


        private final TextView mTvSeq;
        private final TextView mTvVersion;
        private final RelativeLayout mRlDetail;
        private final RelativeLayout mRlSyn;

        public ConfigVersionHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mTvSeq = itemView.findViewById(R.id.tv_seq);
            mTvVersion = itemView.findViewById(R.id.tv_version);
            mRlDetail = itemView.findViewById(R.id.rl_details);
            mRlSyn = itemView.findViewById(R.id.rl_use);

        }
    }

}
