package com.willhua.tomatowork.ui.fragment;

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
        void onTabSelected(int index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tab_layout, null);
        return  view;
    }


}
