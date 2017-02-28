package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class GoView extends View {

    private static final int SPACE = 20;

    private float x, y;
    private int color;
    private Paint p = new Paint();
    private Bitmap easy_background;
    private Bitmap stone_b, stone_w;
    private Bitmap goban_13;

    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

    float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
    float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
    int dpi = displayMetrics.densityDpi;

    public GoView(Context context) {
        super(context);
        Resources res = this.getContext().getResources();
        easy_background = BitmapFactory.decodeResource(res, R.drawable.easylist_background);
        stone_b = BitmapFactory.decodeResource(res, R.drawable.stone_b);
        stone_w = BitmapFactory.decodeResource(res, R.drawable.stone_w);
        goban_13 = BitmapFactory.decodeResource(res, R.drawable.goban_13);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            x = ev.getX();
            y = ev.getY();
            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);

        easy_background.setDensity(dpi);
        c.drawRect(new RectF(0, 0, dpWidth, dpHeight), p);
        c.drawBitmap(easy_background, 0, 0, p);

        goban_13.setDensity(dpi);
        c.drawRect(new RectF(0, 120, dpWidth, (dpHeight - 120)), p);
        c.drawBitmap(goban_13, 0, (dpHeight - 120), p);

        stone_b.setDensity(dpi);
        c.drawBitmap(stone_b, Math.round(x / SPACE), Math.round(y / SPACE), p);
        stone_w.setDensity(dpi);
        c.drawBitmap(stone_w, Math.round(y / SPACE), Math.round(x / SPACE), p);
    }
}
