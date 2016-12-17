package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

public class NoteListFragment extends BaseFragment implements INoteView {
    private static final String TAG = "NoteListFragment";

    @BindView(R.id.new_item)
    EditText mEtAddTitle;
    @BindView(R.id.item_list)
    ListView mListView;
    @BindView(R.id.show_panel)
    ViewGroup mRootContainer;

    private NotePresenter mNotePresenter;
    private List<Note> mNoteList;
    private View mAddView;
    private EditText mEtAddDescribe;
    private Button mBtnOK;
    private Button mBtnCancle;
    private boolean mAddStatus = false;

    public NoteListFragment() {
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
        mNoteList = notes;
        mListView.setAdapter(new NoteAdapter(mNoteList));
        mListView.invalidate();
    }

    @OnClick(R.id.new_item)
    public void showAddView(View view) {
        if (!mAddStatus) {
            changView();
        }
    }

    private void initAddView(){
        mAddView = LayoutInflater.from(getContext()).inflate(R.layout.add_note, null);
        mBtnOK = (Button)mAddView.findViewById(R.id.btn_add_note_add);
        mBtnCancle = (Button)mAddView.findViewById(R.id.btn_add_note_cancle);
        mEtAddDescribe = (EditText)mAddView.findViewById(R.id.et_add_note_describe);
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotePresenter.addNote(new Note(mEtAddTitle.getText().toString(), mEtAddDescribe.getText().toString()));
                changView();
            }
        });
        mBtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changView();
            }
        });
    }

    private void changView() {
        if (mAddStatus) {
            mRootContainer.removeView(mAddView);
            mRootContainer.addView(mListView);
            mEtAddTitle.setFocusable(false);
            mEtAddTitle.setFocusableInTouchMode(false);
            mEtAddTitle.setText("");
        } else {
            if (mAddView == null) {
                initAddView();
            }
            mEtAddDescribe.setText("");
            mRootContainer.removeView(mListView);
            mRootContainer.addView(mAddView);
            mEtAddTitle.setFocusable(true);
            mEtAddTitle.setFocusableInTouchMode(true);
            mEtAddTitle.requestFocus();
        }
        mAddStatus = !mAddStatus;
        InputMethodManager imm = (InputMethodManager) mEtAddTitle.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
