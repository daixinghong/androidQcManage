package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinano.R;

import java.util.List;

public class RcyNoQueryServerAdapter extends RecyclerView.Adapter<RcyNoQueryServerAdapter.ServerHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyNoQueryServerAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ServerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ServerHolder holder = new ServerHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_no_query_server_item_view, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ServerHolder holder, final int position) {


    }


    @Override
    public int getItemCount() {
        return mList == null ? 8 : mList.size();
    }


    class ServerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvServerName;

        public ServerHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvServerName = itemView.findViewById(R.id.tv_server_name);

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
