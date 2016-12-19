package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.core.Tomato;
import com.willhua.tomatowork.ui.view.EnterEditText;
import com.willhua.tomatowork.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by willhua on 2016/11/20.
 */

public class UserFragment extends BaseFragment {


    @BindView(R.id.eet_user_set_tomato_time)
    EnterEditText mEetTomatoTime;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.user_set, null);
        ButterKnife.bind(this, view);
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int time = pref.getInt(Constants.PREF_TOMATO_TIME, 25 * Constants.MIN_TO_SECONDS) / Constants.MIN_TO_SECONDS;
        mEetTomatoTime.setText(Integer.toString(time));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            int time = Integer.valueOf(mEetTomatoTime.getText().toString());
            SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
            editor.putInt(Constants.PREF_TOMATO_TIME, time * Constants.MIN_TO_SECONDS);
            editor.commit();
        } catch (Exception e) {
            Toast.makeText(getContext(), R.string.input_wrong, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
