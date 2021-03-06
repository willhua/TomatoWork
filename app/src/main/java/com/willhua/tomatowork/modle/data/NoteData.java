package com.willhua.tomatowork.modle.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.willhua.tomatowork.modle.data.idata.IModleNote;
import com.willhua.tomatowork.modle.entity.Note;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016-12-10.
 */

public class NoteData extends AbstractModle implements IModleNote {
    private static final String TAG = "NoteData";

    private String[] mColoumns = new String[]{
            NoteTable.KEY_ID, NoteTable.KEY_TITLE, NoteTable.KEY_DESCRIBE, NoteTable.KEY_PRIORITY
    };

    @Override
    public List<Note> getNote() {
        Cursor cursor = DbMaster.getMaster().query(NoteTable.TABLE_NAME,mColoumns, null, null, null, null, NoteTable.KEY_TIME + " DESC " );
        int title = cursor.getColumnIndex(NoteTable.KEY_TITLE);
        int describe = cursor.getColumnIndex(NoteTable.KEY_DESCRIBE);
        int priorty = cursor.getColumnIndex(NoteTable.KEY_PRIORITY);
        int id = cursor.getColumnIndex(NoteTable.KEY_ID);
        List<Note> notes = new ArrayList<>();
        LogUtil.d(TAG, "getNote  " + cursor.getCount());
        while (cursor.moveToNext()){
            notes.add(new Note(cursor.getInt(id), cursor.getString(title), cursor.getString(describe), cursor.getInt(priorty)));
        }
        if(!cursor.isClosed()){
            cursor.close();
        }
        return notes;
    }

    @Override
    public void addNote(Note note) {
        if(note != null){
            ContentValues values = new ContentValues();
            values.put(NoteTable.KEY_TITLE, note.getTitle());
            values.put(NoteTable.KEY_DESCRIBE, note.getDescribe());
            values.put(NoteTable.KEY_PRIORITY, note.getPriority());
            values.put(NoteTable.KEY_TYPE, note.getType());
            values.put(NoteTable.KEY_TIME, System.currentTimeMillis());
            LogUtil.d(TAG, "inser note " + note.getTitle());
            long id = DbMaster.getMaster().insert(NoteTable.TABLE_NAME, null, values);
            note.setID(id);
        }
    }

    @Override
    public void modifyNote(Note note) {
        if(note != null){
            ContentValues values = new ContentValues();
            values.put(NoteTable.KEY_TITLE, note.getTitle());
            values.put(NoteTable.KEY_DESCRIBE, note.getDescribe());
            values.put(NoteTable.KEY_PRIORITY, note.getPriority());
            values.put(NoteTable.KEY_TYPE, note.getType());
            DbMaster.getMaster().update(NoteTable.TABLE_NAME, values, NoteTable.KEY_ID + "=?", new String[]{Long.toString(note.getID())});
        }
    }

    @Override
    public void deleteNote(Note note) {
        if(note != null){
            DbMaster.getMaster().delete(NoteTable.TABLE_NAME, NoteTable.KEY_ID + "=?", new String[]{Long.toString(note.getID())});
        }
    }
}
