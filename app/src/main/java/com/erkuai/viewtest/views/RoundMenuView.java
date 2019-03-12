package com.erkuai.viewtest.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.VpnService;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2019/3/12.
 */

public class RoundMenuView extends View implements GestureDetector.OnGestureListener {

    private Paint paint;
    private GestureDetector gestureDetector;
    private static final int CHILD_MENU_SIZE = 8;
    private int selectId = -1;
    private static final float CHILD_ANGLE = 360 / CHILD_MENU_SIZE;
    private float offsetAngle = 0;
    private static final int CENTER_X = 200;
    private static final int CENTER_Y = 200;

    public RoundMenuView(Context context) {
        super(context);
        init();
    }

    public RoundMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setFlags(Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);
        gestureDetector = new GestureDetector(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                selectId = whichSector(event.getX() - 200, event.getY() - 200, 200);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    /**
     * 计算点在那个扇形区域
     *
     * @param X
     * @param Y
     * @param R 半径
     * @return
     */
    private int whichSector(double X, double Y, double R) {

        double mod;
        mod = Math.sqrt(X * X + Y * Y); //将点(X,Y)视为复平面上的点，与复数一一对应，现求复数的模。
        double offset_angle;
        double arg;
        arg = Math.round(Math.atan2(Y, X) / Math.PI * 180);//求复数的辐角。
        arg = arg < 0 ? arg + 360 : arg;
        if (offsetAngle % 360 < 0) {
            offset_angle = 360 + offsetAngle % 360;
        } else {
            offset_angle = offsetAngle % 360;
        }
        if (mod > R) { //如果复数的模大于预设的半径，则返回0。
            return -2;
        } else { //根据复数的辐角来判别该点落在那个扇区。
            for (int i = 0; i < CHILD_MENU_SIZE; i++) {
                if (isSelect(arg, i, offset_angle) || isSelect(360 + arg, i, offset_angle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 判读该区域是否被选中
     *
     * @param arg         角度
     * @param i
     * @param offsetAngle 偏移角度
     * @return 是否被选中
     */
    private boolean isSelect(double arg, int i, double offsetAngle) {
        return arg > (i * CHILD_ANGLE + offsetAngle % 360) &&
                arg < ((i + 1) * CHILD_ANGLE + offsetAngle % 360);
    }


    //扇形
    private void drawArc(Canvas canvas, RectF rectF) {
        for (int i = 0; i < CHILD_MENU_SIZE; i++) {
            paint.setColor(Color.BLUE);
            if (i == selectId) {
                paint.setStyle(Paint.Style.FILL);
            } else {
                paint.setStyle(Paint.Style.STROKE);
            }
            canvas.drawArc(rectF, i * CHILD_ANGLE + offsetAngle, CHILD_ANGLE, true, paint);

            //扇形中心点坐标
            double x = CENTER_X + getRoundX(200f / 3 * 2, i, CHILD_MENU_SIZE, offsetAngle + CHILD_ANGLE / 2);
            double y = CENTER_Y + getRoundY(200f / 3 * 2, i, CHILD_MENU_SIZE, offsetAngle + CHILD_ANGLE / 2);

            String item = "菜单" + i;

            //文字宽高
            Rect rect = new Rect();
            paint.getTextBounds(item, 0, item.length(), rect);
            int width = rect.width();
            int height = rect.height();
            paint.setColor(Color.WHITE);
            canvas.drawText(item, (float) x - width / 2, (float) y - height / 2, paint);
        }

    }

    /**
     * 计算圆形等分扇形的点Y坐标
     *
     * @param r            圆形直径
     * @param i            第几个等分扇形
     * @param n            等分扇形个数
     * @param offset_angle 与X轴偏移角度
     * @return Y坐标
     */
    private double getRoundY(float r, int i, int n, float offset_angle) {
        return r * Math.sin(i * 2 * Math.PI / n + Math.PI / 180
                * offset_angle);
    }

    /**
     * 计算圆形等分扇形的点X坐标
     *
     * @param r            圆形直径
     * @param i            第几个等分扇形
     * @param n            等分扇形个数
     * @param offset_angle 与X轴偏移角度
     * @return x坐标
     */
    private double getRoundX(float r, int i, int n, float offset_angle) {
        return r * Math.cos(i * 2 * Math.PI / n + Math.PI / 180
                * offset_angle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 0, 400, 400);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.LTGRAY);
        canvas.drawCircle(200, 200, 200, paint);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(200, 200, 190, paint);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(200, 200, 180, paint);

        // 画空心扇形
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        drawArc(canvas, new RectF(20, 20, 380, 380));

        // 画中心外圆
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 200, 25, paint);

        // 画三角形
        Path path = new Path();
        path.moveTo(175, 200);// 此点为多边形的起点
        path.lineTo(225, 200);
        path.lineTo(200, 240);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, paint);

        // 画中心内圆
        paint.setColor(Color.MAGENTA);
        canvas.drawCircle(200, 200, 20, paint);

    }

    /**
     * 计算两个坐标点与圆点之间的夹角
     * @param px1 点1的x坐标
     * @param py1 点1的y坐标
     * @param px2 点2的x坐标
     * @param py2 点2的y坐标
     * @return 夹角度数
     */
    private double calculateScrollAngle(float px1, float py1, float px2,
                                        float py2) {
        double radian1 = Math.atan2(py1, px1);
        double radian2 = Math.atan2(py2, px2);
        double diff = radian2 - radian1;
        return Math.round(diff / Math.PI * 180);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float tpx = e2.getX() - 200;
        float tpy = e2.getY() - 200;
        float disx = (int) distanceX;
        float disy = (int) distanceY;
        double scrollAngle = calculateScrollAngle(tpx, tpy, tpx + disx, tpy
                + disy);
        offsetAngle -= scrollAngle;
        selectId = whichSector(0, 40, 200);//0,40是中心三角定点相对于圆点的坐标
        invalidate();
        Log.e("CM", "offsetAngle:" + offsetAngle);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
