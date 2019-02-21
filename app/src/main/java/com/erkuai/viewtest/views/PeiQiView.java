package com.erkuai.viewtest.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2019/2/20.
 */

public class PeiQiView extends View {

    private RectF rectF;
    private Paint paintPink;
    private Paint paintRed;

    public PeiQiView(Context context) {
        super(context);
        init();
    }

    public PeiQiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        rectF = new RectF();
        paintPink = new Paint();
        paintPink.setColor(Color.rgb(255,155,192));
        paintPink.setStyle(Paint.Style.STROKE);
        paintPink.setStrokeWidth(3f);
        paintPink.setAntiAlias(true);

    }
}
