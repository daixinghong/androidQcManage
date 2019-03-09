package com.sinano.devices.presenter;

import com.sinano.http.Network;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CommPresenter {

    private CommInterface mCommInterface;

    public CommPresenter(CommInterface commInterface) {
        this.mCommInterface = commInterface;
    }

    public void getType() {
        Network
                .getObserableIntenceComm()
                .typeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCommInterface::getTypeSuccess, mCommInterface::getDataError);
    }

    public void getLastVersionInfo() {
        Network
                .getObserableIntenceComm()
                .getLastVersionInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCommInterface::getLastAppVersionInfoSuccess, mCommInterface::getDataError);
    }


    public void registerDevice(String result) {
        Network
                .getObserableIntence()
                .registerDevice(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCommInterface::registerDeviceSuccess, mCommInterface::getDataError);
    }

    public void bindCompany(String companyId) {
        Network
                .getObserableIntence()
                .bindCompany(companyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCommInterface::bindCompanySuccess, mCommInterface::getDataError);
    }


}
