package com.sinano.devices.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.devices.view.adapter.RcyConfigureVersionAdapter;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SwitchConfigureActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_terminal_seq)
    TextView mTvTerminalSeq;
    @BindView(R.id.tv_server_seq)
    TextView mTvServerSeq;
    @BindView(R.id.tv_current_model_version)
    TextView mTvCurrentModelVersion;
    @BindView(R.id.tv_switch_version)
    TextView mTvSwitchVersion;
    @BindView(R.id.tv_project)
    TextView mTvProject;
    @BindView(R.id.rl_choose_project)
    RelativeLayout mRlChooseProject;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.rl_choose_type)
    RelativeLayout mRlChooseType;
    @BindView(R.id.rcy_list)
    RecyclerView mRcyList;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.rl_save)
    RelativeLayout mRlSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.terminal_switch_configure));
        mRlSave.setVisibility(View.VISIBLE);
        mTvSave.setText(UiUtils.findStringBuId(R.string.switchs));

        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyList.setLayoutManager(manager);

        RcyConfigureVersionAdapter adapter = new RcyConfigureVersionAdapter(this, null);
        mRcyList.setAdapter(adapter);

    }

    @Override
    public int getContentView() {

        return R.layout.activity_switch_configure;
    }

    @OnClick({R.id.rl_back, R.id.rl_choose_project, R.id.rl_choose_type, R.id.rl_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_choose_project:

                break;
            case R.id.rl_choose_type:

                break;
            case R.id.rl_save:

                break;
        }
    }

}
