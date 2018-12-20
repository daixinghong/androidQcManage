package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;

import java.util.List;

public class RcyModelDetailAdapter extends RecyclerView.Adapter<RcyModelDetailAdapter.ModelHolder> {

    private Context mContext;
    private List<String> mList;


    public RcyModelDetailAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ModelHolder holder = new ModelHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_model_detail_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ModelHolder holder, final int position) {
        holder.mTvSeq.setText(position + 1 + "");

    }


    @Override
    public int getItemCount() {
        return mList == null ? 5 : mList.size();
    }


    class ModelHolder extends RecyclerView.ViewHolder {

        private final TextView mTvSeq;
        private final TextView mTvVersion;
        private final RelativeLayout mRlOpreate;

        public ModelHolder(View itemView) {
            super(itemView);
            mTvSeq = itemView.findViewById(R.id.tv_seq);
            mTvVersion = itemView.findViewById(R.id.tv_version);
            mRlOpreate = itemView.findViewById(R.id.rl_opreate);
        }

    }


}
