package com.sinano.result.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.result.view.adapter.ElResultDetailAdapter;
import com.sinano.utils.UiUtils;
import com.sinano.weight.MyExpandableListView;

import butterknife.BindView;
import butterknife.OnClick;

public class ResultDetailsActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_project_name)
    TextView mTvProjectName;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_good_product_count)
    TextView mTvGoodProductCount;
    @BindView(R.id.tv_bad_count)
    TextView mTvBadCount;
    @BindView(R.id.tv_all_count)
    TextView mTvAllCount;
    @BindView(R.id.el_list)
    MyExpandableListView mElList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {
        mTvTitle.setText(UiUtils.findStringBuId(R.string.project_check_manage));

        ElResultDetailAdapter adapter = new ElResultDetailAdapter(this, null);
        mElList.setAdapter(adapter);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_result_details;
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
