package com.sinano.user.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.user.model.ChildUserBean;

import java.util.List;

public class RcyVersionInfoAdapter extends RecyclerView.Adapter<RcyVersionInfoAdapter.VersionHolder> {

    private Context mContext;
    private List<ChildUserBean.DataBean> mList;
    private RcyUserListAdapter.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(RcyUserListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyVersionInfoAdapter(Context context, List<ChildUserBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public VersionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VersionHolder holder = new VersionHolder(LayoutInflater.from(
                mContext).inflate(R.layout.version_info_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(VersionHolder holder, final int position) {
    }


    @Override
    public int getItemCount() {
        return mList == null ? 5 : mList.size();
    }


    class VersionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RcyUserListAdapter.OnItemClickListener mOnItemClickListener;
        private final TextView mTvInfo;


        public VersionHolder(View itemView, RcyUserListAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvInfo = itemView.findViewById(R.id.tv_info);

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
