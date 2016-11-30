package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.presenter.CandyPresenter;
import com.willhua.tomatowork.ui.IView;
import com.willhua.tomatowork.ui.adapter.CandyAdapter;
import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/11/20.
 */

/**
 * The fragment shown first
 */
public class MainFragment extends Fragment implements IView {
    private static final String TAG = "MainFragment";
    private CandyPresenter mCandyPresenter;
    private ListView mCandyListView;

    public MainFragment(){
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.main_list, null);
        mCandyListView = (ListView)view.findViewById(R.id.task_list);
        mCandyListView.setAdapter(new CandyAdapter(mCandyPresenter.getAllCandies(), mCandyClick, getResources()));

        final EditText et = (EditText)view.findViewById(R.id.new_candy);
        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER){
                    LogUtil.d(TAG, " enter ");
                    mCandyPresenter.addCandy(new Candy(et.getText().toString()));
                }
                return false;
            }
        });

        return view;
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
