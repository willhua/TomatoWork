package com.willhua.tomatowork.modle.data.idata;

import com.willhua.tomatowork.modle.entity.Note;

import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public interface IModleNote extends IModle {
    List<Note> getNote();
    void addNote(Note note);
    void modifyNote(Note note);
    void deleteNote(Note note);
}
