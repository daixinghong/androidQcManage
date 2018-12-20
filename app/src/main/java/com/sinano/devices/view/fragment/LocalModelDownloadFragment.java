package com.sinano.devices.view.fragment;

import android.view.View;

import com.sinano.R;
import com.sinano.base.BaseFragment;

public class LocalModelDownloadFragment extends BaseFragment {
    @Override
    public View getContentView() {

        View view = View.inflate(getActivity(), R.layout.fragment_local_model_download,null);

        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
