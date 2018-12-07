package com.sinano.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG ="sinano" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        ButterKnife.bind(this);

    }

    //每个页面的视图
    public abstract int getContentView();


    public void getDataError(Throwable throwable) {

    }

    public String getHeader() {
        return null;
    }


}
