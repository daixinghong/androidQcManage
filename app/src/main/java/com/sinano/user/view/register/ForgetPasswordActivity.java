package com.sinano.user.view.register;

import android.os.Bundle;

import com.sinano.R;
import com.sinano.base.BaseActivity;

public class ForgetPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_forget_password;
    }
}
