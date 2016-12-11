package com.willhua.tomatowork.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.utils.Utils;

import java.util.List;

/**
 * Created by willhua on 2016/12/11.
 */

public class ChooseCandyAdapter extends BaseAdapter {

    private List<Candy> mCandies;
    private ViewGroup.LayoutParams mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public ChooseCandyAdapter(List<Candy> candies) {
        mCandies = candies;
    }

    @Override
    public int getCount() {
        return mCandies.size();
    }

    @Override
    public Object getItem(int position) {
        return mCandies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = createItemView(parent);
        }
        TextView textView = (TextView) convertView;
        textView.setText(Utils.getFormatCandyInfo(mCandies.get(position)));
        return convertView;
    }

    private View createItemView(ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(mLayoutParams);
        return textView;
    }
}
