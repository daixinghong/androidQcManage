package com.sinano.devices.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseFragment;
import com.sinano.devices.model.DeviceListBean;
import com.sinano.devices.presenter.DeviceInterface;
import com.sinano.devices.presenter.DevicePresenter;
import com.sinano.devices.view.activity.LocalServerDetailsActivity;
import com.sinano.devices.view.activity.TerminalDetailActivity;
import com.sinano.devices.view.adapter.RcyNoQueryServerAdapter;
import com.sinano.devices.view.adapter.RcyServerListAdapter;
import com.sinano.devices.view.adapter.RcyTerminalListAdapter;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class DevicesManageFragment extends BaseFragment implements RcyNoQueryServerAdapter.OnItemClickListener, DeviceInterface {


    RecyclerView mRcyServerList;
    private RcyServerListAdapter mAdapter;
    private List<DeviceListBean.DataBean.ServerBean> mPhoneDeviceList = new ArrayList<>();
    private DevicePresenter mDevicePresenter;
    private RecyclerView mRcyClothDevice;
    private List<DeviceListBean.DataBean.ClothDeviceBean> mList = new ArrayList<>();
    private RcyTerminalListAdapter mClothListAdapter;
    private TextView mTvPhoneTitle;
    private TextView mTvCloth;

    @Override
    public View getContentView() {

        View view = View.inflate(getActivity(), R.layout.fragment_devices, null);

        init(view);

        return view;
    }

    private void init(View view) {

        mRcyServerList = view.findViewById(R.id.rcy_server_list);
        mRcyClothDevice = view.findViewById(R.id.rcy_cloth_device);
        mTvPhoneTitle = view.findViewById(R.id.tv_phone_title);
        mTvCloth = view.findViewById(R.id.tv_cloth);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyServerList.setLayoutManager(manager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyClothDevice.setLayoutManager(gridLayoutManager);
        mClothListAdapter = new RcyTerminalListAdapter(getActivity(), mList);
        mRcyClothDevice.setAdapter(mClothListAdapter);


        mAdapter = new RcyServerListAdapter(getActivity(), mPhoneDeviceList);
        mRcyServerList.setAdapter(mAdapter);

        mDevicePresenter = new DevicePresenter(this);
        mDevicePresenter.getDevicesStatus(getActivity());

        mClothListAdapter.setOnItemClickListener(new RcyTerminalListAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(getActivity(), TerminalDetailActivity.class, bundle);
            }
        });


    }


    @Override
    public void setOnItemClickListener(View view, int position) {
        Bundle bundle = new Bundle();
        IntentUtils.startActivityForParms(getActivity(), LocalServerDetailsActivity.class, bundle);

    }

    @Override
    public void getDeviceInfoSuccess(DeviceListBean deviceListBean) {
        switch (deviceListBean.getCode()) {
            case 200:
                List<DeviceListBean.DataBean.ServerBean> server = deviceListBean.getData().getServer();
                mPhoneDeviceList.clear();
                mPhoneDeviceList.addAll(server);

                if (mPhoneDeviceList == null || mPhoneDeviceList.size() == 0) {
                    mTvPhoneTitle.setVisibility(View.GONE);
                }

                List<DeviceListBean.DataBean.ClothDeviceBean> clothDevice = deviceListBean.getData().getClothDevice();
                mList.clear();
                mList.addAll(clothDevice);

                if (mList == null || mList.size() == 0) {
                    mTvCloth.setVisibility(View.GONE);
                }

                mClothListAdapter.notifyDataSetChanged();
                mAdapter.notifyDataSetChanged();

                break;
            default:
                ToastUtils.showTextToast(deviceListBean.getMsg());
                break;
        }
    }

    @Override
    public String getCompanyId() {
        return null;
    }

    @Override
    public void getDataError(Throwable throwable) {

    }

    @Override
    public void onNetChange(int netMobile) {
        super.onNetChange(netMobile);

//        switch (netMobile) {
//            case 1:
//                mDevicePresenter.getDevicesStatus(getActivity());
//                break;
//            case 0:
//                mDevicePresenter.getDevicesStatus(getActivity());
//                break;
//        }
    }
}
