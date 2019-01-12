package com.sinano;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sinano.base.BaseActivity;
import com.sinano.devices.model.TypeBean;
import com.sinano.devices.presenter.CommInterface;
import com.sinano.devices.presenter.CommPresenter;
import com.sinano.devices.view.fragment.DevicesManageFragment;
import com.sinano.devices.view.fragment.SupperCompanyDeviceFragment;
import com.sinano.receiver.NetBroadcastReceiver;
import com.sinano.result.view.fragment.CheckResultManageFragment;
import com.sinano.user.view.activity.UserCenterActivity;
import com.sinano.user.view.adapter.RcyVersionInfoAdapter;
import com.sinano.user.view.manage.MyFragment;
import com.sinano.utils.Constant;
import com.sinano.utils.DialogUtils;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.SpUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, CommInterface {


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
    @BindView(R.id.ll_user_center)
    LinearLayout mLLUserCenter;
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    private HomeFragment mHomeFragment;
    private DevicesManageFragment mDevicesFragment;
    private CheckResultManageFragment mCheckResultFragment;
    private MyFragment mMyFragment;
    private int REQUEST_CODE = 12138;
    private SupperCompanyDeviceFragment mSupperCompanyDeviceFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.setPriority(1000);
        registerReceiver(new NetBroadcastReceiver(), filter);


        String userName = (String) SpUtils.getParam(this, Constant.USER_NAME, "");
        mTvUserName.setText(userName);

        mRdGroup.setOnCheckedChangeListener(this);
        mRdMyDevices.setChecked(true);

        CommPresenter presenter = new CommPresenter(this);
        presenter.getType();



    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(new NetBroadcastReceiver());
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
//                if (mSupperCompanyDeviceFragment == null) {
//                    mSupperCompanyDeviceFragment = new SupperCompanyDeviceFragment();
//                    transaction.add(R.id.fragment_container, mSupperCompanyDeviceFragment);
//                } else {
//                    transaction.show(mSupperCompanyDeviceFragment);
//                }

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
        if (mSupperCompanyDeviceFragment != null) {
            transaction.hide(mSupperCompanyDeviceFragment);
        }
        if (mCheckResultFragment != null) {
            transaction.hide(mCheckResultFragment);
        }
        if (mMyFragment != null) {
            transaction.hide(mMyFragment);
        }
    }

    @OnClick({R.id.rl_scan, R.id.ll_user_center})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_scan:

                Intent intent = new Intent(MainActivity.this, CustomCaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.ll_user_center:
                IntentUtils.startActivity(this, UserCenterActivity.class);
                break;
        }

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

    @Override
    public void getTypeSuccess(TypeBean typeBean) {
        switch (typeBean.getCode()) {
            case 200:
                List<TypeBean.DataBean> data = typeBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    SpUtils.putParms(this, data.get(i).getId(), data.get(i).getName());
                }
                break;
        }
    }
}
