package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.presenter.CandyPresenter;
import com.willhua.tomatowork.ui.activity.MainActivity;
import com.willhua.tomatowork.ui.iview.ICandyListView;
import com.willhua.tomatowork.ui.adapter.CandyAdapter;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by willhua on 2016/11/20.
 */

/**
 * The fragment shown first
 */
public class CandyPageFragment extends BaseFragment implements ICandyListView {

    private static final String TAG = "CandyPageFragment";

    @BindView(R.id.new_item)
    EditText mEtAddCandy;

    private CandyPresenter mCandyPresenter;
    private CandyAdapter mCandyAdapter;
    private static boolean mAddStatus = false;
    private ListViewFragment mListViewFragment;

    public CandyPageFragment() {
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
    public void onDestroyView() {
        super.onDestroyView();
        mCandyPresenter.onViewDestory();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.candy_list_shower, null);
        ButterKnife.bind(this, view);
/*        mListViewFragment = new ListViewFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.candy_shower_container, mListViewFragment);
        ft.commit();*/
        changView(mAddStatus);
        mCandyPresenter.onViewCreate();
        mCandyPresenter.showUnfinishedCandies();
        return view;
    }

    @OnClick(R.id.new_item)
    public void addNewCandy(final EditText view) {
        if (!mAddStatus) {
            changView(true);
        }
    }

    public void addCandy(Candy candy) {
        mCandyPresenter.addCandy(candy);
    }

    public void changView(boolean addView) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.add_item_up, R.anim.add_item_down);
        if (addView) {
            mEtAddCandy.setFocusable(true);
            mEtAddCandy.setFocusableInTouchMode(true);
            mEtAddCandy.requestFocus();
            ((MainActivity) getActivity()).requestHideFab(true);
            AddCandyFragment addCandyFragment = new AddCandyFragment();
            addCandyFragment.setCandyPageFragment(this);
            ft.replace(R.id.candy_shower_container, addCandyFragment);
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEtAddCandy, 0);
        } else {
            mEtAddCandy.setFocusable(false);
            mEtAddCandy.setFocusableInTouchMode(false);
            ((MainActivity) getActivity()).requestHideFab(false);
            if (mListViewFragment == null) {
                mListViewFragment = new ListViewFragment();
            }
            mListViewFragment.setAdapter(mCandyAdapter);
            ft.replace(R.id.candy_shower_container, mListViewFragment);
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEtAddCandy.getWindowToken(), 0);
        }
        mAddStatus = addView;
        ft.commit();
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
        mCandyAdapter = new CandyAdapter(candies, mCandyClick, getResources());
        if (mListViewFragment != null) {
            mListViewFragment.setAdapter(mCandyAdapter);
        }
    }
}
