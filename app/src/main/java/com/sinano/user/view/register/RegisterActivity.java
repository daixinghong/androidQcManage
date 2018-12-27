package com.sinano.user.view.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.LoginBean;
import com.sinano.user.presenter.LoginInterface;
import com.sinano.user.presenter.LoginPresenter;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements LoginInterface {

    @BindView(R.id.et_user_name)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_confirm_password)
    EditText mEtConfirmPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_user)
    TextView mTvUser;
    @BindView(R.id.et_nickname)
    EditText mEtNickname;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.register_user));
        mPresenter = new LoginPresenter(this);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_register;
    }


    @Override
    public void loginSuccess(LoginBean loginBean) {

    }

    @Override
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", mEtNickname.getText().toString().trim());
        map.put("password", mEtPassword.getText().toString().trim());
        map.put("phone", mEtPhone.getText().toString().trim());
        map.put("username", mEtUserName.getText().toString().trim());
        return map;
    }

    @Override
    public void registerSuccess(BaseResultBean baseResultBean) {

        switch (baseResultBean.getCode()) {
            case 200:
                ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.register_success));
                finish();
                break;
            default:
                ToastUtils.showTextToast(baseResultBean.getMsg());
                break;
        }

    }

    @Override
    public void logoutSuccess(BaseResultBean baseResultBean) {

    }

    @Override
    public String getUserName() {

        return mEtUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString().trim();
    }

    @OnClick({R.id.btn_login, R.id.tv_user, R.id.rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                if (TextUtils.isEmpty(mEtUserName.getText().toString().trim())) {
                    ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.model_list));
                    return;
                }
                if (TextUtils.isEmpty(mEtPassword.getText().toString().trim())) {
                    return;
                }
                if (TextUtils.isEmpty(mEtConfirmPassword.getText().toString().trim())) {
                    return;
                }
                if (TextUtils.isEmpty(mEtNickname.getText().toString().trim())) {
                    return;
                }
                if (TextUtils.isEmpty(mEtPhone.getText().toString().trim())) {
                    return;
                }

                if (!mEtPassword.getText().toString().trim().equals(mEtConfirmPassword.getText().toString().trim())) {
                    return;
                }

                //注册
                mPresenter.register();

                break;
            case R.id.tv_user:
                //用户协议

                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
