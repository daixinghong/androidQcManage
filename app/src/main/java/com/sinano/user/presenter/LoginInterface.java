package com.sinano.user.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.LoginBean;
import com.sinano.user.model.RegisterBean;

import java.util.Map;

public interface LoginInterface extends BaseInterface {

    void loginSuccess(LoginBean loginBean);

    Map<String,Object> getMap();

    void registerSuccess(RegisterBean registerBean);

    void logoutSuccess(BaseResultBean baseResultBean);

    String getUserName();

    String getPassword();

}
