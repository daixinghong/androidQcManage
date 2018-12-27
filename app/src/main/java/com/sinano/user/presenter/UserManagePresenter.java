package com.sinano.user.presenter;

import com.sinano.http.Network;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserManagePresenter {

    private UserManageInterface mUserManageInterface;

    public UserManagePresenter(UserManageInterface userManageInterface) {
        this.mUserManageInterface = userManageInterface;
    }

    public void updataPassword() {
        Network
                .getObserableIntence()
                .updataPassword(mUserManageInterface.getNewPassword(), mUserManageInterface.getOldPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mUserManageInterface::updataPasswordSuccess, mUserManageInterface::getDataError);
    }

    public void getChildUser() {
        Network
                .getObserableIntence()
                .getChildUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mUserManageInterface::getChildUserSuccess, mUserManageInterface::getDataError);
    }

    public void getUserInfo(int  id) {
        Network
                .getObserableIntence()
                .getUserInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mUserManageInterface::getUserInfoSuccess, mUserManageInterface::getDataError);
    }

    public void updataUserInfo(String nickName,String phone) {
        Network
                .getObserableIntence()
                .updataUserInfo(nickName,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mUserManageInterface::updataUserInfoSuccess, mUserManageInterface::getDataError);
    }

}
