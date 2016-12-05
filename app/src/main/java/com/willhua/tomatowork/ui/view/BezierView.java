package com.willhua.tomatowork.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by willhua on 2016/12/5.
 */

public class BezierView extends View {
    private static final String TAG = "BezierView";

    private float[] mValues;
    private Paint mPaint;
    private int mLineColor = 0xFFFF0000;
    private int mLineWidth = 5;
    private boolean mDrawPoint;
    private Path mPath;
    private float mYMin = 0;
    private float mYRange = 10;
    private boolean mNeedUpdatePath = true;

    public void setDrawPoint(boolean drawPoint) {
        mDrawPoint = drawPoint;
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

    public void setYRange(float yMin, float yRange){
        mYMin = yMin;
        mYRange = yRange;
        if(yRange <= 0){
            mYRange = Math.abs(mYMin);
        }
    }


    public BezierView(Context context, AttributeSet attrs){
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineWidth);
        mPath = new Path();
        mValues = new float[]{1,5,8,9,4,8,3,7,6,0,9,4,5};
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(mNeedUpdatePath){
            calculateBezier();
        }
        canvas.drawPath(mPath, mPaint);
    }


    private void calculateBezier(){
        mNeedUpdatePath = false;
        if(mValues != null && mValues.length > 1){
            float xDiff = getWidth() / (mValues.length - 1f);
            mPath.reset();
            mPath.moveTo(toY(0), 0);
            PointF ctrl1 = new PointF();
            PointF ctrl2 = new PointF();
            for(int i = 1; i < mValues.length; i++){
                ctrl1.y = toY(i - 1);
                ctrl1.x = (i - 0.5f) * xDiff;
                ctrl2.x = ctrl1.x;
                ctrl2.y = toY(i);
                mPath.cubicTo(ctrl1.x, ctrl1.y, ctrl2.x, ctrl2.y, i * xDiff, ctrl2.y);
            }
        }
    }

    private float toY(int i){
        return (mValues[i] - mYMin) / mYRange * getHeight();
    }


}
