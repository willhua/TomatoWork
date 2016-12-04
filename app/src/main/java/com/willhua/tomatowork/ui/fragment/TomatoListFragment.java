package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.modle.entity.Tomato;
import com.willhua.tomatowork.presenter.CandyPresenter;
import com.willhua.tomatowork.ui.IView;
import com.willhua.tomatowork.ui.adapter.CandyAdapter;
import com.willhua.tomatowork.ui.view.AddCandyPopupWindow;
import com.willhua.tomatowork.utils.LogUtil;

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
public class TomatoListFragment extends Fragment implements IView {


    private static final String TAG = "TomatoListFragment";
    private CandyPresenter mCandyPresenter;
    private ListView mCandyListView;
    @BindView(R.id.new_candy)
    EditText mNewCandy;
    @BindView(R.id.fab_start)
    FloatingActionButton mFabStart;

    public TomatoListFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCandyPresenter = new CandyPresenter(this, getContext().getApplicationContext());
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
        mCandyListView = (ListView) view.findViewById(R.id.task_list);
        mCandyListView.setAdapter(new CandyAdapter(mCandyPresenter.getAllCandies(), mCandyClick, getResources()));
        return view;
    }

    @OnTouch(R.id.new_candy)
    public boolean addNewCandy(final EditText view) {
        if (view.getVisibility() == View.VISIBLE) {
            LogUtil.d(TAG, "new candy click");
            view.setVisibility(View.INVISIBLE);
            Context context = TomatoListFragment.this.getContext();
            final AddCandyPopupWindow pop = new AddCandyPopupWindow(context, mNewCandy);
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    view.setVisibility(View.VISIBLE);
                    Candy candy = new Candy(pop.getCandyTitle());
                    mCandyPresenter.addCandy(candy);
                    LogUtil.d(TAG, "pop candy:" + candy.getTitle());
                }
            });
            pop.showAtLocation(TomatoListFragment.this.getView().getRootView(), Gravity.NO_GRAVITY, 0, 0);
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


}
