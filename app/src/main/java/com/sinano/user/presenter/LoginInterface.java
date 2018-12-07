package com.sinano.user.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.user.model.LoginBean;
import com.sinano.user.model.RegisterBean;

public interface LoginInterface extends BaseInterface {

    void loginSuccess(LoginBean loginBean);

    void registerSuccess(RegisterBean registerBean);

    String getUserName();

    String getPassword();

}
