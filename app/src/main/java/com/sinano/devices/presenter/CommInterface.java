package com.sinano.devices.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.base.BaseResultBean;
import com.sinano.devices.model.TypeBean;
import com.sinano.user.model.AppVersionBean;

public interface CommInterface extends BaseInterface {


    void getTypeSuccess(TypeBean typeBean);

    void getLastAppVersionInfoSuccess(AppVersionBean appVersionBean);

    void registerDeviceSuccess(BaseResultBean baseResultBean);

    void bindCompanySuccess(BaseResultBean baseResultBean);
}
