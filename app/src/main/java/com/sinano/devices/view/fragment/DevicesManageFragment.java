package com.sinano.devices.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseFragment;
import com.sinano.devices.model.DeviceListBean;
import com.sinano.devices.presenter.DeviceInterface;
import com.sinano.devices.presenter.DevicePresenter;
import com.sinano.devices.view.activity.LocalServerDetailsActivity;
import com.sinano.devices.view.adapter.RcyNoQueryServerAdapter;
import com.sinano.devices.view.adapter.RcyServerListAdapter;
import com.sinano.user.view.adapter.RcyVersionInfoAdapter;
import com.sinano.utils.DialogUtils;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class DevicesManageFragment extends BaseFragment implements RcyNoQueryServerAdapter.OnItemClickListener, DeviceInterface {


    RecyclerView mRcyServerList;
    private RcyServerListAdapter mAdapter;
    private List<DeviceListBean.DataBean.ServerBean> mPhoneDeviceList = new ArrayList<>();
    private DevicePresenter mDevicePresenter;

    @Override
    public View getContentView() {

        View view = View.inflate(getActivity(), R.layout.fragment_devices, null);

        init(view);

        return view;
    }

    private void init(View view) {

        mRcyServerList = view.findViewById(R.id.rcy_server_list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyServerList.setLayoutManager(manager);
        mAdapter = new RcyServerListAdapter(getActivity(), mPhoneDeviceList);
        mRcyServerList.setAdapter(mAdapter);

        mDevicePresenter = new DevicePresenter(this);
        mDevicePresenter.getDevicesStatus(getActivity());

        View views = DialogUtils.inflateView(getActivity(), R.layout.dialog_updialog_view);
        TextView tvTitle = views.findViewById(R.id.tv_title);

        RecyclerView rcyVersionInfo = views.findViewById(R.id.rcy_version_info);
        rcyVersionInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        RcyVersionInfoAdapter adapter = new RcyVersionInfoAdapter(getActivity(), null);
        rcyVersionInfo.setAdapter(adapter);

        RelativeLayout rlAfter = views.findViewById(R.id.rl_after);
        RelativeLayout rlUpgrade = views.findViewById(R.id.rl_upgrade);

//        rlAfter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogUtils.dissDialog();
//
//            }
//        });
//
//        rlUpgrade.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        DialogUtils.createDialogFour(views);

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
