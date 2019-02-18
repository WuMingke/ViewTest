package com.erkuai.viewtest.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2019/2/18.
 */

public class PorterDuffXfermodeView extends View {

    private int width = 200;
    private int height = 200;
    private Bitmap dstBmp,srcBmp;
    private Paint paint;

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        dstBmp = makeDstBmp(width,height);
        srcBmp = makeSrcBmp(width,height);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dstBmp,0,0,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        canvas.drawBitmap(srcBmp,width/2,height/2,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(i);
    }

    private Bitmap makeSrcBmp(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(0,0,width,height,paint);
        return bitmap;
    }

    private Bitmap makeDstBmp(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFFFFCC44);
        canvas.drawOval(new RectF(0,0,width,height),paint);
        return bitmap;
    }

}
