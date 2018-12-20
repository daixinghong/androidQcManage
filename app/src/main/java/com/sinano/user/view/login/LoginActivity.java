package com.sinano.user.view.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sinano.MainActivity;
import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.LoginBean;
import com.sinano.user.model.RegisterBean;
import com.sinano.user.presenter.LoginInterface;
import com.sinano.user.presenter.LoginPresenter;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;
import com.sinano.websocket.SocketClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginInterface {


    @BindView(R.id.et_input_user_name)
    EditText mEtInputUserName;
    @BindView(R.id.et_input_password)
    EditText mEtInputPassword;
    @BindView(R.id.iv_delete_password)
    ImageView mIvDeletePassword;
    @BindView(R.id.rl_delete)
    RelativeLayout mRlDelete;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    private LoginPresenter mPresenter;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

    }

    private void initData() {

        try {
            SocketClient socketClient = new SocketClient(new URI("ws://192.168.10.161:8765"));
            socketClient.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mPresenter = new LoginPresenter(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }


    @Override
    public void loginSuccess(LoginBean loginBean) {

    }

    @Override
    public Map<String, Object> getMap() {
        return null;
    }

    @Override
    public void registerSuccess(RegisterBean registerBean) {

    }

    @Override
    public void logoutSuccess(BaseResultBean baseResultBean) {

    }

    @Override
    public String getUserName() {
        return mEtInputUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mEtInputPassword.getText().toString().trim();
    }


    @OnClick({R.id.btn_login, R.id.rl_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                if (TextUtils.isEmpty(mEtInputUserName.getText().toString().trim())) {
//                    return;
                }

                if (TextUtils.isEmpty(mEtInputPassword.getText().toString().trim())) {
//                    return;
                }

                //登陆
                IntentUtils.startActivityAndFinish(this, MainActivity.class);

                break;
            case R.id.rl_delete:
                mEtInputPassword.setText("");
                break;
        }
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


}
