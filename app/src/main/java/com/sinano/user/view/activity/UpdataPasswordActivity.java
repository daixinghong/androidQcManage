package com.sinano.user.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.ChildUserBean;
import com.sinano.user.model.UserInfoBean;
import com.sinano.user.presenter.UserManageInterface;
import com.sinano.user.presenter.UserManagePresenter;
import com.sinano.user.view.login.LoginActivity;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdataPasswordActivity extends BaseActivity implements UserManageInterface {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.rl_save)
    RelativeLayout mRlSave;
    @BindView(R.id.et_origin_pw)
    EditText mEtOriginPw;
    @BindView(R.id.et_new_pw)
    EditText mEtNewPw;
    @BindView(R.id.et_confirm_pw)
    EditText mEtConfirmPw;
    private UserManagePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();


    }

    private void init() {
        mRlSave.setVisibility(View.VISIBLE);
        mTvTitle.setText(UiUtils.findStringBuId(R.string.updata_password));

        mPresenter = new UserManagePresenter(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_updata_password;
    }

    @OnClick({R.id.rl_back, R.id.rl_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_save:

                if (TextUtils.isEmpty(mEtOriginPw.getText().toString().trim())) {
                    return;
                }
                if (TextUtils.isEmpty(mEtNewPw.getText().toString().trim())) {
                    return;
                }
                if (TextUtils.isEmpty(mEtConfirmPw.getText().toString().trim())) {
                    return;
                }
                if (!mEtNewPw.getText().toString().trim().equals(mEtConfirmPw.getText().toString().trim())) {
                    return;
                }

                mPresenter.updataPassword();

                break;
        }
    }

    @Override
    public String getOldPassword() {
        return mEtOriginPw.getText().toString().trim();
    }

    @Override
    public String getNewPassword() {
        return mEtNewPw.getText().toString().trim();
    }

    @Override
    public void updataPasswordSuccess(BaseResultBean baseResultBean) {
        switch (baseResultBean.getCode()) {
            case 200:
                ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.updata_password_success));
                IntentUtils.startActivity(this, LoginActivity.class);
                finish();
                break;
            default:
                ToastUtils.showTextToast(baseResultBean.getMsg());
                break;
        }

    }

    @Override
    public void getChildUserSuccess(ChildUserBean childUserBean) {

    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {

    }

    @Override
    public void updataUserInfoSuccess(BaseResultBean baseResultBean) {

    }


}
