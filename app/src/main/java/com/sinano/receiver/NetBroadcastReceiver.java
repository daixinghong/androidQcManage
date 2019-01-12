package com.sinano.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.sinano.base.BaseActivity;
import com.sinano.base.BaseFragment;
import com.sinano.utils.NetUtil;

public class NetBroadcastReceiver extends BroadcastReceiver {
    public static NetEvevt evevtActivity = BaseActivity.evevt;

    @Override
    public void onReceive(final Context context, Intent intent) {

        NetUtil.init(context);
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState();
            if (evevtActivity != null) {
                evevtActivity.onNetChange(netWorkState);
            }
            if (BaseFragment.evevt != null) {
                BaseFragment.evevt.onNetChange(netWorkState);
            }
        }
    }

    // 自定义接口
    public interface NetEvevt {
        void onNetChange(int netMobile);
    }


}
