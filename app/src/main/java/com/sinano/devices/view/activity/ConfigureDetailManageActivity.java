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
import com.sinano.devices.presenter.ConfigPresenter;
import com.sinano.devices.view.adapter.ConfigVersionListAdapter;
import com.sinano.utils.Constant;
import com.sinano.utils.SpUtils;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfigureDetailManageActivity extends BaseActivity implements ConfigInterface {

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
    @BindView(R.id.tv_current_version)
    TextView mTvCurrentVersion;
    private String mId = "47a6f47a693d404f865880af591be7c1";
    private String mTypeId = "4858356422014919a5c053cc492ecf50";
    private String mVersion;
    private List<ConfigDetailBean.DataBean> mList = new ArrayList<>();
    private ConfigVersionListAdapter mAdapter;
    private String mConfigName;
    private String mTime;
    private ConfigPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        Bundle bundleExtra = getIntent().getBundleExtra(Constant.BUNDLE_PARMS);
        if (bundleExtra != null) {
            mId = bundleExtra.getString(Constant.ID);
            mTypeId = bundleExtra.getString(Constant.TYPE_ID);
            mVersion = bundleExtra.getString(Constant.VERSION);
            mConfigName = bundleExtra.getString(Constant.NAME);
            mTime = bundleExtra.getString(Constant.TIME);
        }

        mTvCurrentVersion.setText(mVersion);
        mTvConfigName.setText(mConfigName);
        mTvCreateTime.setText(mTime);
        mTvTechnologyClassify.setText((String) SpUtils.getParam(this, mTypeId, ""));
        mTvTitle.setText(UiUtils.findStringBuId(R.string.configure_version));

        mRlSave.setVisibility(View.VISIBLE);
        mRlSave.setBackgroundResource(R.drawable.button_shape);
        mTvSave.setText(UiUtils.findStringBuId(R.string.synchronization));
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyConfigureVersionList.setLayoutManager(manager);

        mAdapter = new ConfigVersionListAdapter(this, mList);
        mRcyConfigureVersionList.setAdapter(mAdapter);

        mPresenter = new ConfigPresenter(this);
        mPresenter.getConfigVersionList(this,mId);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_configure_detail_manage;
    }

    @OnClick({R.id.rl_back, R.id.rl_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_save:
                mPresenter.synchronizeConfig();
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

        switch (configDetailBean.getCode()) {
            case 200:
                List<ConfigDetailBean.DataBean> data = configDetailBean.getData();
                mList.clear();
                mList.addAll(data);
                mAdapter.notifyDataSetChanged();
                break;

        }

    }

    @Override
    public void synchronizeConfigSuccess(BaseResultBean baseResultBean) {

    }

    @Override
    public String getMac() {
        return null;
    }
}
