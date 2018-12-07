package com.sinano.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {


    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View contentView = getContentView();

        mBind = ButterKnife.bind(this, contentView);

        return contentView;
    }

    public abstract View getContentView();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

}
