package com.sinano.user.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.utils.Constant;
import com.sinano.utils.QRCodeUtil;
import com.sinano.utils.RsaUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MyQRActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.rl_save)
    RelativeLayout mRlSave;
    private String mId;
    private Bitmap mQrCodeBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        mRlSave.setVisibility(View.VISIBLE);
        mTvTitle.setText(UiUtils.findStringBuId(R.string.qr_code_card));

        Bundle bundleExtra = getIntent().getBundleExtra(Constant.BUNDLE_PARMS);
        mId = bundleExtra.getString(Constant.ID);
        try {
            String encrypt = RsaUtils.encrypt(mId, Constant.PUBLIC_PEM);
            encrypt = "bindCompany:" + encrypt;
            mQrCodeBitmap = QRCodeUtil.createQRCodeBitmap(encrypt, 2000, 2000);
            mIvCode.setImageBitmap(mQrCodeBitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getContentView() {
        return R.layout.activity_my_qr;
    }

    @OnClick({R.id.rl_back, R.id.rl_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_save:      //保存图片到手机
                try {
                    if (mQrCodeBitmap != null)
                        MediaStore.Images.Media.insertImage(getContentResolver(), mQrCodeBitmap, "title", "description");
                    ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.save_success));
                } catch (Exception e) {

                }


                break;
        }
    }
}
