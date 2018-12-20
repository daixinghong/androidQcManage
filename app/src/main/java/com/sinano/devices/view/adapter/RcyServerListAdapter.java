package com.sinano.devices.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.devices.view.activity.LocalServerDetailsActivity;
import com.sinano.utils.IntentUtils;

import java.util.List;

public class RcyServerListAdapter extends RecyclerView.Adapter<RcyServerListAdapter.ServerHolder> {


    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyServerListAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ServerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ServerHolder holder = new ServerHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_server_list_item_view, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ServerHolder holder, final int position) {

        RcyTerminalListAdapter adapter = new RcyTerminalListAdapter(mContext, null);
        holder.mRcyTerminal.setAdapter(adapter);
        holder.mLlServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(mContext, LocalServerDetailsActivity.class, bundle);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mList == null ? 3 : mList.size();
    }


    class ServerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvServerName;
        private final RecyclerView mRcyTerminal;
        private final LinearLayout mLlServer;

        public ServerHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvServerName = itemView.findViewById(R.id.tv_server_name);
            mRcyTerminal = itemView.findViewById(R.id.rcy_terminal_list);
            mLlServer = itemView.findViewById(R.id.ll_server);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 6) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            mRcyTerminal.setLayoutManager(gridLayoutManager);

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
