package com.willhua.tomatowork.ui.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.modle.entity.Tomato;
import com.willhua.tomatowork.presenter.CandyPresenter;
import com.willhua.tomatowork.ui.iview.ICandyListView;
import com.willhua.tomatowork.ui.activity.MainActivity;
import com.willhua.tomatowork.ui.adapter.CandyAdapter;
import com.willhua.tomatowork.ui.view.AddCandyPopupWindow;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * Created by willhua on 2016/11/20.
 */

/**
 * The fragment shown first
 */
public class CandyListFragment extends BaseFragment implements ICandyListView {

    private static final String TAG = "CandyListFragment";
    private CandyPresenter mCandyPresenter;
    @BindView(R.id.task_list) ListView mCandyListView;
    @BindView(R.id.new_candy)
    EditText mNewCandy;
    @BindView(R.id.fab_start)
    FloatingActionButton mFabStart;

    private List<Candy> mUnfinishedCandy;

    public CandyListFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCandyPresenter = new CandyPresenter(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tomato_list, null);
        ButterKnife.bind(this, view);
        mFabStart.requestFocus();
        mCandyPresenter.showUnfinishedCandies();
        return view;
    }

    @OnTouch(R.id.new_candy)
    public boolean addNewCandy(final EditText view) {
        if (view.getVisibility() == View.VISIBLE) {
            LogUtil.d(TAG, "new candy click");
            view.setVisibility(View.INVISIBLE);
            Context context = CandyListFragment.this.getContext();
            final AddCandyPopupWindow pop = new AddCandyPopupWindow(context, mNewCandy);
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    view.setVisibility(View.VISIBLE);
                    Candy candy = new Candy(pop.getCandyTitle());
                    mCandyPresenter.addCandy(candy);
                    mUnfinishedCandy.add(candy);
                    mCandyListView.invalidate();
                }
            });
            pop.showAtLocation(CandyListFragment.this.getView().getRootView(), Gravity.NO_GRAVITY, 0, 0);
        }
        return true;
    }

    @OnClick(R.id.fab_start)
    public void startTomato(View view) {
        LogUtil.d(TAG, "fab click");
        if (!Tomato.getInstance().isDuringTomato()) {
            LogUtil.d(TAG, "fab click 2");
            Tomato.getInstance().startTomato();
        }
    }

    private CandyAdapter.CandyClick mCandyClick = new CandyAdapter.CandyClick() {
        @Override
        public void onDone(int position, boolean checked) {

        }

        @Override
        public void onStick(int position, boolean checked) {

        }
    };


    @Override
    public void onFinishedCandyQueried(List<Candy> candies) {

    }

    @Override
    public void onUnfinishedCandyQueried(List<Candy> candies) {
        LogUtil.d(TAG, "onUnfinishedCandyQueried size:" + candies.size());
        mUnfinishedCandy = candies;
        mCandyListView.setAdapter(new CandyAdapter(mUnfinishedCandy, mCandyClick, getResources()));
        mCandyListView.invalidate();
    }
}
