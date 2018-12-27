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

}
