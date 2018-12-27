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

public class RcyUserListAdapter extends RecyclerView.Adapter<RcyUserListAdapter.UserHolder> {


    private Context mContext;
    private List<ChildUserBean.DataBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyUserListAdapter(Context context, List<ChildUserBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserHolder holder = new UserHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_user_manage_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, final int position) {
        holder.mTvPhone.setText(mList.get(position).getPhone());
        holder.mTvUser.setText(mList.get(position).getNickname());
        holder.mTvUserName.setText(mList.get(position).getUsername());
    }


    @Override
    public int getItemCount() {
        return mList == null ? 5 : mList.size();
    }


    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvUser;
        private final TextView mTvUserName;
        private final TextView mTvPhone;

        public UserHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvUser = itemView.findViewById(R.id.tv_user);
            mTvUserName = itemView.findViewById(R.id.tv_user_name);
            mTvPhone = itemView.findViewById(R.id.tv_phone);

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
