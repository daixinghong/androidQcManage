package com.sinano.user.view.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.user.model.LoginBean;
import com.sinano.user.model.RegisterBean;
import com.sinano.user.presenter.LoginInterface;
import com.sinano.user.presenter.LoginPresenter;

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
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

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
    public void registerSuccess(RegisterBean registerBean) {

    }

    @Override
    public String getUserName() {

        return mEtUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {

        return mEtPassword.getText().toString().trim();
    }

    @OnClick({R.id.btn_login, R.id.tv_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                if(TextUtils.isEmpty(mEtUserName.getText().toString().trim())){
                    return;
                }
                if(TextUtils.isEmpty(mEtPassword.getText().toString().trim())){

                }
                if(TextUtils.isEmpty(mEtConfirmPassword.getText().toString().trim())){
                    return;
                }

                if(!mEtPassword.getText().toString().trim().equals(mEtConfirmPassword.getText().toString().trim())){
                    return;
                }

                //注册


                break;
            case R.id.tv_user:

                //用户协议

                break;
        }
    }
}
