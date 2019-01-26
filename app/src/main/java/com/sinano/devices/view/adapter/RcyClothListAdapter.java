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

import java.util.List;

public class RcyClothListAdapter extends RecyclerView.Adapter<RcyClothListAdapter.ClothHolder> {

    private Context mContext;
    private List<DeviceListBean.DataBean.ClothDeviceBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyClothListAdapter(Context context, List<DeviceListBean.DataBean.ClothDeviceBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ClothHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClothHolder holder = new ClothHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_cloth_device_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClothHolder holder, final int position) {
        holder.setIsRecyclable(false);
        if (mList.get(position).isOnline()) {
            holder.mIvDevice.setImageResource(R.mipmap.child_plant);
        } else {
            holder.mIvDevice.setImageResource(R.mipmap.plant_dark);
        }
        if (mList.get(position).getDeviceName() != null)
            holder.mTvDeviceName.setText(mList.get(position).getDeviceName());

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class ClothHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvDeviceName;
        private final ImageView mIvDevice;


        public ClothHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvDeviceName = itemView.findViewById(R.id.tv_device_name);
            mIvDevice = itemView.findViewById(R.id.iv_device);

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
