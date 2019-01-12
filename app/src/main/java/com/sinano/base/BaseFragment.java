package com.sinano.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.BarDataSet;
import com.sinano.receiver.NetBroadcastReceiver;

public abstract class BaseFragment extends Fragment implements NetBroadcastReceiver.NetEvevt {


    public final String TAG = "sinano";
    public static NetBroadcastReceiver.NetEvevt evevt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        evevt = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View contentView = getContentView();

        return contentView;
    }

    public abstract View getContentView();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(11f);
    }

    public void getDataError(Throwable throwable) {

        Log.e(TAG, "getDataError: " + throwable.getMessage());
    }

    @Override
    public void onNetChange(int netMobile) {

    }
}
