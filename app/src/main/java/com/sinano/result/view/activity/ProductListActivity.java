package com.sinano.result.view.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.result.view.adapter.RcyProductAdapter;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductListActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rcy_product)
    RecyclerView mRcyProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.bad_product_list));

        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRcyProduct.setLayoutManager(recyclerViewLayoutManager);

        RcyProductAdapter adapter = new RcyProductAdapter(this, null);
        mRcyProduct.setAdapter(adapter);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_product_list;
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
