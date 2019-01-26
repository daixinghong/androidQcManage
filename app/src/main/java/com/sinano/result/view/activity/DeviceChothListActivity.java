package com.sinano.result.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.ClothContentBean;
import com.sinano.result.model.ClothDescBean;
import com.sinano.result.model.ClothDescForMd5Bean;
import com.sinano.result.model.DeviceResultForConfigBean;
import com.sinano.result.model.ResultBean;
import com.sinano.result.presenter.ResultInterface;
import com.sinano.result.presenter.ResultPresenter;
import com.sinano.result.view.adapter.RcyClothListAdapter;
import com.sinano.utils.Constant;
import com.sinano.utils.DateUtil;
import com.sinano.utils.IntentUtils;
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

public class DeviceChothListActivity extends BaseActivity implements RcyClothListAdapter.OnItemClickListener, ResultInterface {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rcy_cloth_list)
    RecyclerView mRcyClothList;
    @BindView(R.id.tv_start_date)
    TextView mTvStartDate;
    @BindView(R.id.ll_start_date)
    LinearLayout mLlStartDate;
    @BindView(R.id.tv_end_date)
    TextView mTvEndDate;
    @BindView(R.id.ll_end_date)
    LinearLayout mLlEndDate;
    @BindView(R.id.rl_delete_start_date)
    RelativeLayout mRlDeleteStartDate;
    @BindView(R.id.rl_delete_end_date)
    RelativeLayout mRlDeleteEndDate;
    @BindView(R.id.ll_empty_view)
    LinearLayout mLlEmptyView;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    private RcyClothListAdapter mAdapter;
    private String mMac;
    private ResultPresenter mPresenter;
    private List<CheckResultDetailBean.DataBean.RecordsBean> mList = new ArrayList<>();
    private Gson mGson = new Gson();
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
        }

        mPresenter = new ResultPresenter(this);
        mPresenter.getCheckResultDetail(this);

        mTvTitle.setText(UiUtils.findStringBuId(R.string.cloth_list));

        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyClothList.setLayoutManager(manager);
        mAdapter = new RcyClothListAdapter(this, mList);
        mRcyClothList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
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
        return R.layout.activity_device_choth_list;
    }


    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = Calendar.getInstance(Locale.CHINA);
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = Calendar.getInstance(Locale.CHINA);
        secondDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar thirdDisabled = Calendar.getInstance(Locale.CHINA);
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }


    @Override
    public void setOnItemClickListener(View view, int position) {

        try {
            Bundle bundle = new Bundle();
            String content = mList.get(position).getContent();
            ClothContentBean clothContentBean = mGson.fromJson(content, ClothContentBean.class);
            bundle.putSerializable("data", (ArrayList<ClothContentBean.BadInfoBean>) clothContentBean.getBad_info());

            ClothDescBean clothDescBean = mGson.fromJson(mList.get(position).getDescription(), ClothDescBean.class);
            bundle.putSerializable("desc", clothDescBean);

            IntentUtils.startActivityForParms(this, ClothBadTypeCountDetailActivity.class, bundle);
        } catch (Exception e) {

        }

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
                mPresenter.getCheckResultDetail(DeviceChothListActivity.this);
                break;
            case R.id.rl_delete_end_date:
                mTvEndDate.setText("");
                mClear = true;
                mPresenter.getCheckResultDetail(DeviceChothListActivity.this);
                break;
        }
    }

    public void showDate() {
        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -5);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.DAY_OF_WEEK, 0);

        DatePickerBuilder oneDayBuilder = new DatePickerBuilder(this, listener)
                .date(Calendar.getInstance())
                .pickerType(CalendarView.ONE_DAY_PICKER)
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
                mPresenter.getCheckResultDetail(DeviceChothListActivity.this);
            }
        }

    };


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

        mRefresh.finishLoadmore();
        switch (checkResultDetailBean.getCode()) {
            case 200:
                CheckResultDetailBean.DataBean data = checkResultDetailBean.getData();
                List<CheckResultDetailBean.DataBean.RecordsBean> records = data.getRecords();
                if (data.getCurrent() == data.getPages()) {
                    mIsLoadMore = true;
                }
                mIndex = data.getCurrent();
                if (records == null || records.size() == 0) {
                    mRcyClothList.setVisibility(View.GONE);
                    mLlEmptyView.setVisibility(View.VISIBLE);
                    return;
                } else {
                    mRcyClothList.setVisibility(View.VISIBLE);
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
        if (!TextUtils.isEmpty(mTvEndDate.getText().toString().trim()))
            map.put("endTime", mTvEndDate.getText().toString().trim());
        map.put("mac", mMac);
        if (!TextUtils.isEmpty(mTvStartDate.getText().toString().trim()))
            map.put("startTime", mTvStartDate.getText().toString().trim());
        map.put("type", "cloth");
        map.put("current", mIndex);
        map.put("size", 20);
        return map;
    }

}
