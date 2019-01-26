package com.sinano.devices.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.ClothDescForMd5Bean;
import com.sinano.result.model.DeviceResultForConfigBean;
import com.sinano.result.model.ResultBean;
import com.sinano.result.presenter.ResultInterface;
import com.sinano.result.presenter.ResultPresenter;
import com.sinano.result.view.adapter.RcyChartListAdapter;
import com.sinano.utils.Constant;
import com.sinano.utils.UiUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class DeviceResultForConfigActivity extends BaseActivity implements ResultInterface {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_device_count)
    TextView mTvDeviceCount;
    @BindView(R.id.tv_good_count)
    TextView mTvGoodCount;
    @BindView(R.id.tv_bad_count)
    TextView mTvBadCount;
    @BindView(R.id.tv_defective_rate)
    TextView mTvDefectiveRate;
    @BindView(R.id.tv_all_count)
    TextView mTvAllCount;
    @BindView(R.id.rcy_chart_list)
    RecyclerView mRcyChartList;
    @BindView(R.id.ll_empty_view)
    LinearLayout mLlEmptyView;
    private String mConfigId;
    private ResultPresenter mPresenter;
    private RcyChartListAdapter mChartListAdapter;
    private List<DeviceResultForConfigBean.DataBean> mList = new ArrayList<>();
    private NumberFormat nf = NumberFormat.getNumberInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        Bundle bundleExtra = getIntent().getBundleExtra(Constant.BUNDLE_PARMS);

        if (bundleExtra != null) {
            mConfigId = bundleExtra.getString("configId");
        }

        nf.setMaximumFractionDigits(2);

        mPresenter = new ResultPresenter(this);
        mPresenter.getCheckResultForConfig(this);

        mTvTitle.setText(UiUtils.findStringBuId(R.string.check_result_detail));


        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyChartList.setLayoutManager(manager);

        mChartListAdapter = new RcyChartListAdapter(this, mList, false, null, mConfigId);
        mRcyChartList.setAdapter(mChartListAdapter);


    }

    @Override
    public int getContentView() {
        return R.layout.activity_device_result_for_config;
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void getResultSuccess(ResultBean responseBody) {

    }

    @Override
    public void getResultForConfigSuccess(DeviceResultForConfigBean resultBean) {
        switch (resultBean.getCode()) {
            case 200:
                List<DeviceResultForConfigBean.DataBean> data = resultBean.getData();

                if (data == null || data.size() == 0) {
                    mRcyChartList.setVisibility(View.GONE);
                    mLlEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mRcyChartList.setVisibility(View.VISIBLE);
                    mLlEmptyView.setVisibility(View.GONE);
                }
                mTvDeviceCount.setText(data.size() + "");
                double badCount = 0;
                int goodCount = 0;
                for (int i = 0; i < data.size(); i++) {
                    badCount += data.get(i).getNo();
                    goodCount += data.get(i).getYes();
                }

                mTvGoodCount.setText(goodCount + "");
                mTvBadCount.setText((int) badCount + "");
                mTvAllCount.setText(goodCount + badCount + "");

                double Rate = badCount / (badCount + goodCount) * 100d;

                mTvDefectiveRate.setText(nf.format(Rate) + "%");

                mList.clear();
                mList.addAll(data);
                mChartListAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void getResultForDeviceSuccess(DeviceResultForConfigBean resultBean) {

    }

    @Override
    public void getCheckResultDetailSuccess(CheckResultDetailBean checkResultDetailBean) {

    }

    @Override
    public void getClothResultDetailSuccess(ClothDescForMd5Bean clothDescForMd5Bean) {

    }

    @Override
    public String getConfigId() {
        return mConfigId;
    }

    @Override
    public Map<String, Object> getParms() {
        return null;
    }
}
