package com.sinano.result.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.result.model.CheckResultDetailBean;
import com.sinano.result.model.ClothDescForMd5Bean;
import com.sinano.result.model.DeviceResultForConfigBean;
import com.sinano.result.model.ResultBean;

import java.util.Map;

public interface ResultInterface extends BaseInterface {

    void getResultSuccess(ResultBean resultBean);

    void getResultForConfigSuccess(DeviceResultForConfigBean resultBean);

    void getResultForDeviceSuccess(DeviceResultForConfigBean resultBean);

    void getCheckResultDetailSuccess(CheckResultDetailBean checkResultDetailBean);

    void getClothResultDetailSuccess(ClothDescForMd5Bean clothDescForMd5Bean);

    String getConfigId();

    Map<String, Object> getParms();

}
