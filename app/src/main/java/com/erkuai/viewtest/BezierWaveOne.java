package com.erkuai.viewtest;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/12/19.
 * 贝塞尔曲线实现的水波效果
 */

public class BezierWaveOne extends View {

    private Path mWavePath;
    private Paint mWavePaint;
    private int mWaveColor = 0xaaFF7E37;


    public BezierWaveOne(Context context) {
        super(context);
    }

    public BezierWaveOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        initPaint();
    }

    private void initPaint() {
        mWavePath = new Path();

        mWavePaint = new Paint();
        mWavePaint.setColor(mWaveColor);
        mWavePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWavePaint.setAntiAlias(true);
    }
}
