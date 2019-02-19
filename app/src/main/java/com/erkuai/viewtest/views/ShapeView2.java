package com.erkuai.viewtest.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2019/2/19.
 */

public class ShapeView2 extends View {

    private ShapeDrawable shapeDrawable;
    private Path path;

    public ShapeView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        path = new Path();
        path.moveTo(0,0);
        path.lineTo(100,0);
        path.lineTo(100,100);
        path.lineTo(0,100);
        path.close();
        shapeDrawable = new ShapeDrawable(new PathShape(path,100,100));
        shapeDrawable.setBounds(new Rect(0,0,250,150));
        shapeDrawable.getPaint().setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapeDrawable.draw(canvas);
    }
}
