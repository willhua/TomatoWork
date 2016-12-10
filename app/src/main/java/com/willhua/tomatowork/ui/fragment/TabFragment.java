package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willhua.tomatowork.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by willhua on 2016/11/20.
 */

public class TabFragment extends BaseFragment {
    public interface TabSelected{
        void onShowCandy();
        void onShowNote();
        void onShowStatistics();
        void onShowUser();
    }

    @BindView(R.id.tab_candy_list)
    TextView mCandyList;
    @BindView(R.id.tab_note_list)
    TextView mNoteList;
    @BindView(R.id.tab_statistics)
    TextView mStatistics;
    @BindView(R.id.tab_user)
    TextView mUser;

    private TabSelected mTabSelected;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  TabSelected){
            mTabSelected = (TabSelected)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tab_layout, null);
        ButterKnife.bind(this, view);
        return  view;
    }

    @OnClick(R.id.tab_candy_list)
    public void showCandy(){
        mTabSelected.onShowCandy();
    }

    @OnClick(R.id.tab_note_list)
    public void showNote(){
        mTabSelected.onShowNote();
    }

    @OnClick(R.id.tab_statistics)
    public void showStatistics(){
        mTabSelected.onShowStatistics();
    }

    @OnClick(R.id.tab_user)
    public void showUser(){
        mTabSelected.onShowUser();
    }
}
