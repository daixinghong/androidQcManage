package com.sinano.result.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sinano.R;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.PhoneBadContentBean;
import com.sinano.utils.Constant;

import java.util.List;

public class RcyProductAdapter extends RecyclerView.Adapter<RcyProductAdapter.ProductHolder> {

    private Context mContext;
    private List<CheckResultDetailBean.DataBean.RecordsBean> mList;
    private OnItemClickListener mOnItemClickListener;
    private Gson gson = new Gson();


    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyProductAdapter(Context context, List<CheckResultDetailBean.DataBean.RecordsBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductHolder holder = new ProductHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_product_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, final int position) {

        List<String> url = mList.get(position).getUrl();

        if (url != null && url.size() != 0)
            Glide.with(mContext).load(Constant.IMAGE_URL + url.get(0)).into(holder.mIvImage);

        try {
            PhoneBadContentBean phoneBadContentBean = gson.fromJson(mList.get(position).getContent(), PhoneBadContentBean.class);
            String desc = "";
            for (int i = 0; i < phoneBadContentBean.getInfo().size(); i++) {
                if (i == phoneBadContentBean.getInfo().size() - 1) {
                    desc += phoneBadContentBean.getInfo().get(i).getPart() + ":" + phoneBadContentBean.getInfo().get(i).getDesc();
                } else {
                    desc += phoneBadContentBean.getInfo().get(i).getPart() + ":" + phoneBadContentBean.getInfo().get(i).getDesc() + "\r\n";
                }
            }
            holder.mTvName.setText(desc);
        } catch (Exception e) {

        }


    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvName;
        private final ImageView mIvImage;


        public ProductHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.name_item);
            mIvImage = itemView.findViewById(R.id.image_item);
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
