package com.willhua.tomatowork.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by willhua on 2016-12-10.
 */

public class NoteListFragment extends BaseFragment implements INoteView {
    private static final String TAG = "NoteListFragment";

    @BindView(R.id.new_item)
    EditText mEtAdd;
    @BindView(R.id.item_list)
    ListView mListView;

    private NotePresenter mNotePresenter;
    private List<Note> mNoteList;

    public NoteListFragment() {
        mNotePresenter = new NotePresenter(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_list, null);
        ButterKnife.bind(this, view);
        mEtAdd.setHint(R.string.create_new_note);
        view.findViewById(R.id.fab_start).setVisibility(View.GONE);
        mNotePresenter.getNotes();
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


}
