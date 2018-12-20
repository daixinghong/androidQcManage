package com.sinano.result.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;

import com.sinano.R;

import java.util.List;

public class ElResultDetailAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mList;

    public ElResultDetailAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }


    @Override
    public int getGroupCount() {
        return 5;
    }

    @Override
    public int getChildrenCount(int i) {
        return 5;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.el_group_item, null);
            holder = new GroupHolder(view);
            view.setTag(holder);
        } else {
            holder = (GroupHolder) view.getTag();
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int position, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder childViewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.el_result_child_item, viewGroup, false);
            childViewHolder = new ChildHolder(view);
            view.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildHolder) view.getTag();
        }

        if (position == 0) {
            childViewHolder.mLlContainer.setVisibility(View.VISIBLE);
        }else{
            childViewHolder.mLlContainer.setVisibility(View.GONE);
        }
        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public class GroupHolder {

        public GroupHolder(View itemView) {

        }
    }

    public class ChildHolder {

        private final LinearLayout mLlContainer;

        public ChildHolder(View itemView) {
            mLlContainer = itemView.findViewById(R.id.ll_container);

        }
    }

}
