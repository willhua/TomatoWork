package com.willhua.tomatowork.ui.iview;

import com.willhua.tomatowork.modle.entity.Note;

import java.util.List;

/**
 * Created by willhua on 2016-12-10.
 */

public interface INoteView {
    void onGetNote(List<Note> notes);
}
