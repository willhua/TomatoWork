package com.willhua.tomatowork.presenter;

import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.data.IObserver;
import com.willhua.tomatowork.modle.data.idata.IModleNote;
import com.willhua.tomatowork.modle.data.NoteData;
import com.willhua.tomatowork.modle.entity.Note;
import com.willhua.tomatowork.ui.iview.INoteView;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public class NotePresenter implements IPresenter{
    private static final String TAG = "NotePresenter";
    private IModleNote mModleNote;
    private INoteView mView;

    public NotePresenter(INoteView view){
        mModleNote = new NoteData();
        mView = view;
    }

    public void getNotes(){
        CommandRunner.getRunner().runCommand(mGetNotesCommand);
    }

    private ICommand mGetNotesCommand = new ICommand() {
        List<Note> notes;
        @Override
        public void execute() {
            notes = mModleNote.getNote();
        }

        @Override
        public void updateUI() {
            LogUtil.d(TAG, " mGetNotesCommand  updateUI ");
            mView.onGetNote(notes);
        }
    };

    public void addNote(Note note){
        if(note != null){
            mModleNote.addNote(note);
        }
    }

    public void modifyNote(Note note){
        if(note != null){
            mModleNote.modifyNote(note);
        }
    }

    public void deleteNote(Note note){
        if(note != null){
            mModleNote.deleteNote(note);
        }
    }

    @Override
    public void onViewCreate() {
        mModleNote.registerOberver(mObserver);
    }

    @Override
    public void onViewResume() {
    }

    private IObserver mObserver = new IObserver() {
        @Override
        public void onDataChanged(String table) {
            getNotes();
        }
    };

    @Override
    public void onViewPause() {
    }

    @Override
    public void onViewDestory() {
        mModleNote.unregisterObserver();
    }
}
