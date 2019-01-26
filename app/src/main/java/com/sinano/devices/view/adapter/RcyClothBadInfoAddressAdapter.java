package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.result.model.ClothContentBean;

import java.util.List;

public class RcyClothBadInfoAddressAdapter extends RecyclerView.Adapter<RcyClothBadInfoAddressAdapter.ClothBadHolder> {


    private Context mContext;
    private List<ClothContentBean.BadInfoBean> mList;
    private OnItemClickListener mOnItemClickListener;
    private boolean mStatus;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyClothBadInfoAddressAdapter(Context context, List<ClothContentBean.BadInfoBean> list, boolean status) {
        this.mContext = context;
        this.mList = list;
        this.mStatus = status;

    }

    @Override
    public ClothBadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClothBadHolder holder = new ClothBadHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_cloth_bad_address_info_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClothBadHolder holder, final int position) {
        holder.mTvName.setText(mList.get(position).getDesc());
        holder.mTvLeft.setText(mList.get(position).getLocation().getX());
        holder.mTvTop.setText(mList.get(position).getLocation().getY());
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class ClothBadHolder extends RecyclerView.ViewHolder {


        private final TextView mTvName;
        private final TextView mTvLeft;
        private final TextView mTvTop;

        public ClothBadHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvLeft = itemView.findViewById(R.id.tv_left);
            mTvTop = itemView.findViewById(R.id.tv_top);

        }
    }

}
