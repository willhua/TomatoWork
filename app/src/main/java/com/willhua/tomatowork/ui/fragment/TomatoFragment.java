package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.presenter.TomatoPresenter;
import com.willhua.tomatowork.ui.iview.IStatisticsView;
import com.willhua.tomatowork.ui.view.BezierView;
import com.willhua.tomatowork.utils.LogUtil;
import com.willhua.tomatowork.utils.Utils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by willhua on 2016/11/20.
 */

public class TomatoFragment extends BaseFragment implements IStatisticsView{
    private static final String TAG = "TomatoFragment";
    private TomatoPresenter mPresenter;

    @BindView(R.id.stat_bezierview)
    BezierView mBezierView;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TomatoPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.statistics_fragment, null);
        ButterKnife.bind(this, view);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        mPresenter.getMouthTomato(year, month);
        return  view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMouthTomatoGot(int year, int mouth, int[] data) {
        LogUtil.d(TAG, "onMouthTomatoGot SIZE " + data.length);
        mBezierView.setPoints(Utils.intArrayToFloatArray(data));
        mBezierView.setYRange(0, 20);
        mBezierView.invalidate();
    }
}
