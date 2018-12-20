package com.sinano.devices.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.devices.view.activity.TerminalDetailActivity;
import com.sinano.utils.IntentUtils;

import java.util.List;

public class RcyNoQueryTerminalAdapter extends RecyclerView.Adapter<RcyNoQueryTerminalAdapter.TerminalHolder> implements View.OnClickListener {
    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyNoQueryTerminalAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

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

        holder.mLlTerminal.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_terminal:
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(mContext, TerminalDetailActivity.class, bundle);

                break;
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 6 : mList.size();
    }


    class TerminalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvServerName;
        private final LinearLayout mLlTerminal;


        public TerminalHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvServerName = itemView.findViewById(R.id.tv_server_name);
            mLlTerminal = itemView.findViewById(R.id.ll_terminal);

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
