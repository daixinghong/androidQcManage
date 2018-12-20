package com.sinano.devices.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.devices.view.adapter.ConfigVersionListAdapter;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfigureDetailManageActivity extends BaseActivity {

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
    @BindView(R.id.rcy_configure_version_list)
    RecyclerView mRcyConfigureVersionList;
    @BindView(R.id.rl_save)
    RelativeLayout mRlSave;
    @BindView(R.id.tv_save)
    TextView mTvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        mTvTitle.setText(UiUtils.findStringBuId(R.string.configure_version));
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyConfigureVersionList.setLayoutManager(manager);

        ConfigVersionListAdapter adapter = new ConfigVersionListAdapter(this, null);
        mRcyConfigureVersionList.setAdapter(adapter);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_configure_detail_manage;
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }

    }
}
