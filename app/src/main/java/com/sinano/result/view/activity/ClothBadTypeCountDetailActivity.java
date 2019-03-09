package com.sinano.result.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;
import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.devices.view.adapter.RcyClothBadInfoAddressAdapter;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.ClothContentBean;
import com.sinano.result.model.ClothDescBean;
import com.sinano.result.model.ClothDescForMd5Bean;
import com.sinano.result.model.DeviceResultForConfigBean;
import com.sinano.result.model.ResultBean;
import com.sinano.result.presenter.ResultInterface;
import com.sinano.result.presenter.ResultPresenter;
import com.sinano.utils.Constant;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressWarnings("unchecked")
public class ClothBadTypeCountDetailActivity extends BaseActivity implements ResultInterface {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.pic_chart)
    PieChart mPicChart;
    @BindView(R.id.tv_check_number)
    TextView mTvCheckNumber;
    @BindView(R.id.tv_entrusted_unit)
    TextView mTvEntrustedUnit;
    @BindView(R.id.tv_check_date)
    TextView mTvCheckDate;
    @BindView(R.id.tv_cloth_grade)
    TextView mTvClothGrade;
    @BindView(R.id.tv_bad_count)
    TextView mTvBadCount;
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;
    @BindView(R.id.tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.tv_pot)
    TextView mTvPot;
    @BindView(R.id.tv_length)
    TextView mTvLength;
    @BindView(R.id.tv_color)
    TextView mTvColor;
    @BindView(R.id.tv_width)
    TextView mTvWidth;
    @BindView(R.id.tv_weight)
    TextView mTvWeight;
    @BindView(R.id.rcy_bad_list)
    RecyclerView mRcyBadList;
    @BindView(R.id.tv_operate_user)
    TextView mTvOperateUser;
    private Gson mGson = new Gson();
    private List<ClothContentBean.BadInfoBean> mList = new ArrayList<>();
    private Map<Integer, List<ClothContentBean.BadInfoBean>> mStringMap = new HashMap<>();
    private RcyClothBadInfoAddressAdapter mAdapter;
    private ClothDescBean mClothDescBean;
    private String mMd5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        Bundle bundleExtra = getIntent().getBundleExtra(Constant.BUNDLE_PARMS);
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyBadList.setLayoutManager(manager);

        if (bundleExtra != null) {
            ArrayList<ClothContentBean.BadInfoBean> list = (ArrayList<ClothContentBean.BadInfoBean>) bundleExtra.getSerializable("data");
            if (list != null) {
                mList.clear();
                mList.addAll(list);
            }
            mClothDescBean = (ClothDescBean) bundleExtra.getSerializable("desc");

            mMd5 = bundleExtra.getString("md5");
        }

        mAdapter = new RcyClothBadInfoAddressAdapter(this, mList, false);
        mRcyBadList.setAdapter(mAdapter);
        mTvTitle.setText(UiUtils.findStringBuId(R.string.bcloth_detail));

        if (mMd5 != null) {
            ResultPresenter presenter = new ResultPresenter(this);
            presenter.getClothDetailForMd5(this, mMd5);

        } else {
            setClothInfo(mClothDescBean);
            setChartData(mList);
        }

    }

    @Override
    public int getContentView() {
        return R.layout.activity_cloth_bad_type_count_detail;
    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

    public void setClothInfo(ClothDescBean clothInfo) {
        mTvOrderNumber.setText(clothInfo.getOrder_number());
        mTvPot.setText(clothInfo.getCylinder_numbe());
        mTvLength.setText(clothInfo.getLength() + "");
        mTvOperateUser.setText(clothInfo.getUser());
        mTvColor.setText(clothInfo.getColor());
        mTvEntrustedUnit.setText(clothInfo.getEntrusted_unit());
        mTvClothGrade.setText(clothInfo.getCust() + "");
        mTvCheckDate.setText(clothInfo.getDate());
        mTvWeight.setText(clothInfo.getWeight());
        mTvProductName.setText(clothInfo.getTitle());
        mTvCheckNumber.setText(clothInfo.getOrder_number());
        mTvWidth.setText(clothInfo.getWidth());
        mTvTitleName.setText(clothInfo.getTitle());
        mTvBadCount.setText(mList.size() + "");
    }

    public void setChartData(List<ClothContentBean.BadInfoBean> dataList) {

        Description description = new Description();
        description.setText("");
        mPicChart.setDescription(description);
        mPicChart.setHoleRadius(0f);
        Legend l = mPicChart.getLegend();
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        mPicChart.setTransparentCircleRadius(0f);
        mPicChart.setDrawEntryLabels(false);

        List<PieEntry> listPie = new ArrayList<>();

        int counts = dataList.size();

        for (int i = 0; i < dataList.size(); i++) {
            int type = dataList.get(i).getType();
            if (mStringMap.get(type) == null) {
                List<ClothContentBean.BadInfoBean> list = new ArrayList<>();
                list.add(dataList.get(i));
                mStringMap.put(type, list);
            } else {
                mStringMap.get(type).add(dataList.get(i));
            }
        }

        for (List<ClothContentBean.BadInfoBean> v : mStringMap.values()) {
            double qty = v.size();
            float number = (float) (qty / counts);
            float count = (number * 100);
            PieEntry pieEntry = new PieEntry(count, v.get(0).getDesc() + "( " + ((int) qty) + " )");
            listPie.add(pieEntry);
        }

        setData(listPie);
    }

    private void setData(List<PieEntry> entries) {

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.color_F97319));
        colors.add(getResources().getColor(R.color.color_9BD504));
        colors.add(getResources().getColor(R.color.color_OBCA4E));
        colors.add(getResources().getColor(R.color.color_F45637));
        colors.add(getResources().getColor(R.color.color_34BCC6));

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        mPicChart.setData(data);
        mPicChart.highlightValues(null);
        //刷新
        mPicChart.invalidate();
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

    }

    @Override
    public void getClothResultDetailSuccess(ClothDescForMd5Bean clothDescForMd5Bean) {
        switch (clothDescForMd5Bean.getCode()) {
            case 200:
                ClothDescForMd5Bean.DataBean data = clothDescForMd5Bean.getData();
                String description = data.getDescription();
                String content = data.getContent();

                ClothContentBean clothContentBean = mGson.fromJson(content, ClothContentBean.class);
                mList.clear();
                mList.addAll(clothContentBean.getBad_info());
                mAdapter.notifyDataSetChanged();

                setChartData(clothContentBean.getBad_info());

                ClothDescBean clothDescBean = mGson.fromJson(description, ClothDescBean.class);
                setClothInfo(clothDescBean);

                break;
            default:
                ToastUtils.showTextToast(clothDescForMd5Bean.getMsg());
                break;
        }
    }

    @Override
    public String getConfigId() {
        return null;
    }

    @Override
    public Map<String, Object> getParms() {
        return null;
    }
}
