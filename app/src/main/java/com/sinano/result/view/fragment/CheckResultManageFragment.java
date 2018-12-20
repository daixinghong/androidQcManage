package com.sinano.result.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.base.BaseFragment;
import com.sinano.result.view.activity.TerminalResultDetailActivity;
import com.sinano.result.view.adapter.RcyCheckResultAdapter;
import com.sinano.result.view.adapter.RcyTerminalListAdapter;
import com.sinano.utils.IntentUtils;

public class CheckResultManageFragment extends BaseFragment implements RcyTerminalListAdapter.OnItemClickListener {

    private TextView mTvAllCount;
    private TextView mTvType;
    private TextView mTvProjectCount;
    private RecyclerView mRcyProjectList;
    private RcyCheckResultAdapter mAdapter;
    private RecyclerView mRcyDeviceList;
    private RcyTerminalListAdapter mTerminalListAdapter;
    private TextView mTvServerCount;

    @Override
    public View getContentView() {

        View view = View.inflate(getActivity(), R.layout.fragment_result, null);

        initView(view);

        initData();

        initEvent();

        return view;
    }

    private void initEvent() {
        mTerminalListAdapter.setOnItemClickListener(this);
    }

    private void initData() {

        mAdapter = new RcyCheckResultAdapter(getActivity(), null);
        mRcyProjectList.setAdapter(mAdapter);

        mTerminalListAdapter = new RcyTerminalListAdapter(getActivity(), null);
        mRcyDeviceList.setAdapter(mTerminalListAdapter);

    }

    private void initView(View view) {

        mTvProjectCount = view.findViewById(R.id.tv_terminal_count);
        mTvServerCount = view.findViewById(R.id.tv_server_count);
        mTvType = view.findViewById(R.id.tv_type);
        mTvAllCount = view.findViewById(R.id.tv_all_count);
        mRcyProjectList = view.findViewById(R.id.rcy_project_list);
        mRcyDeviceList = view.findViewById(R.id.rcy_device_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyDeviceList.setLayoutManager(gridLayoutManager);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyProjectList.setLayoutManager(manager);
    }


    @Override
    public void setOnItemClickListener(View view, int position) {
        Bundle bundle = new Bundle();
        IntentUtils.startActivityForParms(getActivity(), TerminalResultDetailActivity.class, bundle);
    }
}
