package com.example.oubeika.tsumegonomori;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    // 表示する画像
    private Bitmap image01;
    private Bitmap image02;
    private Bitmap image03;

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);

        //Bitmap goban_13 = BitmapFactory.decodeResource(getResources(), R.drawable.goban_13);
        // Bitmapリソースを用意
        //image01 = ImageUtils.resizeBitmapToDisplaySize((Activity) context, goban_13);
        image01 = BitmapFactory.decodeResource(getResources(), R.drawable.goban_13);
        image02 = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b);
        image03 = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Canvas取得しロックする
        Canvas canvas = holder.lockCanvas();
        canvas.drawRGB(255, 255, 255);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        // 描画処理
        canvas.drawBitmap(image01, 0, 0, paint);
        canvas.drawBitmap(image02, 96, 96, paint);
        canvas.drawBitmap(image03, 328, 328, paint);

        // LockしたCanvasを解放する
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
