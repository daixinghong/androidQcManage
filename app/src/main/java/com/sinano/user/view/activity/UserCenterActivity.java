package com.sinano.user.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.ChildUserBean;
import com.sinano.user.model.LoginBean;
import com.sinano.user.model.UserInfoBean;
import com.sinano.user.presenter.LoginInterface;
import com.sinano.user.presenter.LoginPresenter;
import com.sinano.user.presenter.UserManageInterface;
import com.sinano.user.presenter.UserManagePresenter;
import com.sinano.user.view.login.LoginActivity;
import com.sinano.utils.Constant;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.SpUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;
import com.sinano.weight.DialogCollection;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserCenterActivity extends BaseActivity implements LoginInterface, UserManageInterface {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.rl_save)
    RelativeLayout mRlSave;
    @BindView(R.id.tv_user)
    TextView mTvUserName;
    @BindView(R.id.ll_user_manage)
    LinearLayout mLLUserManage;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout mRlPhone;
    @BindView(R.id.tv_server_count)
    TextView mTvServerCount;
    @BindView(R.id.tv_terminal_count)
    TextView mTvTerminalCount;
    @BindView(R.id.ll_about)
    LinearLayout mLlAbout;
    @BindView(R.id.tv_contact_phone)
    TextView mTvContactPhone;
    @BindView(R.id.rl_contact)
    RelativeLayout mRlContact;
    @BindView(R.id.rl_user)
    RelativeLayout mRlUser;
    @BindView(R.id.rl_logout)
    RelativeLayout mRlLogout;
    @BindView(R.id.ll_updata_password)
    LinearLayout mLlUpdataPassword;
    @BindView(R.id.tv_company_name)
    TextView mTvCompanyName;
    @BindView(R.id.et_nick_name)
    EditText mEtNickName;
    @BindView(R.id.ll_device)
    LinearLayout mLlDevice;
    @BindView(R.id.ll_son_manage)
    LinearLayout mLlSonManage;
    private LoginPresenter mPresenter;
    private UserManagePresenter mUserManagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        mTvTitle.setText(UiUtils.findStringBuId(R.string.user_center));
        mPresenter = new LoginPresenter(this);
        mRlSave.setVisibility(View.VISIBLE);

        if (!(boolean) SpUtils.getParam(this, Constant.ADMIN, false)) {
            mLlSonManage.setVisibility(View.GONE);
            mLlDevice.setVisibility(View.GONE);
        }

        mUserManagePresenter = new UserManagePresenter(this);

        int id = (int) SpUtils.getParam(this, Constant.USER_ID, 0);
        mUserManagePresenter.getUserInfo(id);


    }

    @Override
    public int getContentView() {
        return R.layout.activity_user_center;
    }

    @OnClick({R.id.rl_back, R.id.ll_user_manage, R.id.rl_phone, R.id.ll_about,
            R.id.rl_contact, R.id.rl_user, R.id.rl_logout, R.id.ll_updata_password,
            R.id.rl_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_user_manage:
                Bundle bundle = new Bundle();
                IntentUtils.startActivityForParms(this, UserManageActivity.class, bundle);
                break;
            case R.id.ll_about:

                break;
            case R.id.rl_contact:

                break;
            case R.id.rl_user:

                break;
            case R.id.rl_logout:
                DialogCollection.getInstance().showDoubleDialog(this, getResources().getString(R.string.confirm_logout), "", getResources().getString(R.string.cancel), getResources().getString(R.string.confirm), new DialogCollection.DialogCallBack() {
                    @Override
                    public void onCancel(Dialog dialog) {
                    }

                    @Override
                    public void onConfirm(Dialog dialog) {
                        dialog.dismiss();
                        mPresenter.logout();
                    }
                });

                break;
            case R.id.ll_updata_password:
                IntentUtils.startActivity(this, UpdataPasswordActivity.class);
                break;
            case R.id.rl_save:

                if (TextUtils.isEmpty(mEtNickName.getText().toString().trim())) {
                    ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.nickname_no_empty));
                    return;
                }
                if (TextUtils.isEmpty(mEtPhone.getText().toString().trim())) {
                    ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.phone_no_empty));
                    return;
                }
                mUserManagePresenter.updataUserInfo(mEtNickName.getText().toString().trim(), mEtPhone.getText().toString().trim());

                break;
        }
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {

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
        switch (baseResultBean.getCode()) {
            case 200:
                ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.logout_success));
                SpUtils.putParms(this, Constant.TOKEN, "");
                IntentUtils.startActivity(this, LoginActivity.class);
                break;
        }
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getOldPassword() {
        return null;
    }

    @Override
    public String getNewPassword() {
        return null;
    }

    @Override
    public void updataPasswordSuccess(BaseResultBean baseResultBean) {

    }

    @Override
    public void getChildUserSuccess(ChildUserBean childUserBean) {

    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {
        switch (userInfoBean.getCode()) {
            case 200:
                UserInfoBean.DataBean data = userInfoBean.getData();
                mTvUserName.setText(data.getUsername());
                mEtPhone.setText(data.getPhone());
                mTvCompanyName.setText(data.getCompanyName());
                mEtNickName.setText(data.getNickname());
                break;
            default:
                ToastUtils.showTextToast(userInfoBean.getMsg());
                break;
        }
    }

    @Override
    public void updataUserInfoSuccess(BaseResultBean baseResultBean) {
        switch (baseResultBean.getCode()) {
            case 200:
                ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.updata_success));
                finish();
                break;
            default:
                ToastUtils.showTextToast(baseResultBean.getMsg());
                break;
        }

    }
}
