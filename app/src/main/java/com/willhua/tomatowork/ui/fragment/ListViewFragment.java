package com.willhua.tomatowork.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import butterknife.BindView;

/**
 * Created by willhua on 2016/12/17.
 */

public class ListViewFragment extends BaseFragment {

    private ListView mListView;
    private ListAdapter mListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mListView = new ListView(inflater.getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mListView.setLayoutParams(layoutParams);
        if (mListAdapter != null) {
            mListView.setAdapter(mListAdapter);
        }
        return mListView;
    }

    @Override
    public void onDestroyView() {
        mListAdapter = null;
        super.onDestroyView();
    }

    public void setAdapter(ListAdapter adapter) {
        mListAdapter = adapter;
        if (mListView != null) {
            mListView.setAdapter(adapter);
            mListView.invalidate();
        }
    }
}
