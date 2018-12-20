package com.sinano.result.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinano.R;

import java.util.List;

public class RcyCheckResultAdapter extends RecyclerView.Adapter<RcyCheckResultAdapter.CheckResultHolder> {


    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyCheckResultAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public CheckResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckResultHolder holder = new CheckResultHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_check_result_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(CheckResultHolder holder, final int position) {


    }


    @Override
    public int getItemCount() {
        return mList == null ? 5 : mList.size();
    }


    class CheckResultHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvProjectName;
        private final TextView mTvGoodProductCount;
        private final TextView mTvBadCount;
        private final TextView mTvAllCount;

        public CheckResultHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvProjectName = itemView.findViewById(R.id.tv_project_name);
            mTvGoodProductCount = itemView.findViewById(R.id.tv_good_product_count);
            mTvBadCount = itemView.findViewById(R.id.tv_bad_count);
            mTvAllCount = itemView.findViewById(R.id.tv_all_count);

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
