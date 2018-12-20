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
import com.sinano.devices.view.activity.ModelDetailActivity;
import com.sinano.utils.IntentUtils;

import java.util.List;

public class RcyModelListAdapter extends RecyclerView.Adapter<RcyModelListAdapter.ServerHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyModelListAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ServerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ServerHolder holder = new ServerHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_local_server_detail_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ServerHolder holder, final int position) {
        holder.mRlDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(mContext, ModelDetailActivity.class, bundle);
            }
        });


    }


    @Override
    public int getItemCount() {
        return mList == null ? 8 : mList.size();
    }


    class ServerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTvNewVersion;
        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvModelName;
        private final TextView mTvVersion;
        private final RelativeLayout mRlDetail;


        public ServerHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mTvModelName = itemView.findViewById(R.id.tv_model_name);
            mTvVersion = itemView.findViewById(R.id.tv_current_model_version);
            mTvNewVersion = itemView.findViewById(R.id.tv_new_model_version);
            mRlDetail = itemView.findViewById(R.id.rl_detail);

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
