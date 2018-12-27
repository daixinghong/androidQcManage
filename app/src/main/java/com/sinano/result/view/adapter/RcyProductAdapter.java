package com.sinano.result.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinano.R;

import java.util.List;

public class RcyProductAdapter extends RecyclerView.Adapter<RcyProductAdapter.ProductHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyProductAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductHolder holder = new ProductHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_product_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, final int position) {


    }


    @Override
    public int getItemCount() {
        return mList == null ? 7 : mList.size();
    }


    class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;


        public ProductHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

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
