package com.example.oubeika.tsumegonomori;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import static com.example.oubeika.tsumegonomori.Board.BOARD_SIZE;
import static com.example.oubeika.tsumegonomori.Disc.BLACK;
import static com.example.oubeika.tsumegonomori.Disc.WHITE;

public class GoBoardView extends View {

    private static final int SPACE = 50;

    private Activity activity;
    private Board board;

    private Bitmap goban_13;
    private Bitmap stone_b_bitmap;
    private Bitmap stone_w_bitmap;

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Matrix matrix;

    private Paint p;

    private int x, y;

    private int screen_width, screen_height;
    private float stone_size = 100F;
    private float goban_size = 500F;

    private boolean set_stones_flag = true;

    public GoBoardView(Context context) {
        super(context);
        this.activity = (Activity) context;
        p = new Paint();
        board = new Board();
        goban_13 = BitmapFactory.decodeResource(getResources(), R.drawable.goban_13);
        stone_b_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b);
        stone_w_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w);
    }

    public GoBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.activity = (Activity) context;
        setFocusable(true);
        p = new Paint();
        board = new Board();
        goban_13 = BitmapFactory.decodeResource(getResources(), R.drawable.goban_13);
        stone_b_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b);
        stone_w_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("onDrawCalled", "onDraw called!!");

        screen_width = canvas.getWidth();
        screen_height = canvas.getHeight();

        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.goban_13);
        Bitmap dst = ImageUtils.resizeBitmapToDisplaySize(activity, src);

        setImages(dst);

        //float paddingWidth = (screen_width - goban_13.getWidth()) / 2;

        //Log.d("screen_width", String.valueOf(screen_width));
        //Log.d("bitmap_width", String.valueOf(goban_13.getWidth()));


        canvas.drawBitmap(goban_13, 0, 0, p);

        int[][] ban = board.getRawBoard();
        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                switch (ban[col][row]) {
                    case BLACK: //黒石の描画
                        canvas.drawBitmap(stone_b_bitmap, col * SPACE, row * SPACE, p);
                        break;
                    case WHITE: //白石の描画
                        canvas.drawBitmap(stone_w_bitmap, col * SPACE, row * SPACE, p);
                        break;
                }
            }
        }

        canvas.drawBitmap(stone_b_bitmap, x * SPACE - (SPACE * 2), y * SPACE - (SPACE * 2), p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = Math.round(event.getX() / SPACE);
        y = Math.round(event.getY() / SPACE);

        Log.d("Zahyo", "X:" + x + "Y:" + y);

        invalidate();

        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //setSize();
    }

    public void setSize() {
        stone_size = (float) (SPACE * 0.75); // 0.75は端末の解像度倍率。後で動的に取得するよう変更する。

        Log.d("stoneSize", "stoneSize:" + stone_size);
        set_stones_flag = true;
    }

    private Bitmap getScaledRes(Bitmap bitmap) {

        return Bitmap.createBitmap(bitmap, 0, 0, this.screen_width, this.screen_height, matrix, true);
    }

    public void setImages(Bitmap bitmap) {
        goban_13 = bitmap;
        //stone_b_bitmap = getScaledRes(stone_b_bitmap);
        //stone_w_bitmap = getScaledRes(stone_w_bitmap);
    }

}
