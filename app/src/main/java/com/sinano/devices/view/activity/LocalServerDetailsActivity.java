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
import com.sinano.devices.view.adapter.RcyModelListAdapter;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LocalServerDetailsActivity extends BaseActivity implements RcyModelListAdapter.OnItemClickListener {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rcy_model_list)
    RecyclerView mRcyServerList;
    @BindView(R.id.tv_server_seq)
    TextView mTvServerSeq;
    @BindView(R.id.tv_connect_terminal_count)
    TextView mTvConnectTerminalCount;
    @BindView(R.id.rcy_config_list)
    RecyclerView mRcyConfigList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.local_server_manage));

        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyServerList.setLayoutManager(manager);
        RcyModelListAdapter adapter = new RcyModelListAdapter(this, null);
        mRcyServerList.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyConfigList.setLayoutManager(linearLayoutManager);

        RcyConfigListAdapter configListAdapter = new RcyConfigListAdapter(this, null);
        mRcyConfigList.setAdapter(configListAdapter);


    }

    @Override
    public int getContentView() {
        return R.layout.activity_local_server_details;
    }

    @OnClick({R.id.rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    public void setOnItemClickListener(View view, int position) {

        Bundle bundle = new Bundle();
        IntentUtils.startActivityForParms(this, ConfigureManageActivity.class, bundle);
    }
}
