package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinano.R;

import java.util.List;

public class RcySupperCompanyAdapter extends RecyclerView.Adapter<RcySupperCompanyAdapter.CompanyHolder> {
    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcySupperCompanyAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public CompanyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CompanyHolder holder = new CompanyHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_supper_company_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(CompanyHolder holder, final int position) {


    }


    @Override
    public int getItemCount() {
        return mList == null ? 3 : mList.size();
    }


    class CompanyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;

        public CompanyHolder(View itemView, OnItemClickListener onItemClickListener) {
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
