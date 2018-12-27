package com.sinano.devices.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.devices.model.ConfigDetailBean;
import com.sinano.devices.model.ConfigListBean;

import java.util.Map;

public interface ConfigInterface extends BaseInterface {

    Map<String,Object> getMap();

    void getConfigListDataSuccess(ConfigListBean configListBean);

    void getConfigVersionDetailSuccess(ConfigDetailBean configDetailBean);

}
