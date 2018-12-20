package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinano.R;

import java.util.List;

public class RcyConfigureVersionAdapter extends RecyclerView.Adapter<RcyConfigureVersionAdapter.ConfigureHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyConfigureVersionAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ConfigureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConfigureHolder holder = new ConfigureHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_configure_version_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConfigureHolder holder, final int position) {

        holder.mTvSeq.setText(position + 1 + "");

    }


    @Override
    public int getItemCount() {
        return mList == null ? 8 : mList.size();
    }


    class ConfigureHolder extends RecyclerView.ViewHolder {


        private final TextView mTvSeq;

        public ConfigureHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mTvSeq = itemView.findViewById(R.id.tv_seq);

        }
    }

}
