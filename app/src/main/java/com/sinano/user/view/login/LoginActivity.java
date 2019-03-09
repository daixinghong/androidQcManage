package com.sinano.user.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.sinano.MainActivity;
import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.LoginBean;
import com.sinano.user.presenter.LoginInterface;
import com.sinano.user.presenter.LoginPresenter;
import com.sinano.utils.Constant;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.SpUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;
import com.sinano.websocket.SocketClient;

import org.java_websocket.handshake.ServerHandshake;

import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
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
    @BindView(R.id.btn_id)
    CircularProgressButton mBtnId;
    private LoginPresenter mPresenter;
    private long exitTime;
    private SocketClient mSocketClient;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

    }

    private void initData() {

        try {
            mSocketClient = new SocketClient(new URI("ws://192.168.1.187:8765"));
            mSocketClient.setOnCallBackListener(new SocketClient.onCallBack() {
                @Override
                public void open(ServerHandshake handshake) {
                    Log.e(TAG, "message: 打开端口");
                    Map<String, Object> map = new HashMap<>();
                    map.put("type", 1);
                    map.put("id", "02:00:00:00:00:00");
                    mSocketClient.send(gson.toJson(map));
                }

                @Override
                public void message(String message) {
                    Log.e(TAG, "message: " + message);
                }

                @Override
                public void close(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception e) {

                }
            });
            mSocketClient.connect();
//            if (!socketClient.isOpen()) {
//                socketClient.connect();
//            } else {
//
//            }
        } catch (Exception e) {
            Log.e(TAG, "initData: " + e.getMessage());
        }

        mEtInputUserName.setText((String) SpUtils.getParam(this, Constant.USER, ""));
        mPresenter = new LoginPresenter(this);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }


    @Override
    public void loginSuccess(LoginBean loginBean) {

        mBtnId.revertAnimation();
        switch (loginBean.getCode()) {
            case 200:
                SpUtils.putParms(this, Constant.TOKEN, loginBean.getData().getToken());
                SpUtils.putParms(this, Constant.USER_NAME, loginBean.getData().getNickname());
                SpUtils.putParms(this, Constant.USER_ID, loginBean.getData().getUid());
                SpUtils.putParms(this, Constant.USER, mEtInputUserName.getText().toString().trim());
                SpUtils.putParms(this, Constant.ADMIN, loginBean.getData().isAdmin());
                IntentUtils.startActivityAndFinish(this, MainActivity.class);
                break;
            default:
                ToastUtils.showTextToast(loginBean.getMsg());
                break;
        }
    }

    @Override
    public Map<String, Object> getMap() {
        return null;
    }

    @Override
    public void registerSuccess(BaseResultBean baseResultBean) {

    }


    @Override
    public void logoutSuccess(BaseResultBean baseResultBean) {

//        mBtnId.stopAnimation();
    }

    @Override
    public String getUserName() {
        return mEtInputUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mEtInputPassword.getText().toString().trim();
    }


    @OnClick({R.id.btn_login, R.id.rl_delete, R.id.btn_id})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                break;
            case R.id.rl_delete:
                mEtInputPassword.setText("");
                break;
            case R.id.btn_id:

//                if (mSocketClient.isOpen()) {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("type", 3);
//                    map.put("id", "02:00:00:00:00:23");
//                    mSocketClient.send(gson.toJson(map));
//                }

                if (TextUtils.isEmpty(mEtInputUserName.getText().toString().trim())) {
                    ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.user_name_no_empty));
                    return;
                }

                if (TextUtils.isEmpty(mEtInputPassword.getText().toString().trim())) {
                    ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.password_no_empty));
                    return;
                }
                mBtnId.startAnimation();
                mPresenter.login();
//                IntentUtils.startActivityAndFinish(this, MainActivity.class);

                break;
        }
    }


    @Override
    public void getDataError(Throwable throwable) {
        if (throwable instanceof UnknownHostException) {
            ToastUtils.showTextToast("网络无连接");
            mBtnId.revertAnimation();
        } else if (throwable instanceof SocketTimeoutException) {
            ToastUtils.showTextToast("请求超时");
            mBtnId.revertAnimation();
        } else {
            ToastUtils.showTextToast("请求出错");
            mBtnId.revertAnimation();
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
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                return true;
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
