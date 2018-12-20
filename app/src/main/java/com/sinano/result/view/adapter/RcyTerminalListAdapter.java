package com.sinano.result.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinano.R;

import java.util.List;

public class RcyTerminalListAdapter extends RecyclerView.Adapter<RcyTerminalListAdapter.TerminalHolder> {


    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyTerminalListAdapter(Context context, List<String> list) {
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
//            holder.mTvTerminalName

    }


    @Override
    public int getItemCount() {
        return mList == null ? 8 : mList.size();
    }


    class TerminalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvTerminalName;


        public TerminalHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvTerminalName = itemView.findViewById(R.id.tv_terminal_name);
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
