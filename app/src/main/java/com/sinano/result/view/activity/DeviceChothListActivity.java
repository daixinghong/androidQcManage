package com.sinano.result.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.result.view.adapter.RcyClothListAdapter;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class DeviceChothListActivity extends BaseActivity implements RcyClothListAdapter.OnItemClickListener {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rcy_cloth_list)
    RecyclerView mRcyClothList;
    private RcyClothListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.cloth_list));

        mRcyClothList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RcyClothListAdapter(this, null);
        mRcyClothList.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_device_choth_list;
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setOnItemClickListener(View view, int position) {

        Bundle bundle = new Bundle();
        IntentUtils.startActivityForParms(this, ClothBadTypeCountDetailActivity.class, bundle);
    }
}
