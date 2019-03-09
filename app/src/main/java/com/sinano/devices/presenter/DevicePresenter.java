package com.sinano.devices.presenter;

import android.content.Context;

import com.sinano.http.Network;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DevicePresenter {
    private DeviceInterface mInterface;

    public DevicePresenter(DeviceInterface deviceInterface) {
        this.mInterface = deviceInterface;
    }

    public void getDevicesStatus(Context context) {
        Network
                .getObserableIntence(context)
                .getDeviceStatusList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getDeviceInfoSuccess, mInterface::getDataError);
    }

    public void getDevicesStatus() {
        Network
                .getObserableIntence()
                .getDeviceStatusList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getDeviceInfoSuccess, mInterface::getDataError);
    }

    public void getDeviceInfo(Context context,String mac) {
        Network
                .getObserableIntence(context)
                .getDeviceInfo(mac)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getDeviceDetailSuccess, mInterface::getDataError);
    }

    public void getDeviceInfo(String mac) {
        Network
                .getObserableIntence()
                .getDeviceInfo(mac)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::getDeviceDetailSuccess, mInterface::getDataError);
    }


    public void publishDevice(String mac) {
        Network
                .getObserableIntence()
                .publishDevice(mac)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mInterface::publishDeviceSuccess, mInterface::getDataError);
    }
}
