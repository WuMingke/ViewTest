package com.erkuai.viewtest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2018/12/19.
 * 贝塞尔曲线实现的水波效果
 */

public class BezierWaveOne extends View {

    private Path mWavePath;//波浪的路径

    private Paint mWavePaint;//波浪的画笔

    private int mWaveColor = 0xaaFF7E37;//波形的颜色

    private static final int SIN = 0;//波浪的振幅方式
    private static final int COS = 1;
    private int mWaveType = COS;

    private int mWaveLength = dp2px(500);//一个周期波浪的长度

    private int mOffset;//平移的偏移量

    private int mWaveAmplitude = dp2px(20);//振幅

    private ValueAnimator valueAnimator;//动画

    private int mScreenHeight;//屏幕宽高
    private int mScreenWidth;

    private int mWaveCount;//屏幕范围内显示几个周期

    public BezierWaveOne(Context context) {
        super(context);
    }

    public BezierWaveOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initPaint();
        initAnimation();
    }

    private void initPaint() {
        mWavePath = new Path();

        mWavePaint = new Paint();
        mWavePaint.setColor(mWaveColor);
        mWavePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWavePaint.setAntiAlias(true);
    }

    //layout  绘制过程中
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mScreenHeight = h;
        mScreenWidth = w;

        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (mWaveType) {
            case SIN:
                drawSinPath(canvas);//sin波形
                break;
            case COS:
                drawCosPath(canvas);//cos波形
                break;
        }
    }

    private void drawCosPath(Canvas canvas) {
        mWavePath.reset();

        mWavePath.moveTo(-mWaveLength + mOffset, mWaveAmplitude);
/*        //第一个控制点的坐标为(-mWaveLength * 3 / 4,3 * mWaveAmplitude
        mWavePath.quadTo(-mWaveLength * 3 / 4 ,
                3 * mWaveAmplitude,
                -mWaveLength / 2 ,
                mWaveAmplitude);

        //第二个控制点的坐标为(-mWaveLength / 4,-mWaveAmplitude)
        mWavePath.quadTo(-mWaveLength / 4 ,
                -mWaveAmplitude,
                mOffset,
                mWaveAmplitude);*/

        for (int i = 0; i < mWaveCount; i++) {

            //第一个控制点的坐标为(-mWaveLength * 3 / 4,3 * mWaveAmplitude
            mWavePath.quadTo(-mWaveLength * 3 / 4 + mOffset + i * mWaveLength,
                    3 * mWaveAmplitude,
                    -mWaveLength / 2 + mOffset + i * mWaveLength,
                    mWaveAmplitude);

            //第二个控制点的坐标为(-mWaveLength / 4,-mWaveAmplitude)
            mWavePath.quadTo(-mWaveLength / 4 + mOffset + i * mWaveLength,
                    -mWaveAmplitude,
                    mOffset + i * mWaveLength,
                    mWaveAmplitude);
        }

        mWavePath.lineTo(getWidth(), getHeight());
        mWavePath.lineTo(0, getHeight());
        mWavePath.close();

        canvas.drawPath(mWavePath, mWavePaint);
    }

    private void drawSinPath(Canvas canvas) {

    }

    private void initAnimation() {//波形动画
        valueAnimator = ValueAnimator.ofInt(0, mWaveLength);
        valueAnimator.setDuration(3000);
        valueAnimator.setStartDelay(300);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}
