package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.willhua.tomatowork.R;

/**
 * Created by willhua on 2016/11/20.
 */

public class TabFragment extends Fragment {
    public interface TabSelected{
        void onStartTomato();
        void onShowCandy();
        void onShowStatistics();
        void onShowUser();
    }

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
        setClick(view);
        return  view;
    }

    private void setClick(View root){
        View view = root.findViewById(R.id.list);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTabSelected != null){
                    mTabSelected.onShowCandy();
                }
            }
        });
        view = root.findViewById(R.id.statistics);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTabSelected != null){
                    mTabSelected.onShowStatistics();
                }
            }
        });
        view = root.findViewById(R.id.user);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTabSelected != null){
                    mTabSelected.onShowUser();
                }
            }
        });
    }


}
