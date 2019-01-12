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
import com.sinano.devices.view.adapter.RcyConfigureAdapter;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfigureManageActivity extends BaseActivity implements ConfigInterface {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rcy_list)
    RecyclerView mRcyList;
    private List<ConfigListBean.DataBean.RecordsBean> mList = new ArrayList<>();
    private RcyConfigureAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.configure_manage));

        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyList.setLayoutManager(manager);

        mAdapter = new RcyConfigureAdapter(this, mList);

        mRcyList.setAdapter(mAdapter);

        ConfigPresenter presenter = new ConfigPresenter(this);
        presenter.getConfigListData();

    }

    @Override
    public int getContentView() {
        return R.layout.activity_configure_manage;
    }

    @OnClick(R.id.rl_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }

    }

    @Override
    public Map<String, Object> getMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("current", "");
        map.put("keywords", "");
        map.put("pcId", "");
        map.put("size", "");
        map.put("current", "");

        return map;
    }

    @Override
    public void getConfigListDataSuccess(ConfigListBean configListBean) {

        switch (configListBean.getCode()) {
            case 200:
                ConfigListBean.DataBean data = configListBean.getData();
                List<ConfigListBean.DataBean.RecordsBean> records = data.getRecords();
                mList.clear();
                mList.addAll(records);
                mAdapter.notifyDataSetChanged();
                break;

        }

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
