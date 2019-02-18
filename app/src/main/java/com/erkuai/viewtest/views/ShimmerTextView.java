package com.erkuai.viewtest.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2019/2/18.
 */

public class ShimmerTextView extends AppCompatTextView {

    private float mDx;
    private LinearGradient linearGradient;
    private Paint paint;

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = getPaint();
        float v = paint.measureText(getText().toString());
        createAnim(v);
        createLinearGradient(v);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        matrix.setTranslate( mDx,0);
        linearGradient.setLocalMatrix(matrix);
        paint.setShader(linearGradient);

    }

    private void createLinearGradient(float v) {
        linearGradient = new LinearGradient(-v,0,0,0,new int[]{getCurrentTextColor(),0xff00ff00,getCurrentTextColor()},new float[]{0,0.5f,1}, Shader.TileMode.CLAMP);
    }

    private void createAnim(float v) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 2 * v);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }


}
