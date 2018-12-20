package com.sinano.devices.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.devices.model.ConfigListBean;

import java.util.Map;

public interface ConfigInterface extends BaseInterface {

    Map<String,Object> getMap();

    void getConfigListDataSuccess(ConfigListBean configListBean);


}
