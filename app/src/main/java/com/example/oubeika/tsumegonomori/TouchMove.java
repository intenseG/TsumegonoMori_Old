package com.example.oubeika.tsumegonomori;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TouchMove extends AppCompatActivity{

    private ImageView goban;
    private ImageView stone;

    private static final int SPACE = 20;
    private int[][] zahyo;
    private Bitmap[] stoneState;
    private Resources res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        stoneState = new Bitmap[2];
        stoneState[0] = null;
        stoneState[1] = BitmapFactory.decodeResource(res, R.drawable.stone_b);
        stoneState[2] = BitmapFactory.decodeResource(res, R.drawable.stone_w);

        goban = (ImageView) findViewById(R.id.goban);
        stone = (ImageView) findViewById(R.id.stone);

        goban.setImageResource(R.drawable.goban_13);
        goban.setScaleType(ImageView.ScaleType.FIT_CENTER);
        goban.setMaxWidth(768);
        goban.setMaxHeight(768);
    }

    public boolean onTouchEvent(MotionEvent e){

        int x = Math.round(e.getX() / SPACE);
        int y = Math.round(e.getY() / SPACE);
        float x2 = e.getX();
        float y2 = e.getY();

        int difX = (int) (x2 - x);
        int difY = (int) (y2 - y);

        zahyo[x][y] = 1;
        stone.setImageBitmap(stoneState[zahyo[x][y]]);
        stone.setX(x * SPACE + difX);
        stone.setY(y * SPACE + difY);

        return true;
    }
}
