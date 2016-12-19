package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Note;
import com.willhua.tomatowork.presenter.NotePresenter;
import com.willhua.tomatowork.ui.adapter.NoteAdapter;
import com.willhua.tomatowork.ui.iview.INoteView;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by willhua on 2016-12-10.
 */

public class NotePageFragment extends BaseFragment implements INoteView {
    private static final String TAG = "NotePageFragment";

    @BindView(R.id.new_item)
    EditText mEtAddTitle;
    @BindView(R.id.show_panel)
    ViewGroup mRootContainer;

    private ListViewFragment mListViewFragment;
    private NotePresenter mNotePresenter;
    private ListAdapter mListAdapter;
    private static boolean mAddStatus = false;

    public NotePageFragment() {
        mNotePresenter = new NotePresenter(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNotePresenter.onViewDestory();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_shower, null);
        ButterKnife.bind(this, view);
        mEtAddTitle.setHint(R.string.create_new_note);
        mNotePresenter.getNotes();
        mNotePresenter.onViewCreate();
        changView(mAddStatus);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onGetNote(List<Note> notes) {
        LogUtil.d(TAG, "onGetNote  " + notes.size());
        mListAdapter = new NoteAdapter(notes);
        mListViewFragment.setAdapter(mListAdapter);
    }

    @OnClick(R.id.new_item)
    public void showAddView(View view) {
        if (!mAddStatus) {
            changView(true);
        }
    }

    private void changView(boolean showAddView) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.add_item_up, R.anim.add_item_down);
        if (showAddView) {
            InputMethodManager imm = (InputMethodManager) mEtAddTitle.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEtAddTitle, 0);
            AddNoteFragment addNoteFragment = new AddNoteFragment();
            addNoteFragment.setNoteListFragment(this);
            ft.replace(R.id.shower_container, addNoteFragment);
            mEtAddTitle.setFocusable(true);
            mEtAddTitle.setFocusableInTouchMode(true);
            mEtAddTitle.requestFocus();
            mEtAddTitle.setText("");
            mEtAddTitle.setHint(R.string.add_note_title);
        } else {
            InputMethodManager imm = (InputMethodManager) mEtAddTitle.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEtAddTitle.getWindowToken(), 0);
            if (mListViewFragment == null) {
                LogUtil.d(TAG, "mListViewFragment  null  ");
                mListViewFragment = new ListViewFragment();
            }
            mListViewFragment.setAdapter(mListAdapter);
            ft.replace(R.id.shower_container, mListViewFragment);
            mEtAddTitle.setFocusable(false);
            mEtAddTitle.setFocusableInTouchMode(false);
            mEtAddTitle.setText("");
            mEtAddTitle.setHint(R.string.create_new_note);
        }
        mAddStatus = showAddView;
        ft.commit();
    }


    public static class AddNoteFragment extends BaseFragment {

        @BindView(R.id.et_add_note_describe)
        EditText mEtDescribe;
        @BindView(R.id.btn_add_note_add)
        Button mBtnAdd;
        @BindView(R.id.btn_add_note_cancle)
        Button mBtnCancle;

        NotePageFragment mNoteListFragment;

        void setNoteListFragment(NotePageFragment fragment) {
            mNoteListFragment = fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View view = inflater.inflate(R.layout.add_note, null);
            ButterKnife.bind(this, view);
            return view;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
        }


        @OnClick(R.id.btn_add_note_add)
        public void onAdd(View v) {
            String title = mNoteListFragment.mEtAddTitle.getText().toString();
            String des = mEtDescribe.getText().toString();
            if (title.length() != 0) {
                mNoteListFragment.mNotePresenter.addNote(new Note(title, des));
            }else{
                Toast.makeText(getContext(), R.string.add_input_wrong, Toast.LENGTH_SHORT).show();
                return;
            }
            mNoteListFragment.changView(false);
        }

        @OnClick(R.id.btn_add_note_cancle)
        public void onCancle(View v) {
            mNoteListFragment.changView(false);
        }
    }


}
