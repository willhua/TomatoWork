package com.willhua.tomatowork.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.ui.view.ColorChooserView;
import com.willhua.tomatowork.ui.view.EnterEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by willhua on 2016/12/18.
 */

public class AddCandyFragment extends BaseFragment {


    @BindView(R.id.eet_add_candy_objecttomato)
    EnterEditText mEetObjectTomato;
    @BindView(R.id.eet_add_candy_type)
    EnterEditText mEetCandyType;
    @BindView(R.id.pc_add_candy_proprity)
    ColorChooserView mCcvPriority;
    @BindView(R.id.btn_add_candy_add)
    Button mBtnAdd;
    @BindView(R.id.btn_add_candy_cancle)
    Button mBtnCancle;

    private CandyPageFragment mCandyPageFragment;

    public AddCandyFragment() {
        super();
    }

    void setCandyPageFragment(CandyPageFragment fragment) {
        mCandyPageFragment = fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_candy, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCandyPageFragment != null) {
            mCandyPageFragment.mEtAddCandy.setHint(R.string.add_candy_title);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.btn_add_candy_add)
    public void add(View v) {
        if (mCandyPageFragment != null) {
            Candy candy = null;
            try {
                String title = mCandyPageFragment.mEtAddCandy.getText().toString();
                int priority = mCcvPriority.getChoose();
                int object = Integer.valueOf(mEetObjectTomato.getText().toString());
                int type = Integer.valueOf(mEetObjectTomato.getText().toString());
                candy = new Candy(title);
                candy.setObjectiveTomato(object);
                candy.setPriority(priority);
                candy.setType(type);
            } catch (Exception e) {
                candy = null;
                Toast.makeText(getContext(), R.string.add_input_wrong, Toast.LENGTH_SHORT).show();
                return;
            }
            if (candy != null) {
                mCandyPageFragment.addCandy(candy);
            }
            mCandyPageFragment.changView(false);
            mCandyPageFragment.mEtAddCandy.setHint(R.string.add_candy_new);
        }
    }

    @OnClick(R.id.btn_add_candy_cancle)
    public void cancle(View v) {
        if (mCandyPageFragment != null) {
            mCandyPageFragment.changView(false);
            mCandyPageFragment.mEtAddCandy.setHint(R.string.add_candy_new);
        }
    }
}
