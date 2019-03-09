package com.sinano.devices.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.devices.model.DeviceListBean;
import com.sinano.devices.view.activity.LocalServerDetailsActivity;
import com.sinano.devices.view.activity.TerminalDetailActivity;
import com.sinano.result.view.adapter.RcyTerminalListAdapter;
import com.sinano.utils.IntentUtils;

import java.util.List;

public class RcyServerListAdapter extends RecyclerView.Adapter<RcyServerListAdapter.ServerHolder> {


    private Context mContext;
    private List<DeviceListBean.DataBean.ServerBean> mList;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyServerListAdapter(Context context, List<DeviceListBean.DataBean.ServerBean> list) {
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

        holder.mTvServerName.setText(mList.get(position).getServerNo());

        RcyTerminalListAdapter adapter = new RcyTerminalListAdapter(mContext, mList.get(position).getPhoneDevice(), mList.get(position).getMac());
        holder.mRcyTerminal.setAdapter(adapter);
        holder.mLlServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(mContext, LocalServerDetailsActivity.class, bundle);

            }
        });

        if (mList.get(position).isOnline()) {
            holder.mIvServer.setImageResource(R.mipmap.server_test);
        } else {
            holder.mIvServer.setImageResource(R.mipmap.server_dark);
        }
        adapter.setOnItemClickListener(new RcyTerminalListAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(mContext, TerminalDetailActivity.class, bundle);
            }
        });


    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class ServerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvServerName;
        private final RecyclerView mRcyTerminal;
        private final LinearLayout mLlServer;
        private final ImageView mIvServer;

        public ServerHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvServerName = itemView.findViewById(R.id.tv_server_name);
            mRcyTerminal = itemView.findViewById(R.id.rcy_terminal_list);
            mLlServer = itemView.findViewById(R.id.ll_server);
            mIvServer = itemView.findViewById(R.id.iv_server);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2) {
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
