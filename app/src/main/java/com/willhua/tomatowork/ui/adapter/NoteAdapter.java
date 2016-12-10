package com.willhua.tomatowork.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by willhua on 2016-12-10.
 */

public class NoteAdapter extends BaseAdapter {
    private List<Note> mNoteList;

    public NoteAdapter(List<Note> notes) {
        if (notes == null) {
            mNoteList = new ArrayList<>();
        } else {
            mNoteList = notes;
        }
    }

    @Override
    public int getCount() {
        return mNoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.setText(mNoteList.get(position));
        return convertView;
    }

    private static class ViewHolder{
        TextView mTitle;
        TextView mDescribe;

        public ViewHolder(View view){
            mTitle = (TextView)view.findViewById(R.id.note_tv_title);
            mDescribe = (TextView)view.findViewById(R.id.note_tv_describe);
        }

        public void setText(Note note){
            if(note != null){
                if(mTitle != null){
                    mTitle.setText(note.getTitle());
                }
                if(mDescribe != null){
                    mDescribe.setText(note.getDescribe());
                }
            }
        }
    }
}
