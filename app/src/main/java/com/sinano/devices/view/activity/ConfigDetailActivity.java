package com.sinano.devices.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.devices.model.ConfigDetailBean;
import com.sinano.devices.model.ConfigListBean;
import com.sinano.devices.presenter.ConfigInterface;
import com.sinano.devices.view.adapter.RcyConfigDetailAdapter;
import com.sinano.utils.UiUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfigDetailActivity extends BaseActivity implements ConfigInterface {

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
    @BindView(R.id.rcy_config_detail)
    RecyclerView mRcyConfigDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.config_detail));
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyConfigDetail.setLayoutManager(manager);

        RcyConfigDetailAdapter adapter = new RcyConfigDetailAdapter(this, null);
        mRcyConfigDetail.setAdapter(adapter);

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

    @Override
    public Map<String, Object> getMap() {
        return null;
    }

    @Override
    public void getConfigListDataSuccess(ConfigListBean configListBean) {

    }

    @Override
    public void getConfigVersionDetailSuccess(ConfigDetailBean configDetailBean) {

    }

    @Override
    public void synchronizeConfigSuccess(BaseResultBean baseResultBean) {

    }

    @Override
    public String getMac() {
        return null;
    }
}
