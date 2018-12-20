package com.sinano.devices.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sinano.R;
import com.sinano.base.BaseFragment;
import com.sinano.devices.view.activity.LocalServerDetailsActivity;
import com.sinano.devices.view.adapter.RcyNoQueryServerAdapter;
import com.sinano.devices.view.adapter.RcyNoQueryTerminalAdapter;
import com.sinano.devices.view.adapter.RcyServerListAdapter;
import com.sinano.utils.IntentUtils;

public class DevicesManageFragment extends BaseFragment implements RcyNoQueryServerAdapter.OnItemClickListener {


    RecyclerView mRcyServerList;
    private RecyclerView mRcyNoQueryServer;
    private RecyclerView mRcyNoQueryQCTerminal;
    private RcyServerListAdapter mAdapter;

    @Override
    public View getContentView() {

        View view = View.inflate(getActivity(), R.layout.fragment_devices, null);

        init(view);

        return view;
    }

    private void init(View view) {

        mRcyServerList = view.findViewById(R.id.rcy_server_list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyServerList.setLayoutManager(manager);
        mAdapter = new RcyServerListAdapter(getActivity(), null);
        mRcyServerList.setAdapter(mAdapter);

        mRcyNoQueryServer = view.findViewById(R.id.rcy_no_query_server);

        GridLayoutManager serverGridManager = new GridLayoutManager(getActivity(), 6) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRcyNoQueryServer.setLayoutManager(serverGridManager);

        RcyNoQueryServerAdapter serverAdapter = new RcyNoQueryServerAdapter(getActivity(), null);
        mRcyNoQueryServer.setAdapter(serverAdapter);
        serverAdapter.setOnItemClickListener(this);


        mRcyNoQueryQCTerminal = view.findViewById(R.id.rcy_no_query_qc_terminal);
        GridLayoutManager terminalGridManager = new GridLayoutManager(getActivity(), 6) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRcyNoQueryQCTerminal.setLayoutManager(terminalGridManager);

        RcyNoQueryTerminalAdapter rcyNoQueryTerminalAdapter = new RcyNoQueryTerminalAdapter(getActivity(), null);
        mRcyNoQueryQCTerminal.setAdapter(rcyNoQueryTerminalAdapter);

    }


    @Override
    public void setOnItemClickListener(View view, int position) {
        Bundle bundle = new Bundle();
        IntentUtils.startActivityForParms(getActivity(), LocalServerDetailsActivity.class, bundle);

    }
}
