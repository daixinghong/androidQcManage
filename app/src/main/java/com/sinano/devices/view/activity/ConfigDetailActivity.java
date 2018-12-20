package com.sinano.devices.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.devices.view.adapter.RcyConfigDetailAdapter;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfigDetailActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_config_name)
    TextView mTvConfigName;
    @BindView(R.id.tv_create_time)
    TextView mTvCreateTime;
    @BindView(R.id.tv_technology_classify)
    TextView mTvTechnologyClassify;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.rl_save)
    RelativeLayout mRlSave;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.rcy_configure_version_list)
    RecyclerView mRcyConfigureVersionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.config_detail));
        mRlSave.setVisibility(View.VISIBLE);
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyConfigureVersionList.setLayoutManager(manager);

        RcyConfigDetailAdapter adapter = new RcyConfigDetailAdapter(this, null);
        mRcyConfigureVersionList.setAdapter(adapter);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_config_detail;
    }

    @OnClick({R.id.rl_back, R.id.rl_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_save:

                break;
        }
    }
}
