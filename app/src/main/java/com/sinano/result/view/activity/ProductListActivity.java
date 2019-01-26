package com.sinano.result.view.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.ClothDescForMd5Bean;
import com.sinano.result.model.DeviceResultForConfigBean;
import com.sinano.result.model.ResultBean;
import com.sinano.result.presenter.ResultInterface;
import com.sinano.result.presenter.ResultPresenter;
import com.sinano.result.view.adapter.RcyProductAdapter;
import com.sinano.utils.Constant;
import com.sinano.utils.DateUtil;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductListActivity extends BaseActivity implements ResultInterface {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rcy_product)
    RecyclerView mRcyProduct;
    @BindView(R.id.tv_start_date)
    TextView mTvStartDate;
    @BindView(R.id.ll_start_date)
    LinearLayout mLlStartDate;
    @BindView(R.id.tv_end_date)
    TextView mTvEndDate;
    @BindView(R.id.ll_end_date)
    LinearLayout mLlEndDate;
    @BindView(R.id.ll_empty_view)
    LinearLayout mLlEmptyView;
    @BindView(R.id.rl_delete_start_date)
    RelativeLayout mRlDeleteStartDate;
    @BindView(R.id.rl_delete_end_date)
    RelativeLayout mRlDeleteEndDate;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    private ResultPresenter mPresenter;
    private List<CheckResultDetailBean.DataBean.RecordsBean> mList = new ArrayList<>();
    private RcyProductAdapter mAdapter;
    private String mMac;
    private String mConfigId;
    private boolean mStartTime;
    private boolean mIsLoadMore;
    private int mIndex = 1;
    private boolean mClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        Bundle bundleExtra = getIntent().getBundleExtra(Constant.BUNDLE_PARMS);

        if (bundleExtra != null) {
            mMac = bundleExtra.getString("mac");
            mConfigId = bundleExtra.getString("configId");
        }

        mTvTitle.setText(UiUtils.findStringBuId(R.string.bad_product_list));

        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };

        mRcyProduct.setLayoutManager(recyclerViewLayoutManager);

        mAdapter = new RcyProductAdapter(this, mList);
        mRcyProduct.setAdapter(mAdapter);

        mPresenter = new ResultPresenter(this);
        mPresenter.getCheckResultDetail(this);

        mRefresh.setEnableRefresh(false);
        mRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mIsLoadMore) {
                    mIsLoadMore = false;
                    mRefresh.finishLoadmore();
                    mRefresh.setLoadmoreFinished(true);
                } else {
                    mPresenter.getCheckResultDetail();
                }
            }
        });

    }

    @Override
    public int getContentView() {
        return R.layout.activity_product_list;
    }

    @OnClick({R.id.rl_back, R.id.ll_start_date, R.id.ll_end_date, R.id.rl_delete_start_date, R.id.rl_delete_end_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_start_date:
                showDate();
                mStartTime = true;

                break;
            case R.id.ll_end_date:
                showDate();
                mStartTime = false;

                break;
            case R.id.rl_delete_start_date:
                mTvStartDate.setText("");
                mClear = true;
                mPresenter.getCheckResultDetail(ProductListActivity.this);
                break;
            case R.id.rl_delete_end_date:
                mTvEndDate.setText("");
                mClear = true;
                mPresenter.getCheckResultDetail(ProductListActivity.this);
                break;

        }
    }


    public void showDate() {
        Calendar min = Calendar.getInstance(Locale.CHINA);
        min.add(Calendar.MONTH, -5);

        Calendar max = Calendar.getInstance(Locale.CHINA);

        DatePickerBuilder oneDayBuilder = new DatePickerBuilder(this, listener)
                .pickerType(CalendarView.ONE_DAY_PICKER)
                .date(max)
                .headerColor(R.color.color_102869)
                .headerLabelColor(R.color.white)
                .selectionColor(R.color.daysLabelColor)
                .todayLabelColor(R.color.colorAccent)
                .previousPageChangeListener(new OnCalendarPageChangeListener() {
                    @Override
                    public void onChange() {
                        Log.e(TAG, "onChange: previousPageChangeListener");
                    }
                }) // Listener called when scroll to the previous page
                .forwardPageChangeListener(new OnCalendarPageChangeListener() {
                    @Override
                    public void onChange() {
                        Log.e(TAG, "onChange: forwardPageChangeListener");
                    }
                })
                .dialogButtonsColor(android.R.color.holo_green_dark)
                .disabledDaysLabelsColor(android.R.color.holo_purple)
                .minimumDate(min)
                .maximumDate(max)
                .disabledDays(getDisabledDays());

        DatePicker oneDayPicker = oneDayBuilder.build();
        oneDayPicker.show();
    }

    private OnSelectDateListener listener = new OnSelectDateListener() {
        @Override
        public void onSelect(List<Calendar> calendar) {
            long timeInMillis = calendar.get(0).getTimeInMillis();
            String dateToString = DateUtil.getDateToString(timeInMillis, "yyyy-MM-dd");
            if (mStartTime) {
                mTvStartDate.setText(dateToString);
            } else {
                mTvEndDate.setText(dateToString);
            }
            if (!TextUtils.isEmpty(mTvStartDate.getText().toString().trim()) && !TextUtils.isEmpty(mTvEndDate.getText().toString().trim())) {
                mClear = true;
                mPresenter.getCheckResultDetail(ProductListActivity.this);
            }
        }

    };

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    @Override
    public void getResultSuccess(ResultBean resultBean) {

    }

    @Override
    public void getResultForConfigSuccess(DeviceResultForConfigBean resultBean) {

    }

    @Override
    public void getResultForDeviceSuccess(DeviceResultForConfigBean resultBean) {

    }

    @Override
    public void getCheckResultDetailSuccess(CheckResultDetailBean checkResultDetailBean) {

        switch (checkResultDetailBean.getCode()) {
            case 200:
                CheckResultDetailBean.DataBean data = checkResultDetailBean.getData();
                List<CheckResultDetailBean.DataBean.RecordsBean> records = data.getRecords();
                if (data.getCurrent() == data.getPages()) {
                    mIsLoadMore = true;
                }
                mIndex = data.getCurrent();
                if (records == null || records.size() == 0) {
                    mRcyProduct.setVisibility(View.GONE);
                    mLlEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mRcyProduct.setVisibility(View.VISIBLE);
                    mLlEmptyView.setVisibility(View.GONE);
                }

                if (mClear) {
                    mList.clear();
                    mList.addAll(records);
                    mAdapter.notifyDataSetChanged();
                } else {
                    mList.addAll(records);
                    mAdapter.notifyDataSetChanged();
                }

                break;
            default:
                ToastUtils.showTextToast(checkResultDetailBean.getMsg());
                break;
        }


    }

    @Override
    public void getClothResultDetailSuccess(ClothDescForMd5Bean clothDescForMd5Bean) {

    }

    @Override
    public String getConfigId() {
        return null;
    }

    @Override
    public Map<String, Object> getParms() {

        Map<String, Object> map = new HashMap<>();
        map.put("configId", mConfigId);
        if (!TextUtils.isEmpty(mTvEndDate.getText().toString().trim()))
            map.put("endTime", mTvEndDate.getText().toString().trim());
        if (!TextUtils.isEmpty(mTvStartDate.getText().toString().trim()))
            map.put("startTime", mTvStartDate.getText().toString().trim());
        map.put("mac", mMac);
        map.put("type", "phone");
        map.put("current", mIndex);
        map.put("size", 20);
        return map;
    }


}
