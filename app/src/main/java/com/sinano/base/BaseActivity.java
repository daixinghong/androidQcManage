package com.sinano.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sinano.R;
import com.sinano.receiver.NetBroadcastReceiver;
import com.sinano.utils.DialogUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import java.net.SocketTimeoutException;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.NetEvevt {

    public static final String TAG = "sinano";
    public static NetBroadcastReceiver.NetEvevt evevt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        ButterKnife.bind(this);
        evevt = this;
    }

    //每个页面的视图
    public abstract int getContentView();


    public void getDataError(Throwable throwable) {

        if (throwable instanceof SocketTimeoutException) {
            ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.request_timeout));
        }
        if (DialogUtils.sDialog != null) {
            DialogUtils.dissDialog();
        }
        Log.e(TAG, "getDataError: " + throwable.getMessage());

    }


    @Override
    public void onNetChange(int netMobile) {

    }
}
