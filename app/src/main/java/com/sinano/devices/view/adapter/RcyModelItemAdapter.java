package com.sinano.devices.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinano.R;

import java.util.List;

public class RcyModelItemAdapter extends RecyclerView.Adapter<RcyModelItemAdapter.ModelHolder> {


    private Context mContext;
    private List<String> mList;


    public RcyModelItemAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ModelHolder holder = new ModelHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_model_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ModelHolder holder, final int position) {

        if (position == 4) {
            holder.mView.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 5 : mList.size();
    }


    class ModelHolder extends RecyclerView.ViewHolder {

        private final View mView;

        public ModelHolder(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.view);
        }

    }


}
