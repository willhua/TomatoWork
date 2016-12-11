package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.core.Tomato;
import com.willhua.tomatowork.presenter.CandyPresenter;
import com.willhua.tomatowork.ui.iview.ICandyListView;
import com.willhua.tomatowork.ui.adapter.CandyAdapter;
import com.willhua.tomatowork.ui.view.AddCandyPopupWindow;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.ArrayList;
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
    @BindView(R.id.item_list) ListView mCandyListView;
    @BindView(R.id.new_item)
    EditText mNewCandy;
    @BindView(R.id.fab_start)
    FloatingActionButton mFabStart;

    private List<Candy> mUnfinishedCandy = new ArrayList<>();
    private CandyAdapter mCandyAdapter;

    public CandyListFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCandyPresenter = new CandyPresenter(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_list, null);
        ButterKnife.bind(this, view);
        mFabStart.requestFocus();
        mCandyPresenter.showUnfinishedCandies();
        return view;
    }

    @OnTouch(R.id.new_item)
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
                    mUnfinishedCandy.add(0, candy);
                    LogUtil.d(TAG, " ondisss mUnfinishedCandy " + mUnfinishedCandy.size());
                    mCandyListView.invalidate();
                    mCandyListView.setAdapter(new CandyAdapter(mUnfinishedCandy, mCandyClick, getResources()));
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
