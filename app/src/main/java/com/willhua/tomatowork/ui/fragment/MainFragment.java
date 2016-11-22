package com.willhua.tomatowork.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.presenter.CandyPresenter;
import com.willhua.tomatowork.ui.adapter.CandyAdapter;

/**
 * Created by willhua on 2016/11/20.
 */

/**
 * The fragment shown first
 */
public class MainFragment extends Fragment {
    private CandyPresenter mCandyPresenter = new CandyPresenter();
    private ListView mCandyListView;

    public MainFragment(){
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.main_list, null);
        mCandyListView = (ListView)view.findViewById(R.id.task_list);
        mCandyListView.setAdapter(new CandyAdapter(mCandyPresenter.getCandies(), getContext()));
        return view;
    }
}
