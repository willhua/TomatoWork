package com.willhua.tomatowork.presenter;

import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.IModleNote;
import com.willhua.tomatowork.modle.db.NoteData;
import com.willhua.tomatowork.modle.entity.Note;
import com.willhua.tomatowork.ui.iview.INoteView;

import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public class NotePresenter {
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
}
