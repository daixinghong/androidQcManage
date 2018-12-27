package com.sinano.user.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseActivity;
import com.sinano.base.BaseResultBean;
import com.sinano.user.model.ChildUserBean;
import com.sinano.user.model.UserInfoBean;
import com.sinano.user.presenter.UserManageInterface;
import com.sinano.user.presenter.UserManagePresenter;
import com.sinano.user.view.adapter.RcyUserListAdapter;
import com.sinano.user.view.register.RegisterActivity;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserManageActivity extends BaseActivity implements UserManageInterface {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.rl_save)
    RelativeLayout mRlSave;
    @BindView(R.id.rcy_user_list)
    RecyclerView mRcyUserList;
    private List<ChildUserBean.DataBean> mList = new ArrayList<>();
    private RcyUserListAdapter mAdapter;
    private UserManagePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mPresenter != null)
            mPresenter.getChildUser();
    }

    private void init() {
        mTvTitle.setText(UiUtils.findStringBuId(R.string.user_manage));
        mTvSave.setText(UiUtils.findStringBuId(R.string.register));
        mRlSave.setVisibility(View.VISIBLE);

        mRcyUserList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RcyUserListAdapter(this, mList);
        mRcyUserList.setAdapter(mAdapter);

        mPresenter = new UserManagePresenter(this);
        mPresenter.getChildUser();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_user_manage;
    }

    @OnClick({R.id.rl_back, R.id.rl_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_save:
                IntentUtils.startActivity(this, RegisterActivity.class);
                break;
        }
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
        switch (childUserBean.getCode()) {
            case 200:
                List<ChildUserBean.DataBean> data = childUserBean.getData();
                mList.clear();
                mList.addAll(data);
                mAdapter.notifyDataSetChanged();
                break;
            default:

                break;
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {


    }

    @Override
    public void updataUserInfoSuccess(BaseResultBean baseResultBean) {

    }

}
