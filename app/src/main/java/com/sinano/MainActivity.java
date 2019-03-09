package com.sinano;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.beiing.flikerprogressbar.FlikerProgressBar;
import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.devices.model.TypeBean;
import com.sinano.devices.presenter.CommInterface;
import com.sinano.devices.presenter.CommPresenter;
import com.sinano.devices.view.fragment.DevicesManageFragment;
import com.sinano.devices.view.fragment.SupperCompanyDeviceFragment;
import com.sinano.receiver.NetBroadcastReceiver;
import com.sinano.result.view.activity.ClothBadTypeCountDetailActivity;
import com.sinano.result.view.fragment.CheckResultManageFragment;
import com.sinano.user.model.AppVersionBean;
import com.sinano.user.view.activity.UserCenterActivity;
import com.sinano.user.view.manage.MyFragment;
import com.sinano.utils.APKVersionCodeUtils;
import com.sinano.utils.Constant;
import com.sinano.utils.DialogUtils;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.OSUtils;
import com.sinano.utils.SpUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.text.NumberFormat;
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
    private FlikerProgressBar mBar;
    private TextView mTvStop;
    private final String DOWNLOAD_PATH = "/sdcard/sinano/apk";
    private Dialog mUpdateAppDialog;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    private boolean mCancle;
    private boolean mStop;
    private boolean mStatus;
    private CommPresenter mPresenter;

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
        nf.setMaximumFractionDigits(2);

        Aria.download(this).register();

        String userName = (String) SpUtils.getParam(this, Constant.USER_NAME, "");
        mTvUserName.setText(userName);

        mRdGroup.setOnCheckedChangeListener(this);
        mRdMyDevices.setChecked(true);

        mPresenter = new CommPresenter(this);
        mPresenter.getType();
        mPresenter.getLastVersionInfo();


    }

    @Override
    protected void onResume() {
        super.onResume();
        String userName = (String) SpUtils.getParam(this, Constant.USER_NAME, "");
        mTvUserName.setText(userName);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

                    boolean isAdmin = (boolean) SpUtils.getParam(this, Constant.ADMIN, false);

                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    if (result.contains("md5")) {       //查看布匹信息
                        Bundle bundles = new Bundle();
                        bundles.putString("md5", "c962694bbb265e3d797a353f91f371a3");
                        IntentUtils.startActivityForParms(this, ClothBadTypeCountDetailActivity.class, bundles);
                    } else {        //设备注册
                        if (isAdmin) {
                            mPresenter.registerDevice(result);
                        } else {
                            bundle.putString("error", UiUtils.findStringBuId(R.string.no_permission));
                            IntentUtils.startActivityForParms(this, ShowQRActivity.class, bundle);
                        }

                    }
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

    @Override
    public void getLastAppVersionInfoSuccess(AppVersionBean appVersionBean) {
        switch (appVersionBean.getCode()) {
            case 200:
                AppVersionBean.DataBean data = appVersionBean.getData();
                String versionNo = data.getVersionNo();
                int versionCode = APKVersionCodeUtils.getVersionCode(this);

                if (versionCode < Integer.parseInt(versionNo)) {    //有新版本
                    showDialog(data);
                }
                break;
            default:
                ToastUtils.showTextToast(appVersionBean.getMsg());
                break;
        }
    }

    @Override
    public void registerDeviceSuccess(BaseResultBean baseResultBean) {

        Bundle bundle = new Bundle();
        switch (baseResultBean.getCode()) {
            case 200:
                String mac = (String) baseResultBean.getData();
                bundle.putString("mac", mac);
                IntentUtils.startActivityForParms(this, ShowQRActivity.class, bundle);
                break;
            default:
                bundle.putString("error", baseResultBean.getMsg());
                IntentUtils.startActivityForParms(this, ShowQRActivity.class, bundle);
                break;
        }


    }

    @Override
    public void bindCompanySuccess(BaseResultBean baseResultBean) {
        Bundle bundle = new Bundle();
        bundle.putString("error", baseResultBean.getMsg());
        IntentUtils.startActivityForParms(this, ShowQRActivity.class, bundle);
    }

    public void showDialog(AppVersionBean.DataBean dataBean) {

        View views = View.inflate(this, R.layout.dialog_updialog_view, null);
        TextView tvTitle = views.findViewById(R.id.tv_title);
        mBar = views.findViewById(R.id.flikerbar);
        TextView tvSize = views.findViewById(R.id.tv_size);
        TextView tvCanCle = views.findViewById(R.id.tv_cancle);
        mTvStop = views.findViewById(R.id.tv_stop);
        String remarks = dataBean.getRemarks();
        TextView tvInfo = views.findViewById(R.id.tv_info);
        tvInfo.setText(remarks);
        RelativeLayout rlAfter = views.findViewById(R.id.rl_after);
        RelativeLayout rlUpgrade = views.findViewById(R.id.rl_upgrade);

        File file = new File(DOWNLOAD_PATH);
        if (!file.exists()) {
            file.mkdir();
        }

        tvSize.setText("大小约:" + nf.format(dataBean.getSize() / 1024 / 1024) + "m");
        tvTitle.setText(UiUtils.findStringBuId(R.string.is_update_to) + dataBean.getName());

        mUpdateAppDialog = DialogUtils.createUpdateAppDialog(views);


        if (dataBean.getForcedUpdate() == 0) {
            mStatus = true;
        }

        mUpdateAppDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (mStatus) {
                    finish();
                }
            }
        });
        rlAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mCancle) {
                    Aria.download(this).load(dataBean.getUri()).cancel();
                }
                if (mStatus) {  //强制更新
                    finish();
                }
                mCancle = !mCancle;
                mUpdateAppDialog.dismiss();
            }
        });

        rlUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = dataBean.getUri();
                Log.e(TAG, "onClick: " + uri);
                if (!mStop) {
                    mBar.setVisibility(View.VISIBLE);
                    tvCanCle.setText(UiUtils.findStringBuId(R.string.cancel));
                    mTvStop.setText(UiUtils.findStringBuId(R.string.stop));
                    Aria.download(this)
                            .load("http://" + dataBean.getUri())
                            .setDownloadPath(DOWNLOAD_PATH + "/" + dataBean.getName() + ".apk")
                            .start();
                } else {
                    Aria.download(this).load(dataBean.getUri()).stop();
                    mTvStop.setText(UiUtils.findStringBuId(R.string.keep));
                }
                mStop = !mStop;

            }
        });

    }

    @Download.onTaskRunning
    protected void running(DownloadTask task) {
        Log.e(TAG, "running: " + task.getPercent());
        mBar.setProgress(task.getPercent());
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        //在这里处理任务完成的状态
        mUpdateAppDialog.dismiss();
        File file = new File(task.getDownloadPath());
        OSUtils.installAPK(this, file);
    }

    @Download.onTaskFail
    void taskfail(DownloadTask task) {
        //在这里处理任务完成的状态
        ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.download_fail));
        mTvStop.setText(UiUtils.findStringBuId(R.string.keep));
        mStop = false;
    }

}
