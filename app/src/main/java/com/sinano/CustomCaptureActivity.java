package com.sinano;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.devices.model.TypeBean;
import com.sinano.devices.presenter.CommInterface;
import com.sinano.devices.presenter.CommPresenter;
import com.sinano.user.model.AppVersionBean;
import com.sinano.user.view.login.LoginActivity;
import com.sinano.utils.FileUtils;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomCaptureActivity extends BaseActivity implements CommInterface {

    @BindView(R.id.rl_album)
    RelativeLayout mRlAlbum;
    private int REQUEST_IMAGE = 12137;
    private CommPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        captureFragment.setCameraInitCallBack(new CaptureFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    Log.e("TAG", "callBack: ", e);
                }
            }
        });

        mPresenter = new CommPresenter(this);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_custom_capture;
    }

    @OnClick(R.id.rl_album)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_album:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
                break;
        }

    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            CustomCaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CustomCaptureActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            CustomCaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CustomCaptureActivity.this.finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE) {   //从相册选取
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver cr = getContentResolver();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图
                    long l = System.currentTimeMillis();
                    File fileDirs = new File("/sdcard/srcImage/" + l + ".png");

                    fileDirs.createNewFile();

                    File bitmapFile = FileUtils.bitmapToFile(mBitmap, fileDirs);

                    CodeUtils.analyzeBitmap(bitmapFile.getPath(), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            if (result.contains("bindCompany:")) {
                                String[] split = result.split(":");
                                mPresenter.bindCompany(split[1]);
                            }

                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(CustomCaptureActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });

                    if (mBitmap != null) {
                        mBitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public void getTypeSuccess(TypeBean typeBean) {

    }

    @Override
    public void getLastAppVersionInfoSuccess(AppVersionBean appVersionBean) {

    }

    @Override
    public void registerDeviceSuccess(BaseResultBean baseResultBean) {

    }

    @Override
    public void bindCompanySuccess(BaseResultBean baseResultBean) {

        if (baseResultBean.getCode() == 200) {
            ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.bind_success_please_reset_login));
            IntentUtils.startActivityAndFinish(this, LoginActivity.class);
        } else {
            ToastUtils.showTextToast(baseResultBean.getMsg());
        }
    }
}
