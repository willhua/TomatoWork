package com.willhua.tomatowork.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.MutableChar;
import android.view.MotionEvent;
import android.view.View;

import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/12/18.
 */

public class ColorChooserView extends View {
    interface ColorChooseListener {
        void onColorChoose(int index);
    }

    private static final String TAG  = "ColorChooserView";
    private static final int FIR_LIGHT = 0xFFFF0000;
    private static final int FIR_DARK = 0xFF660000;
    private static final int SEC_LIGHT = 0xFFFFFF00;
    private static final int SEC_DARK = 0xFF666600;
    private static final int THR_LIGHT = 0xFF00FF00;
    private static final int THR_DARK = 0xFF006600;
    private static final int HEIGHT = 150;
    private static final int WIDTH = 500;


    private ColorChooseListener mColorChooseListener;
    private int mColorRadius;
    private int mColorSpace;
    private PointF mFirCenter = new PointF();
    private PointF mSecCenter = new PointF();
    private PointF mThrCenter = new PointF();
    private Paint mPaint;

    private int mCurrentChoose = -1;

    public ColorChooserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setColorChooseListener(ColorChooseListener listener) {
        mColorChooseListener = listener;
    }

    public int getChoose(){
        return mCurrentChoose;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int paddingW = getPaddingLeft() + getPaddingRight();
        int paddingH = getPaddingTop() + getPaddingBottom();

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(WIDTH, HEIGHT);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(WIDTH, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, HEIGHT);
        }
        mColorRadius = Math.min((getMeasuredWidth() - paddingW) / 4, getMeasuredHeight() - paddingH) / 2;
        if (mColorRadius < 0) {
            mColorRadius = 0;
        }
        mColorSpace = (getMeasuredWidth() - paddingW - 6 * mColorRadius) / 4;
        calculateCenter();
        LogUtil.d(TAG, "onmeasure " + getMeasuredWidth() + "*" + getMeasuredHeight() + "  radius:" + mColorRadius + "  space:" + mColorSpace);
    }

    private void calculateCenter() {
        int xStart = getPaddingLeft() + mColorSpace + mColorRadius;
        int y = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) / 2 + getPaddingTop();
        mFirCenter.set(xStart, y);
        mSecCenter.set(mFirCenter.x + 2 * mColorRadius + mColorSpace, y);
        mThrCenter.set(mSecCenter.x + 2 * mColorRadius + mColorSpace, y);
        LogUtil.d(TAG, "cla:paddingleft " + getPaddingLeft() + " " + getPaddingRight());
        LogUtil.d(TAG, "cla:" + mFirCenter + mSecCenter + mThrCenter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurrentChoose == 0) {
            mPaint.setColor(FIR_LIGHT);
        } else {
            mPaint.setColor(FIR_DARK);
        }
        canvas.drawCircle(mFirCenter.x, mFirCenter.y, mColorRadius, mPaint);
        if (mCurrentChoose == 1) {
            mPaint.setColor(SEC_LIGHT);
        } else {
            mPaint.setColor(SEC_DARK);
        }
        canvas.drawCircle(mSecCenter.x, mSecCenter.y, mColorRadius, mPaint);
        if (mCurrentChoose == 2) {
            mPaint.setColor(THR_LIGHT);
        } else {
            mPaint.setColor(THR_DARK);
        }
        canvas.drawCircle(mThrCenter.x, mThrCenter.y, mColorRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(event.getPointerCount() > 1){
            return false;
        }
        float x = event.getX();
        LogUtil.d(TAG, "ontouch x" + x);
        if(x < mFirCenter.x + mColorRadius + mColorSpace / 2){
            mCurrentChoose = 0;
        }else if(x < mSecCenter.x + mColorRadius + mColorSpace / 2){
            mCurrentChoose = 1;
        }else{
            mCurrentChoose = 2;
        }
        if(mColorChooseListener != null){
            mColorChooseListener.onColorChoose(mCurrentChoose);
        }
        invalidate();
        return true;
    }
}
