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
import com.sinano.devices.model.ConfigListBean;
import com.sinano.devices.view.activity.ConfigureDetailManageActivity;
import com.sinano.utils.IntentUtils;

import java.util.List;

public class RcyConfigureAdapter extends RecyclerView.Adapter<RcyConfigureAdapter.ConfigureHolder> {


    private Context mContext;
    private List<ConfigListBean.DataBean.RecordsBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyConfigureAdapter(Context context, List<ConfigListBean.DataBean.RecordsBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ConfigureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConfigureHolder holder = new ConfigureHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_configure_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConfigureHolder holder, final int position) {
        holder.mTvName.setText(mList.get(position).getName());
        holder.mTvVersion.setText(mList.get(position).getCreateTime());

        holder.mRlDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(mContext, ConfigureDetailManageActivity.class, bundle);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class ConfigureHolder extends RecyclerView.ViewHolder {


        private final TextView mTvName;
        private final TextView mTvType;
        private final TextView mTvVersion;
        private final RelativeLayout mRlDetail;

        public ConfigureHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvType = itemView.findViewById(R.id.tv_type);
            mTvVersion = itemView.findViewById(R.id.tv_version);
            mRlDetail = itemView.findViewById(R.id.rl_opreate);

        }
    }


}
