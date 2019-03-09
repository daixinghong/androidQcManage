package com.sinano;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.base.BaseActivity;
import com.sinano.user.view.login.LoginActivity;
import com.sinano.utils.APKVersionCodeUtils;
import com.sinano.utils.Constant;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.SpUtils;
import com.sinano.utils.UiUtils;

import butterknife.BindView;

public class StartAppActivity extends BaseActivity {

    @BindView(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    private final int REQUEST_CODE = 12138;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
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

        APKVersionCodeUtils.getVerName(this);

        mTvVersion.setText(UiUtils.findStringBuId(R.string.smart_qc_manage_app) + APKVersionCodeUtils.getVerName(this));

        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(2000);// 设置动画显示时间
        mRelativeLayout.startAnimation(anima);

        anima.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                PackageManager pm = getPackageManager();
                try {
                    pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
                    String[] permissions = pi.requestedPermissions;
                    ActivityCompat.requestPermissions(StartAppActivity.this, permissions, REQUEST_CODE);
//                    requestPermission();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    if (TextUtils.isEmpty((String) SpUtils.getParam(this, Constant.TOKEN, ""))) {
                        IntentUtils.startActivity(StartAppActivity.this, LoginActivity.class);
                    } else {
                        IntentUtils.startActivity(StartAppActivity.this, MainActivity.class);
                    }
                    finish();

                } else {
                    showWaringDialog();
                }
                return;
            }
        }
    }


    private void showWaringDialog() {
        new AlertDialog.Builder(this)
                .setTitle("警告！")
                .setMessage("请前往设置->应用->sinano->权限中打开相关权限，否则功能无法正常运行！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }


}
