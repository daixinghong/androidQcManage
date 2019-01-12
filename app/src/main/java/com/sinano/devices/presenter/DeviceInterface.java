package com.sinano.devices.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.devices.model.DeviceListBean;

public interface DeviceInterface extends BaseInterface {

    void getDeviceInfoSuccess(DeviceListBean deviceListBean);

    String getCompanyId();


}
