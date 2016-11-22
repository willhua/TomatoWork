package com.willhua.tomatowork.ui.adapter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.utils.LogUtil;
import com.willhua.tomatowork.utils.SizedDrawable;

import java.util.List;

/**
 * Created by willhua on 2016/11/21.
 */

public class CandyAdapter extends BaseAdapter {
    public interface CandyClick{
        void onDone(int position, boolean checked);
        void onStick(int position, boolean checked);
    }

    private final String FILTER = "CandyAdapter";
    private List<Candy> mCandyList;
    private CandyClick mCandyClick;
    private int mDrawableSize;
    private SizedDrawable mDoneChecked;
    private SizedDrawable mDoneNotChecked;
    private SizedDrawable mStickChecked;
    private SizedDrawable mStickNotChecked;

    public CandyAdapter(@NonNull List<Candy> tomatos, @NonNull CandyClick candyClick, final Resources res){
        long t = System.currentTimeMillis();
        mCandyList = tomatos;
        mCandyClick = candyClick;
        mDrawableSize = (int)res.getDimension(R.dimen.candy_item_height);
        Drawable drawable = res.getDrawable(R.drawable.done);
        mDoneChecked = new SizedDrawable(drawable, mDrawableSize, mDrawableSize);
        drawable = res.getDrawable(R.drawable.done);
        mDoneNotChecked = new SizedDrawable(drawable, mDrawableSize, mDrawableSize);
        drawable = res.getDrawable(R.drawable.pin2);
        mStickChecked = new SizedDrawable(drawable, mDrawableSize, mDrawableSize);
        drawable = res.getDrawable(R.drawable.pin);
        mStickNotChecked = new SizedDrawable(drawable, mDrawableSize, mDrawableSize);
        LogUtil.d("time ", "time  " + (System.currentTimeMillis() - t));
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
        ViewHolder vh = null;
        if(convertView != null){
            vh = (ViewHolder) convertView.getTag();
        }else{
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.tomato, null);
            vh = new ViewHolder();
            vh.mTextView = (TextView) convertView.findViewById(R.id.tomato_tv);
            vh.mDone = (CheckBox) convertView.findViewById(R.id.done);
            vh.mStick = (CheckBox) convertView.findViewById(R.id.stick);
            convertView.setTag(vh);
        }
        vh.mPosition = position;
        vh.mTextView.setText(mCandyList.get(position).getDescribe());
        vh.setClick();
        return convertView;
    }

    private class ViewHolder {
        int mPosition;
        TextView mTextView;
        CheckBox mDone;
        CheckBox mStick;

        void setClick(){
            if(mDone.isChecked()){
                mDone.setButtonDrawable(mDoneChecked);
            }else{
                mDone.setButtonDrawable(mDoneNotChecked);
            }
            if(mStick.isChecked()){
                mStick.setButtonDrawable(mStickChecked);
            }else{
                mStick.setButtonDrawable(mStickNotChecked);
            }
            mDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(mDone.isChecked()){
                        mDone.setButtonDrawable(mDoneChecked);
                    }else{
                        mDone.setButtonDrawable(mDoneNotChecked);
                    }
                    LogUtil.d(FILTER, "ondone " + isChecked);
                    if(mCandyClick != null){
                        mCandyClick.onDone(mPosition, isChecked);
                    }
                }
            });
            mStick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(mStick.isChecked()){
                        mStick.setButtonDrawable(mStickChecked);
                    }else{
                        mStick.setButtonDrawable(mStickNotChecked);
                    }
                    LogUtil.d(FILTER, "onstick " + isChecked);
                    if(mCandyClick != null){
                        mCandyClick.onStick(mPosition, isChecked);
                    }
                }
            });
        }
    }
}
