package com.example.oubeika.tsumegonomori;

import android.view.MotionEvent;

public class TouchMove {

    private float x, y;

    public boolean onTouchEvent(MotionEvent e){

        x = e.getX();
        y = e.getY();

        return false;
    }
}
