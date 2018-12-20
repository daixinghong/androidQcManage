package com.sinano.devices.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.devices.view.adapter.RcyConfigListAdapter;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class TerminalDetailActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_device_seq)
    TextView mTvDeviceSeq;
    @BindView(R.id.tv_use_config)
    TextView mTvUseConfig;
    @BindView(R.id.tv_technology_classify)
    TextView mTvTechnologyClassify;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tv_current_count)
    TextView mTvCurrentCount;
    @BindView(R.id.tv_all_count)
    TextView mTvAllCount;
    @BindView(R.id.tv_current_bad_count)
    TextView mTvCurrentBadCount;
    @BindView(R.id.tv_all_bad_count)
    TextView mTvAllBadCount;
    @BindView(R.id.rcy_config_list)
    RecyclerView mRcyConfigList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.device_info));
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRcyConfigList.setLayoutManager(manager);

        RcyConfigListAdapter adapter = new RcyConfigListAdapter(this, null);
        mRcyConfigList.setAdapter(adapter);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_terminal_detail;
    }

    @OnClick(R.id.rl_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }

    }
}
