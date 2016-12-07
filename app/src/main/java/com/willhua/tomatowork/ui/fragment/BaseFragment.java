package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/12/7.
 */

public class BaseFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d(getClass().getSimpleName(), "onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.d(getClass().getSimpleName(), "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(getClass().getSimpleName(), "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(getClass().getSimpleName(), "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        LogUtil.d(getClass().getSimpleName(), "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        LogUtil.d(getClass().getSimpleName(), "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        LogUtil.d(getClass().getSimpleName(), "onDetach");
        super.onDetach();
    }

    @Override
    public void onPause() {
        LogUtil.d(getClass().getSimpleName(), "onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        LogUtil.d(getClass().getSimpleName(), "onResume");
        super.onResume();
    }

    @Override
    public void onStop() {
        LogUtil.d(getClass().getSimpleName(), "onStop");
        super.onStop();
    }
}
