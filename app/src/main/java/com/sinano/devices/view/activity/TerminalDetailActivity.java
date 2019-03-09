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
import com.sinano.devices.model.DeviceInfoBean;
import com.sinano.devices.model.DeviceListBean;
import com.sinano.devices.presenter.DeviceInterface;
import com.sinano.devices.presenter.DevicePresenter;
import com.sinano.devices.view.adapter.RcyConfigListAdapter;
import com.sinano.utils.Constant;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class TerminalDetailActivity extends BaseActivity implements DeviceInterface {


    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_device_seq)
    TextView mTvDeviceSeq;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_mac)
    TextView mTvMac;
    @BindView(R.id.tv_opreate_user)
    TextView mTvOpreateUser;
    @BindView(R.id.tv_technology_classify)
    TextView mTvTechnologyClassify;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tv_current_checking_cloth_meter)
    TextView mTvCurrentCheckingClothMeter;
    @BindView(R.id.tv_current_checking_cloth_bad_count)
    TextView mTvCurrentCheckingClothBadCount;
    @BindView(R.id.tv_current_day_check_meter)
    TextView mTvCurrentDayCheckMeter;
    @BindView(R.id.tv_current_bad_count)
    TextView mTvCurrentBadCount;
    @BindView(R.id.tv_current_cloth_count)
    TextView mTvCurrentClothCount;
    @BindView(R.id.tv_device_all_check_cloth_count_)
    TextView mTvDeviceAllCheckClothCount;
    @BindView(R.id.tv_device_all_check_cloth_meter_)
    TextView mTvDeviceAllCheckClothMeter;
    @BindView(R.id.tv_device_all_check_bad_count)
    TextView mTvDeviceAllCheckBadCount;
    @BindView(R.id.rcy_config_list)
    RecyclerView mRcyConfigList;
    private DevicePresenter mPresenter;
    private boolean mIsOnLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        Bundle bundleExtra = getIntent().getBundleExtra(Constant.BUNDLE_PARMS);
        mIsOnLine = bundleExtra.getBoolean("isOnLine");
        String mac = bundleExtra.getString("mac");

        mTvStatus.setText(mIsOnLine ? "设备在线" : "设备离线");
        mTvMac.setText(mac);

        mTvTitle.setText(UiUtils.findStringBuId(R.string.device_info));
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRcyConfigList.setLayoutManager(manager);

        RcyConfigListAdapter adapter = new RcyConfigListAdapter(this, null);
        mRcyConfigList.setAdapter(adapter);

        mPresenter = new DevicePresenter(this);
        mPresenter.getDeviceInfo(this, mac);


    }

    @Override
    public int getContentView() {
        return R.layout.activity_terminal_detail;
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
    public void getDeviceInfoSuccess(DeviceListBean deviceListBean) {

    }

    @Override
    public String getCompanyId() {
        return null;
    }

    @Override
    public void getDeviceDetailSuccess(DeviceInfoBean deviceInfoBean) {

        switch (deviceInfoBean.getCode()) {
            case 200:
                DeviceInfoBean.DataBean data = deviceInfoBean.getData();

                if (data.getIsUpdate() != "1" && mIsOnLine) {
                    mPresenter.getDeviceInfo(data.getMac());
                }

                mTvDeviceSeq.setText(data.getDeviceName());
                try {
                    String deviceAllData = data.getDeviceAllData();   //总共检测信息
                    JSONObject deviceAllDataJsonObject = new JSONObject(deviceAllData);
                    mTvDeviceAllCheckClothMeter.setText(deviceAllDataJsonObject.getInt("all_meter") + "米");
                    mTvDeviceAllCheckBadCount.setText(deviceAllDataJsonObject.getInt("all_bad_count") + "");
                    mTvDeviceAllCheckClothCount.setText(deviceAllDataJsonObject.getInt("all_cloth_count") + "");

                    String deviceDataDay = data.getDeviceDataDay();   //当日检测信息
                    JSONObject deviceDataDayJsonObject = new JSONObject(deviceDataDay);

                    mTvCurrentBadCount.setText(deviceDataDayJsonObject.getInt("badCount") + "");
                    mTvCurrentClothCount.setText(deviceDataDayJsonObject.getInt("cloth_count") + "");
                    mTvCurrentDayCheckMeter.setText(deviceDataDayJsonObject.getInt("length") + "米");

                    String deviceInfo = data.getDeviceInfo();         //当前检测信息
                    JSONObject deviceInfoJsonObject = new JSONObject(deviceInfo);

                    mTvCurrentCheckingClothMeter.setText(deviceInfoJsonObject.getInt("length") + "米");
                    mTvCurrentCheckingClothBadCount.setText(deviceInfoJsonObject.getInt("bad_count") + "");
                    mTvOpreateUser.setText(deviceInfoJsonObject.getString("user"));


                } catch (Exception e) {

                }

                break;
            default:
                ToastUtils.showTextToast(deviceInfoBean.getMsg());
                break;
        }

    }

    @Override
    public void publishDeviceSuccess(BaseResultBean baseResultBean) {

    }
}
