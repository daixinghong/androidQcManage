package com.sinano;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.base.BaseActivity;
import com.sinano.utils.Constant;
import com.sinano.utils.QRCodeUtil;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class ShowQRActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.iv_qr)
    ImageView mIvQr;
    @BindView(R.id.tv_hint)
    TextView mTvHint;
    @BindView(R.id.iv_hint)
    ImageView mIvHint;
    @BindView(R.id.rl_save)
    RelativeLayout mRlSave;
    @BindView(R.id.cv_cardview)
    CardView mCvCardview;
    @BindView(R.id.iv_hint1)
    ImageView mIvHint1;
    @BindView(R.id.tv_hint1)
    TextView mTvHint1;
    @BindView(R.id.cv_cardview1)
    CardView mCvCardview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        Bundle bundleExtra = getIntent().getBundleExtra(Constant.BUNDLE_PARMS);
        mTvTitle.setText(UiUtils.findStringBuId(R.string.scan_result));
        if (bundleExtra != null) {
            String mac = bundleExtra.getString("mac");


            String error = bundleExtra.getString("error");
            if (!TextUtils.isEmpty(mac)) {
                mCvCardview.setVisibility(View.VISIBLE);

                Bitmap qrCodeBitmap = QRCodeUtil.createQRCodeBitmap(mac, 2000, 2000);
                mIvQr.setImageBitmap(qrCodeBitmap);
                mTvHint.setText(UiUtils.findStringBuId(R.string.success));
                mIvHint.setImageResource(R.mipmap.success);
            } else {
                mCvCardview1.setVisibility(View.VISIBLE);
                mTvHint1.setText(error);
                mIvHint1.setImageResource(R.mipmap.fail_hint);
            }
        }

    }

    @Override
    public int getContentView() {
        return R.layout.activity_show_qr;
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
