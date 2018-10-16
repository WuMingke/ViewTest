package com.erkuai.viewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DELL on 2018/10/16.
 */

public class GetPosTan extends View {

    private Paint paint;

    private float currentValue = 0;
    private float[] pos;
    private float[] tans;
    private Bitmap bitmap;
    private Matrix matrix;

    public GetPosTan(Context context) {
        super(context);
        init();
    }

    public GetPosTan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);

        pos = new float[2];
        tans = new float[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 10;

        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon,options);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);

        Path path = new Path();
        path.addCircle(0,0,200, Path.Direction.CW);
        PathMeasure measure = new PathMeasure(path,false);

        currentValue = currentValue+0.005f;
        if (currentValue>1){
            currentValue = 0;
        }

        measure.getPosTan(measure.getLength()*currentValue,pos,tans);

        matrix.reset();
        float degrees = (float)(Math.atan2(tans[1],tans[0])*180/Math.PI);

        matrix.postRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);   // 旋转图片
        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, paint);                                   // 绘制 Path
        canvas.drawBitmap(bitmap, matrix, paint);                     // 绘制箭头

        invalidate();

    }
}
