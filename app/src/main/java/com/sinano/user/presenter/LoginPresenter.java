package com.sinano.user.presenter;

import com.sinano.http.Network;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter {

    private LoginInterface mLoginInterface;

    public LoginPresenter(LoginInterface loginInterface) {
        this.mLoginInterface = loginInterface;
    }

    public void login() {
        Network
                .getObserableIntenceLogin()
                .login(mLoginInterface.getUserName(), mLoginInterface.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mLoginInterface::loginSuccess, mLoginInterface::getDataError);
    }

    public void register() {
        Network
                .getObserableIntence()
                .register(mLoginInterface.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mLoginInterface::registerSuccess, mLoginInterface::getDataError);
    }

    public void logout() {
        Network
                .getObserableIntence()
                .logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mLoginInterface::logoutSuccess, mLoginInterface::getDataError);
    }

    public void updataPassword() {
        Network
                .getObserableIntence()
                .register(mLoginInterface.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mLoginInterface::registerSuccess, mLoginInterface::getDataError);
    }



}
