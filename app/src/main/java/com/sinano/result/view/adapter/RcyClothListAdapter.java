package com.sinano.result.view.adapter;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sinano.R;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.ClothContentBean;
import com.sinano.result.model.ClothDescBean;

import java.util.List;

public class RcyClothListAdapter extends RecyclerView.Adapter<RcyClothListAdapter.ClothHolder> {

    private TranslateAnimation mShowAction;
    private TranslateAnimation mHiddenAction;
    private Context mContext;
    private List<CheckResultDetailBean.DataBean.RecordsBean> mList;
    private OnItemClickListener mOnItemClickListener;
    private Gson mGson = new Gson();

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RcyClothListAdapter(Context context, List<CheckResultDetailBean.DataBean.RecordsBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public ClothHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClothHolder holder = new ClothHolder(LayoutInflater.from(
                mContext).inflate(R.layout.rcy_cloth_list_item, parent,
                false), mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClothHolder holder, final int position) {

        String content = mList.get(position).getContent();
        String description = mList.get(position).getDescription();

        try {
            ClothContentBean clothContentBean = mGson.fromJson(content, ClothContentBean.class);
            ClothDescBean clothDescBean = mGson.fromJson(description, ClothDescBean.class);

            holder.mTvClothNumber.setText(clothDescBean.getOrder_number());
            holder.mTvPotNumber.setText(clothDescBean.getCylinder_numbe());
            holder.mTvLength.setText(clothDescBean.getLength() + "");
            holder.mTvOpreateUser.setText(clothDescBean.getUser());
            holder.mTvBadCount.setText(clothContentBean.getBad_info().size() + "");
            holder.mTvColor.setText(clothDescBean.getColor());
            holder.mTvUnit.setText(clothDescBean.getEntrusted_unit());
            holder.mTvGrade.setText(mList.get(position).getCust());
            holder.mTvDate.setText(mList.get(position).getCreateTime());
            holder.mTvWeight.setText(clothDescBean.getWeight());
        } catch (Exception e) {

        }

        holder.mRlEls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.get(position).isStatus()) {
                    holder.mIvImage.setImageResource(R.mipmap.down);
                    holder.mLlContainer2.setVisibility(View.GONE);
                    holder.mLlContainer1.setVisibility(View.GONE);
                } else {
                    holder.mLlContainer1.setVisibility(View.VISIBLE);
                    holder.mLlContainer2.setVisibility(View.VISIBLE);
                    holder.mIvImage.setImageResource(R.mipmap.up);
                }

                mList.get(position).setStatus(!mList.get(position).isStatus());
            }
        });


    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class ClothHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mOnItemClickListener;
        private final TextView mTvClothNumber;
        private final TextView mTvPotNumber;
        private final TextView mTvLength;
        private final TextView mTvBadCount;
        private final TextView mTvOpreateUser;
        private final TextView mTvUnit;
        private final TextView mTvColor;
        private final TextView mTvGrade;
        private final TextView mTvDate;
        private final TextView mTvWeight;
        private final LinearLayout mLlContainer1;
        private final LinearLayout mLlContainer2;
        private final RelativeLayout mRlEls;
        private final ImageView mIvImage;


        public ClothHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mTvClothNumber = itemView.findViewById(R.id.tv_cloth_number);
            mTvPotNumber = itemView.findViewById(R.id.tv_pot_number);
            mTvLength = itemView.findViewById(R.id.tv_length);
            mTvBadCount = itemView.findViewById(R.id.tv_bad_count);
            mTvOpreateUser = itemView.findViewById(R.id.tv_operate_user);
            mTvUnit = itemView.findViewById(R.id.tv_entrusted_unit);
            mTvColor = itemView.findViewById(R.id.tv_color);
            mTvGrade = itemView.findViewById(R.id.tv_grade);
            mTvDate = itemView.findViewById(R.id.tv_date);
            mTvWeight = itemView.findViewById(R.id.tv_weight);
            mLlContainer1 = itemView.findViewById(R.id.ll_container1);
            mLlContainer2 = itemView.findViewById(R.id.ll_container2);
            mRlEls = itemView.findViewById(R.id.rl_els);
            mIvImage = itemView.findViewById(R.id.iv_image);

            LayoutTransition transition = mLlContainer1.getLayoutTransition();
            transition.enableTransitionType(LayoutTransition.CHANGING);

            LayoutTransition transition2 = mLlContainer2.getLayoutTransition();
            transition2.enableTransitionType(LayoutTransition.CHANGING);


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
