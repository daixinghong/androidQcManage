package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sinano.R;

import java.util.List;

public class RcyLocalServerDownloadAdapter extends RecyclerView.Adapter<RcyLocalServerDownloadAdapter.ModelHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyLocalServerDownloadAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ModelHolder holder = new ModelHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_local_model_download_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ModelHolder holder, final int position) {


    }


    @Override
    public int getItemCount() {
        return mList == null ? 6 : mList.size();
    }


    class ModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvSeq;
        private final TextView mTvStatus;
        private final TextView mTvVersion;
        private final CheckBox mCbDownload;


        public ModelHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvSeq = itemView.findViewById(R.id.tv_seq);
            mTvStatus = itemView.findViewById(R.id.tv_status);
            mTvVersion = itemView.findViewById(R.id.tv_version);
            mCbDownload = itemView.findViewById(R.id.cb_download);


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
