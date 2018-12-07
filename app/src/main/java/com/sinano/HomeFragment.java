package com.sinano;

import android.view.View;

import com.sinano.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    @Override
    public View getContentView() {
        View view = View.inflate(getActivity(),R.layout.fragment_home,null);

        return view;
    }

}
