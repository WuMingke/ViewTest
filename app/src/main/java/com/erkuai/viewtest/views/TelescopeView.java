package com.erkuai.viewtest.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.erkuai.viewtest.R;

/**
 * Created by Administrator on 2019/2/19.
 */

public class TelescopeView extends View {

    private Bitmap bitmap;
    private ShapeDrawable shapeDrawable;
    private static final int RADIUS = 150;
    private final static int FACTOR = 3;//放大倍数
    private Matrix matrix = new Matrix();

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setLayerType(LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null==bitmap){
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.test);
            bitmap = Bitmap.createScaledBitmap(bmp,getWidth(),getHeight(),false);
            BitmapShader bitmapShader = new BitmapShader(Bitmap.createScaledBitmap(bitmap,
                    bitmap.getWidth() * FACTOR, bitmap.getHeight() * FACTOR, true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            shapeDrawable =new ShapeDrawable(new OvalShape());
            shapeDrawable.getPaint().setShader(bitmapShader);
            shapeDrawable.setBounds(0,0,RADIUS*2,RADIUS*2);
        }
        canvas.drawBitmap(bitmap,0,0,null);
        shapeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /* switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:*/

                int x = (int) event.getX();
                int y = (int) event.getY();
                matrix.setTranslate(RADIUS-x*FACTOR,RADIUS-y*FACTOR);
                shapeDrawable.getPaint().getShader().setLocalMatrix(matrix);
                shapeDrawable.setBounds(x-RADIUS,y-RADIUS,x+RADIUS,y+RADIUS);
                postInvalidate();

            return true;

  /*      }
        return super.onTouchEvent(event);
*/
    }
}
