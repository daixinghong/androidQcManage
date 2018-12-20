package com.sinano;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sinano.base.BaseActivity;
import com.sinano.devices.view.fragment.DevicesManageFragment;
import com.sinano.result.view.fragment.CheckResultManageFragment;
import com.sinano.user.view.manage.MyFragment;
import com.sinano.utils.Constant;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.rb_work)
    RadioButton mRbWork;
    @BindView(R.id.rd_my_devices)
    RadioButton mRdMyDevices;
    @BindView(R.id.rb_hint)
    RadioButton mRbHint;
    @BindView(R.id.rd_group)
    RadioGroup mRdGroup;
    @BindView(R.id.rl_scan)
    LinearLayout mRlScan;
    @BindView(R.id.rb_my)
    RadioButton mRbMy;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    private HomeFragment mHomeFragment;
    private DevicesManageFragment mDevicesFragment;
    private CheckResultManageFragment mCheckResultFragment;
    private MyFragment mMyFragment;
    private int REQUEST_CODE = 12138;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        mRdGroup.setOnCheckedChangeListener(this);
        mRbWork.setChecked(true);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.press2finsh));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (i) {
            case R.id.rb_work:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.fragment_container, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }

                break;
            case R.id.rd_my_devices:
                if (mDevicesFragment == null) {
                    mDevicesFragment = new DevicesManageFragment();
                    transaction.add(R.id.fragment_container, mDevicesFragment);
                } else {
                    transaction.show(mDevicesFragment);
                }

                break;
            case R.id.rb_hint:
                if (mCheckResultFragment == null) {
                    mCheckResultFragment = new CheckResultManageFragment();
                    transaction.add(R.id.fragment_container, mCheckResultFragment);
                } else {
                    transaction.show(mCheckResultFragment);
                }

                break;
            case R.id.rb_my:
                if (mMyFragment == null) {
                    mMyFragment = new MyFragment();
                    transaction.add(R.id.fragment_container, mMyFragment);
                } else {
                    transaction.show(mMyFragment);
                }

                break;
        }

        transaction.commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Bundle bundleExtra = getIntent().getBundleExtra(Constant.BUNDLE_PARMS);
        int index = 0;
        if (bundleExtra != null) {
            index = bundleExtra.getInt(Constant.KEY, 1);
        }
        switch (index) {
            case 0:
                mRbWork.setChecked(true);
                break;
            case 1:
                mRdMyDevices.setChecked(true);
                break;
            case 2:
                mRbHint.setChecked(true);
                break;
            case 3:
                mRbMy.setChecked(true);
                break;

        }
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mDevicesFragment != null) {
            transaction.hide(mDevicesFragment);
        }
        if (mCheckResultFragment != null) {
            transaction.hide(mCheckResultFragment);
        }
        if (mMyFragment != null) {
            transaction.hide(mMyFragment);
        }
    }

    @OnClick({R.id.rl_scan})
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, CustomCaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
