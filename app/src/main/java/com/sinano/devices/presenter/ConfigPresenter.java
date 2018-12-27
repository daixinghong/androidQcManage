package com.sinano.devices.presenter;

import com.sinano.http.Network;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ConfigPresenter {

    private ConfigInterface mInterface;
    public ConfigPresenter(ConfigInterface configInterface) {
        this.mInterface = configInterface;
    }

    public void getConfigListData() {
        Network
                .getObserableIntence()
                .configList(mInterface.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getConfigListDataSuccess, mInterface::getDataError);
    }

    public void getConfigVersionList(String id) {
        Network
                .getObserableIntence()
                .configVersionDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getConfigVersionDetailSuccess, mInterface::getDataError);
    }

}
