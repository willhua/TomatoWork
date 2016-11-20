package com.willhua.tomatowork.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Candy;

import java.util.List;

/**
 * Created by willhua on 2016/11/21.
 */

public class CandyAdapter extends BaseAdapter {
    private List<Candy> mCandyList;

    private CandyAdapter(List<Candy> tomatos){
        mCandyList = tomatos;
    }

    @Override
    public int getCount() {
        return mCandyList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCandyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = null;
        if(convertView != null){
            tv = (TextView) convertView.getTag();
        }else{
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.tomato, null);
            tv = (TextView)convertView.findViewById(R.id.tomato_tv);
            convertView.setTag(tv);
        }
        tv.setText(mCandyList.get(position).getDescribe());
        return convertView;
    }

    private class ViewHolder{
        TextView mTextView;
    }
}
