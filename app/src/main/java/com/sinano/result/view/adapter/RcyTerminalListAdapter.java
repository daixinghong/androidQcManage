package com.sinano.result.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.devices.model.DeviceListBean;

import java.util.List;

public class RcyTerminalListAdapter extends RecyclerView.Adapter<RcyTerminalListAdapter.TerminalHolder> {


    private Context mContext;
    private List<DeviceListBean.DataBean.ServerBean.PhoneDevice> mList;
    private OnItemClickListener mOnItemClickListener;
    private String mac;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyTerminalListAdapter(Context context, List<DeviceListBean.DataBean.ServerBean.PhoneDevice> list, String mac) {
        this.mContext = context;
        this.mList = list;
        this.mac = mac;

    }

    @Override
    public TerminalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TerminalHolder holder = new TerminalHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_terminal_item_view, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(TerminalHolder holder, final int position) {

        holder.mTvTerminalName.setText(mList.get(position).getDeviceName());
        if (mList.get(position).isOnline()) {
            holder.mIvTerimal.setImageResource(R.mipmap.child_plant);
        } else {
            holder.mIvTerimal.setImageResource(R.mipmap.plant_dark);
        }



    }


    @Override
    public int getItemCount() {
        return mList == null ? 8 : mList.size();
    }


    class TerminalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvTerminalName;
        private final ImageView mIvTerimal;


        public TerminalHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvTerminalName = itemView.findViewById(R.id.tv_terminal_name);
            mIvTerimal = itemView.findViewById(R.id.iv_terimal);

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
