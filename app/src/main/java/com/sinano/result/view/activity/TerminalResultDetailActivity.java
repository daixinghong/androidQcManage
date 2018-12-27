package com.sinano.result.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.result.view.adapter.ElResultDetailAdapter;
import com.sinano.result.view.adapter.RcyChartListAdapter;
import com.sinano.utils.UiUtils;
import com.sinano.weight.MyExpandableListView;

import butterknife.BindView;
import butterknife.OnClick;

public class TerminalResultDetailActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_server_count)
    TextView mTvServerCount;
    @BindView(R.id.tv_terminal_count)
    TextView mTvTerminalCount;
    @BindView(R.id.tv_good_product_count)
    TextView mTvGoodProductCount;
    @BindView(R.id.tv_defective_rate)
    TextView mTvDefectiveRate;
    @BindView(R.id.tv_all_count)
    TextView mTvAllCount;
    @BindView(R.id.el_list)
    MyExpandableListView mElList;
    @BindView(R.id.rcy_chart_list)
    RecyclerView mRcyChartList;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        mTvTitle.setText(UiUtils.findStringBuId(R.string.check_result_detail));

        ElResultDetailAdapter adapter = new ElResultDetailAdapter(this, null);
        mElList.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyChartList.setLayoutManager(manager);

        RcyChartListAdapter chartListAdapter = new RcyChartListAdapter(this, null);
        mRcyChartList.setAdapter(chartListAdapter);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_terminal_result_detail;
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
