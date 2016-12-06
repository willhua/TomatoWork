package com.willhua.tomatowork.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import com.willhua.tomatowork.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016/12/5.
 */

public class BezierView extends View {
    private static final String TAG = "BezierView";
    private static final int HEIGHT = 360;
    private static final int WIDTH = 540;

    private int mDrawableWidth;
    private int mDrawableHeight;

    //properties for draw curve
    private float[] mValues;
    private Paint mPaint;
    private int mLineColor = 0xFFFF0000;
    private int mLineWidth = 5;
    private Path mPath;
    private float mYMin = 0;
    private float mYRange = 10;
    private boolean mNeedUpdatePath = true;

    //properties for draw divider
    private int mDividerWidth = 2;
    private int mDividerLineColor = 0xFFFFFFFF;
    private int mDividerNumX = 0; //the num of lines (vertical line) to divide x,
    private int mDividerNumY = 2;

    //properties for draw border
    private int mBorderWidth = 5;
    private int mBorderColor = 0xFFFFFFFF;
    private boolean mBorderX = false; //draw whether or not a line border in horizontal direction
    private boolean mBorderY = true;

    //properties for draw points
    private boolean mDrawPoint = true;
    private int mPointColor = 0xFF00FF00;
    private int mPointWidth = 10;
    private List<PointF> mPoint;

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineWidth);
        mPath = new Path();
        mValues = new float[]{1, 2,3,4,5,6,7,8,9,8,5,1,4,9};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);
        if (modeH == MeasureSpec.AT_MOST && modeW == MeasureSpec.AT_MOST) {
            setMeasuredDimension(WIDTH, HEIGHT);
        } else if (modeH == MeasureSpec.AT_MOST) {
            setMeasuredDimension(sizeW, HEIGHT);
        } else if (modeW == MeasureSpec.AT_MOST) {
            setMeasuredDimension(WIDTH, sizeH);
        }
        mDrawableWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mDrawableHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    public void onDraw(Canvas canvas) {
        long time = System.currentTimeMillis();
        super.onDraw(canvas);
        LogUtil.d(TAG, "time 1 " + (System.currentTimeMillis() - time));
        if (mNeedUpdatePath) {
            calculateBezier();
            LogUtil.d(TAG, "time 2 " + (System.currentTimeMillis() - time));
        }
        drawDivider(canvas);
        drawBorder(canvas);
        mPaint.setStrokeWidth(mLineColor);
        mPaint.setColor(mLineColor);
        canvas.drawPath(mPath, mPaint);
        drawPoint(canvas);
        LogUtil.d(TAG, "time 3 " + (System.currentTimeMillis() - time));
    }

    public void setDividerNumX(int dividerNumX) {
        mDividerNumX = dividerNumX;
    }

    public void setDividerNumY(int dividerNumY) {
        mDividerNumY = dividerNumY;
    }


    public void setDrawPoint(boolean drawPoint) {
        mDrawPoint = drawPoint;
    }

    public void setPointColor(int pointColor) {
        mPointColor = pointColor;
    }

    public void setPointWidth(int pointWidth) {
        mPointWidth = pointWidth;
    }

    public void setBorderX(boolean borderX) {
        mBorderX = borderX;
    }

    public void setBorderY(boolean borderY) {
        mBorderY = borderY;
    }

    public void setLineColor(int lineColor) {
        mLineColor = lineColor;
        mPaint.setColor(mLineColor);
    }

    public void setLineWidth(int lineWidth) {
        mLineWidth = lineWidth;
        mPaint.setStrokeWidth(lineWidth);
    }

    public void setPoints(float[] values) {
        mValues = values;
        mNeedUpdatePath = true;
    }

    public void setYRange(float yMin, float yRange) {
        mYMin = yMin;
        mYRange = yRange;
        if (yRange <= 0) {
            mYRange = Math.abs(mYMin);
        }
    }


    private void calculateBezier() {
        mNeedUpdatePath = false;
        if(mPoint == null){
            mPoint = new ArrayList<>();
        }
        mPoint.clear();
        if (mValues != null && mValues.length > 1) {
            float xDiff = (getWidth() - getPaddingLeft() - getPaddingBottom()) / (mValues.length - 1f);
            mPath.reset();
            mPath.moveTo(getPaddingLeft(), toY(0));
            mPoint.add(new PointF(getPaddingLeft(), toY(0)));
            PointF ctrl1 = new PointF();
            PointF ctrl2 = new PointF();
            for (int i = 1; i < mValues.length; i++) {
                ctrl1.y = toY(i - 1);
                ctrl1.x = (i - 0.5f) * xDiff + getPaddingLeft();
                ctrl2.x = ctrl1.x;
                ctrl2.y = toY(i);
                PointF pointF = new PointF(i * xDiff + getPaddingLeft(), ctrl2.y);
                mPoint.add(pointF);
                mPath.cubicTo(ctrl1.x, ctrl1.y, ctrl2.x, ctrl2.y, pointF.x, pointF.y);
            }
        }
    }

    private float toY(int i) {
        float ymax = mYMin + mYRange;
        float y = getPaddingTop() + (ymax - mValues[i]) / mYRange * mDrawableHeight;
        LogUtil.d(TAG, mValues[i] +" to y:" + y);
        return y;
    }

    private void drawDivider(Canvas canvas) {
        mPaint.setColor(mDividerLineColor);
        mPaint.setStrokeWidth(mDividerWidth);
        if (mDividerNumX > 0) {
            float xdis = mDrawableWidth / (mDividerNumX + 1);
            for (int i = 1; i <= mDividerNumX; i++) {
                canvas.drawLine(getPaddingLeft() + i * xdis, getPaddingTop(), getPaddingLeft() + i * xdis, getHeight() - getPaddingBottom(), mPaint);
            }
        }
        if (mDividerNumY > 0) {
            float ydis = mDrawableHeight / (mDividerNumY + 1);
            for (int i = 1; i <= mDividerNumY; i++) {
                canvas.drawLine(getPaddingLeft(), getPaddingTop() + i * ydis, getWidth() - getPaddingRight(), getPaddingTop() + i * ydis, mPaint);
            }
        }
    }

    private void drawBorder(Canvas canvas) {
        mPaint.setColor(mBorderColor);
        mPaint.setStrokeWidth(mBorderWidth);
        if (mBorderX) {
            canvas.drawLine(getPaddingLeft(), getPaddingTop(), getPaddingLeft(), getHeight() - getPaddingBottom(), mPaint);
            canvas.drawLine(getWidth() - getPaddingRight(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), mPaint);
        }
        if (mBorderY) {
            canvas.drawLine(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getPaddingTop(), mPaint);
            canvas.drawLine(getPaddingLeft(), getHeight() - getPaddingBottom(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), mPaint);
        }
    }

    private void drawPoint(Canvas canvas){
        if(mDrawPoint && mPoint != null && mPoint.size() > 0){
            mPaint.setColor(mPointColor);
            mPaint.setStrokeWidth(mPointWidth);
            for(int i = 0; i < mPoint.size(); i++){
                canvas.drawPoint(mPoint.get(i).x, mPoint.get(i).y, mPaint);
            }
        }
    }

}
