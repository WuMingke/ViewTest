package com.erkuai.viewtest.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2019/2/19.
 */

public class ShapeView1 extends View {

    private ShapeDrawable shapeDrawable;

    public ShapeView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.setBounds(new Rect(50,50,200,100));
        shapeDrawable.getPaint().setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapeDrawable.draw(canvas);
    }
}
