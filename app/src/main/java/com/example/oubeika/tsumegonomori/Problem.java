package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.oubeika.tsumegonomori.GameConst.*;

//問題出題画面を表示するクラス
public class Problem extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;

    private TextView q_num, level_text, teban;
    private ImageView goban_13;
    private ImageView stone;
    private ImageButton undo, redo, reload, previous, next;

    private Board board;
    private GoDataParse gdp;

    private int[] stoneImage = {
            R.drawable.stone1,
            R.drawable.stone2
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_screen);

        // データベース関連
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        setView();

        board = new Board();

        Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        String level = intent.getStringExtra("level");
        String goDataP = intent.getStringExtra("goDataP");
        String goDataA = intent.getStringExtra("goDataA");

        q_num.setText("Q" + number);
        level_text.setText(level);

        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.goban_13);
        Bitmap dst = ImageUtils.resizeBitmapToDisplaySize(this, src);

        goban_13.setScaleType(ImageView.ScaleType.CENTER);
        goban_13.setImageBitmap(dst);

        gdp = new GoDataParse(goDataP, goDataA);

        int[][] ban = board.getRawBoard();

        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                switch (ban[col][row]) {
                    case BLACK: //黒石の描画
                        stone.setImageResource(stoneImage[0]);
                        break;
                    case WHITE: //白石の描画
                        stone.setImageResource(stoneImage[1]);
                        break;
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

        if (view != null) {
            switch (view.getId()) {
                case R.id.button_undo:
                    //1手戻る処理
                    break;
                case R.id.button_redo:
                    //1手進む処理
                    break;
                case R.id.button_reload:
                    //初期盤面に戻す処理
                    break;
                case R.id.button_skip_previous:
                    //前の問題に戻る処理
                    break;
                case R.id.button_skip_next:
                    //次の問題に進む処理
                    break;
            }
        }
    }

    private void setView() {
        // UI部品の設定
        goban_13 = (ImageView) findViewById(R.id.goban_13);
        stone = (ImageView) findViewById(R.id.stone);
        q_num = (TextView) findViewById(R.id.q_num);
        teban = (TextView) findViewById(R.id.teban);
        level_text = (TextView) findViewById(R.id.level_text);
        undo = (ImageButton) findViewById(R.id.button_undo);
        undo.setOnClickListener(this);
        redo = (ImageButton) findViewById(R.id.button_redo);
        redo.setOnClickListener(this);
        reload = (ImageButton) findViewById(R.id.button_reload);
        reload.setOnClickListener(this);
        previous = (ImageButton) findViewById(R.id.button_skip_previous);
        previous.setOnClickListener(this);
        next = (ImageButton) findViewById(R.id.button_skip_next);
        next.setOnClickListener(this);
    }
}


   /* private void addStone() {

        int colP = goData.getColP();
        int rowP = goData.getRowP();
        int stoneColorP = goData.getStoneColorP();

        Bitmap goban13 = BitmapFactory.decodeResource(getResources(), R.drawable.goban_13);
        Bitmap stoneB = BitmapFactory.decodeResource(getResources(), R.drawable.stone1);
        Bitmap stoneW = BitmapFactory.decodeResource(getResources(), R.drawable.stone2);

        Goban goban = new Goban();
        // goban.update(colP, rowP, stoneColorP);

        //碁盤の描画
        goban13.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        goban_13.setImageBitmap(goban13);

        //碁石の描画
        int[][] ban = goban.getBoard();
        for (int c = 0; c < LINE; c++) {
            for (int r = 0; r < LINE; r++) {
                switch (ban[c][r]) {
                    case 1:
                        stone_b.setImageBitmap(stoneB);
                    case 2:
                        stone_w.setImageBitmap(stoneW);
                }
            }
        }
    }*/