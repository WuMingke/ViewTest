package com.erkuai.viewtest.views;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/12/19.
 *
 * 雷达水波扩散动画
 */

public class RadarView extends View {

    private Paint mBgPaint;

    private int mBgColor;



    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
