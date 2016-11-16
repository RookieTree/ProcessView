package com.tree.nfcdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/*
 *  @项目名：  NFCdemo 
 *  @包名：    com.tree.nfcdemo
 *  @文件名:   StepNumView
 *  @创建者:   Qfits
 *  @创建时间:  2016/11/16 14:54
 *  @描述：    TODO
 */
public class StepNumView
        extends View
{
    private static final String TAG = "StepNumView";
    private Paint  mRecPaint;
    private Paint  mTextPaint;
    private String mText;
    private float            mLineLength = 300;
    private int              stepNum     = 3;
    private ArrayList<Float> mList       = new ArrayList<>();
    private Paint mUnCompletePaint;
    private int   ComNum;
    private Paint mLinePaint;
    private Paint mLinePaintGray;

    public StepNumView(Context context) {
        this(context, null);
    }

    public StepNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float leftX = (getWidth() - (stepNum - 1) * mLineLength - stepNum * 60) / 2;
        for (int i = 0; i < stepNum; i++) {
            //把所有矩形的中心，添加到集合中
            mList.add(leftX + 30 + 60 * i + mLineLength * i);
        }
    }

    private void initPaint() {
        mRecPaint = new Paint();
        mRecPaint.setAntiAlias(true);
        mRecPaint.setColor(Color.parseColor("#5EB8F5"));
        //淡蓝色线条的笔
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.parseColor("#5EB8F5"));
        mLinePaint.setStrokeWidth(6);
        //灰色线条的笔
        mLinePaintGray = new Paint();
        mLinePaintGray.setAntiAlias(true);
        mLinePaintGray.setColor(Color.parseColor("#A9C2D3"));
        mLinePaintGray.setStrokeWidth(6);

        mUnCompletePaint = new Paint();
        mUnCompletePaint.setAntiAlias(true);
        mUnCompletePaint.setColor(Color.parseColor("#A9C2D3"));
        mUnCompletePaint.setStrokeWidth(6);


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(50);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setPaintColor(String color) {
        mRecPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    public void setCompleteNum(int num) {
        this.ComNum = num - 1;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mList.size(); i++) {

            canvas.rotate(45, mList.get(i), getHeight() / 2);

            RectF rectF = new RectF(mList.get(i) - 30,
                                    getHeight() / 2 - 30,
                                    mList.get(i) + 30,
                                    getHeight() / 2 + 30);

            canvas.drawRect(rectF,ComNum >= i? mRecPaint:mUnCompletePaint);


            canvas.rotate(-45, mList.get(i), getHeight() / 2);

            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            //计算文字高度
            float fontHeight = fontMetrics.bottom - fontMetrics.top;
            //计算文字baseline
            float textBaseY = getHeight() - (getHeight() - fontHeight) / 2 - fontMetrics.bottom;
            canvas.drawText("" + (i + 1), mList.get(i), textBaseY, mTextPaint);
            if (i != mList.size() - 1) {

                canvas.drawLine(mList.get(i) + 36,
                                getHeight() / 2,
                                mList.get(i) + 36 + mLineLength,
                                getHeight() / 2,
                                ComNum <= i
                                ? mLinePaintGray
                                : mLinePaint);

            } else {
                return;
            }

        }


    }
}
