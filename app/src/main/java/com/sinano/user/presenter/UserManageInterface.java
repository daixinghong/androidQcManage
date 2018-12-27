package com.sinano.user.presenter;

import com.sinano.base.BaseInterface;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.ChildUserBean;
import com.sinano.user.model.UserInfoBean;

public interface UserManageInterface extends BaseInterface {


    String getOldPassword();

    String getNewPassword();

    void updataPasswordSuccess(BaseResultBean baseResultBean);

    void getChildUserSuccess(ChildUserBean childUserBean);

    void getUserInfoSuccess(UserInfoBean userInfoBean);

    void updataUserInfoSuccess(BaseResultBean baseResultBean);



}
