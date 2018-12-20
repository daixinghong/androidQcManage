package com.sinano.devices.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.devices.view.adapter.RcyLocalServerDownloadAdapter;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LocalModelDownloadActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_technology_classify)
    TextView mTvTechnologyClassify;
    @BindView(R.id.rl_classify)
    RelativeLayout mRlClassify;
    @BindView(R.id.tv_check_object)
    TextView mTvCheckObject;
    @BindView(R.id.rl_check_object)
    RelativeLayout mRlCheckObject;
    @BindView(R.id.rcy_check_part_list)
    RecyclerView mRcyCheckPartList;
    @BindView(R.id.rl_download)
    RelativeLayout mRlDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.local_model_download));
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRcyCheckPartList.setLayoutManager(manager);

        RcyLocalServerDownloadAdapter adapter = new RcyLocalServerDownloadAdapter(this, null);
        mRcyCheckPartList.setAdapter(adapter);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_local_model_download;
    }

    @OnClick({R.id.rl_back, R.id.rl_classify, R.id.rl_check_object, R.id.rl_download})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_classify:

                break;
            case R.id.rl_check_object:

                break;
            case R.id.rl_download:

                break;
        }
    }
}
