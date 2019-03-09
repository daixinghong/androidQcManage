package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.devices.model.DeviceListBean;
import com.sinano.utils.UiUtils;

import java.util.List;

public class RcyTerminalListAdapter extends RecyclerView.Adapter<RcyTerminalListAdapter.TerminalHolder> {
    private Context mContext;
    private List<DeviceListBean.DataBean.ClothDeviceBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyTerminalListAdapter(Context context, List<DeviceListBean.DataBean.ClothDeviceBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public TerminalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TerminalHolder holder = new TerminalHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_cloth_device_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(TerminalHolder holder, final int position) {

        if (mList.get(position).isOnline()) {
            holder.mIvTerminal.setImageResource(R.mipmap.child_plant);
            holder.mTvDeviceStatus.setText(UiUtils.findStringBuId(R.string.device_online));
        } else {
            holder.mIvTerminal.setImageResource(R.mipmap.plant_dark);
            holder.mTvDeviceStatus.setText(UiUtils.findStringBuId(R.string.device_upline));
        }
        holder.mTvTerminalName.setText(mList.get(position).getDeviceName());
        holder.mTvMac.setText(mList.get(position).getMac());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class TerminalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTvTerminalName;
        private OnItemClickListener mOnItemClickListener;
        private final ImageView mIvTerminal;
        private final TextView mTvMac;
        private final TextView mTvDeviceStatus;


        public TerminalHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvTerminalName = itemView.findViewById(R.id.tv_device_name);
            mIvTerminal = itemView.findViewById(R.id.iv_device);
            mTvMac = itemView.findViewById(R.id.tv_mac);
            mTvDeviceStatus = itemView.findViewById(R.id.tv_device_status);
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
