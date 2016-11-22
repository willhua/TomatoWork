package com.willhua.tomatowork.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/**
 * Created by willhua on 2016/11/23.
 */

public class SizedDrawable extends Drawable {

    private int mWidth;
    private int mHeight;
    private Drawable mDrawable;

    public SizedDrawable(Drawable drawable, int width, int height){
        mDrawable = drawable;
        mWidth = width;
        mHeight = height;
    }

    @Override
    public void draw(Canvas canvas) {
        mDrawable.draw(canvas);
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mDrawable.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom){
        super.setBounds(left, top, right, bottom);//This is needed so that getBounds on this class would work correctly.
        mDrawable.setBounds(left, top, right, bottom);
    }

    @Override
    public int getIntrinsicWidth(){
        return  mWidth;
    }

    @Override
    public int getIntrinsicHeight(){
        return  mHeight;
    }
}
