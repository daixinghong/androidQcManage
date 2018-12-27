package com.sinano.user.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.LoginBean;

import java.util.Map;

public interface LoginInterface extends BaseInterface {

    void loginSuccess(LoginBean loginBean);

    Map<String,Object> getMap();

    void registerSuccess(BaseResultBean baseResultBean);

    void logoutSuccess(BaseResultBean baseResultBean);

    String getUserName();

    String getPassword();

}
