package com.tree.nfcdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/*
 *  @项目名：  NFCdemo 
 *  @包名：    com.tree.nfcdemo
 *  @文件名:   ProcessView
 *  @创建者:   Qfits
 *  @创建时间:  2016/11/16 10:38
 *  @描述：    TODO
 */
public class ProcessView
        extends View
{
    private static final String TAG = "ProcessView";


    private Paint mTextPaint;
    private float             mLineLength = 300;
    private int               stepNum     = 3;
    private ArrayList<Float>  mList       = new ArrayList<>();
    private String[] mStrArr   = new String[]{};
    private int   ComNum;
    private Paint mLinePaintGray;

    public ProcessView(Context context) {
        this(context, null);
    }

    public ProcessView(Context context, AttributeSet attrs) {
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

    public void setListText(String[] strArr) {
        this.mStrArr = strArr;
        invalidate();
    }

    private void initPaint() {
        //蓝色笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#5EB8F5"));
        mTextPaint.setTextSize(50);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        //灰色笔
        mLinePaintGray = new Paint();
        mLinePaintGray.setAntiAlias(true);
        mLinePaintGray.setColor(Color.parseColor("#A9C2D3"));
        mLinePaintGray.setTextSize(50);
        mLinePaintGray.setTextAlign(Paint.Align.CENTER);
    }

    public void setPaintColor(String color) {
        mTextPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    public void setCompleteNum(int num) {
        this.ComNum = num - 1;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < stepNum; i++) {

            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            //计算文字高度
            float fontHeight = fontMetrics.bottom - fontMetrics.top;
            //计算文字baseline
            float textBaseY = getHeight() - (getHeight() - fontHeight) / 2 - fontMetrics.bottom;
            if (mStrArr != null && mStrArr.length > 0) {
                canvas.drawText(mStrArr[i],
                                mList.get(i),
                                textBaseY,
                                ComNum >= i
                                ? mTextPaint
                                : mLinePaintGray);

            }
        }
    }
}
