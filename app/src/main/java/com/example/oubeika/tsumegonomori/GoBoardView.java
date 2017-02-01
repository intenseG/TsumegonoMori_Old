package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.example.oubeika.tsumegonomori.Board.BOARD_SIZE;
import static com.example.oubeika.tsumegonomori.Disc.BLACK;
import static com.example.oubeika.tsumegonomori.Disc.WHITE;

public class GoBoardView extends View {

    private static final int SPACE = 20;

    private Board board;

    private Bitmap stone_b_bitmap;
    private Bitmap stone_w_bitmap;

    private Paint p = new Paint();

    private int x, y;

    private float stone_size = 0F;

    private boolean set_stones_flag = true;

    public GoBoardView(Context context) {
        super(context);

        board = new Board();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("onDrawCalled", "onDraw called!!");

        if (set_stones_flag) {
            setStoneImages();
        }

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
        setSize();
    }

    public void setSize() {
        stone_size = (float) (SPACE * 0.75);

        Log.d("stoneSize", "stoneSize:" + stone_size);
        set_stones_flag = true;
    }

    private Bitmap getScaledRes(float size, int resID) {
        Bitmap unscaled_bitmap = BitmapFactory.decodeResource(getResources(), resID);
        return Bitmap.createScaledBitmap(unscaled_bitmap, (int)size, (int)size, true);
    }

    public void setStoneImages() {
        stone_b_bitmap = getScaledRes(stone_size, R.drawable.stone1);
        stone_w_bitmap = getScaledRes(stone_size, R.drawable.stone2);
    }

}
