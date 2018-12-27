package com.sinano.result.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinano.R;

import java.util.List;

public class RcyClothListAdapter extends RecyclerView.Adapter<RcyClothListAdapter.ClothHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyClothListAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ClothHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClothHolder holder = new ClothHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_cloth_list_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClothHolder holder, final int position) {


    }


    @Override
    public int getItemCount() {
        return mList == null ? 7 : mList.size();
    }


    class ClothHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvClothNumber;
        private final TextView mTvPotNumber;
        private final TextView mTvLength;
        private final TextView mTvBadCount;
        private final TextView mTvOpreateUser;


        public ClothHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvClothNumber = itemView.findViewById(R.id.tv_cloth_number);
            mTvPotNumber = itemView.findViewById(R.id.tv_pot_number);
            mTvLength = itemView.findViewById(R.id.tv_length);
            mTvBadCount = itemView.findViewById(R.id.tv_bad_count);
            mTvOpreateUser = itemView.findViewById(R.id.tv_operate_user);

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
