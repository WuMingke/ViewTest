package com.erkuai.viewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private TextView test;
    private GestureDetector.OnGestureListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.test);
        test.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        listener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Log.i("wmk","onDown");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.i("wmk","onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.i("wmk","onSingleTapUp");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i("wmk","onScroll");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.i("wmk","onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("wmk","onFling");
                return true;
            }
        };
        return new GestureDetector(listener).onTouchEvent(event);
    }

}
