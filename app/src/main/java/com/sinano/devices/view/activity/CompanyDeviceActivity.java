package com.sinano.devices.view.activity;

import android.os.Bundle;

import com.sinano.R;
import com.sinano.base.BaseActivity;

public class CompanyDeviceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_company_device;
    }
}
