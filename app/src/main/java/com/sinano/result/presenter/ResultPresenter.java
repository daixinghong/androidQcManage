package com.sinano.result.presenter;

import android.content.Context;

import com.sinano.http.Network;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ResultPresenter {

    private ResultInterface mInterface;

    public ResultPresenter(ResultInterface resultInterface) {
        this.mInterface = resultInterface;
    }

    public void getAllCheckResult(Context context) {
        Network
                .getObserableIntence(context)
                .getAllCheckResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getResultSuccess, mInterface::getDataError);
    }

    public void getCheckResultForConfig(Context context) {
        Network
                .getObserableIntence(context)
                .getCheckResultForConfig(mInterface.getConfigId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getResultForConfigSuccess, mInterface::getDataError);
    }

    public void getCheckResultFordevice(Context context) {
        Network
                .getObserableIntence(context)
                .getCheckResultForDevice(mInterface.getConfigId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getResultForDeviceSuccess, mInterface::getDataError);
    }

    public void getCheckResultDetail(Context context) {
        Network
                .getObserableIntence(context)
                .getCheckResultDetail(mInterface.getParms())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getCheckResultDetailSuccess, mInterface::getDataError);
    }


    public void getCheckResultDetail() {
        getCheckResultDetail(null);
    }
}
