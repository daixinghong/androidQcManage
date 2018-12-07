package com.sinano;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.sinano.base.BaseActivity;
import com.sinano.user.view.login.LoginActivity;
import com.sinano.utils.IntentUtils;

import butterknife.BindView;

public class StartAppActivity extends BaseActivity {

    @BindView(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    private final int REQUEST_CODE = 12138;
    private PackageInfo pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_start_app;
    }


    private void init() {

        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(1000);// 设置动画显示时间
        mRelativeLayout.startAnimation(anima);

        PackageManager pm = getPackageManager();

        try {
            pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] permissions = pi.requestedPermissions;
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    IntentUtils.startActivity(StartAppActivity.this, LoginActivity.class);
                    finish();
                } else {
                    System.exit(0);
                }
                return;
            }
        }
    }




}
