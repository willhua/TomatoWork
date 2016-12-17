package com.willhua.tomatowork.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.ui.view.EnterEditText;
import com.willhua.tomatowork.ui.view.ProprityChooseView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by willhua on 2016/12/18.
 */

public class AddCandyFragment extends BaseFragment {


    @BindView(R.id.eet_add_candy_objecttomato)
    EnterEditText mEetObjectTomato;
    @BindView(R.id.eet_add_candy_type)
    EnterEditText mEetCandyType;
    @BindView(R.id.pc_add_candy_proprity)
    ProprityChooseView mPcvPriority;
    @BindView(R.id.btn_add_candy_add)
    Button mBtnAdd;
    @BindView(R.id.btn_add_candy_cancle)
    Button mBtnCancle;

    private CandyPageFragment mCandyPageFragment;

    void setCandyPageFragment(CandyPageFragment fragment){
        mCandyPageFragment = fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_candy, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
