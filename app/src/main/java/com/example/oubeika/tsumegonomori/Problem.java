package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//問題出題画面を表示するクラス
public class Problem extends AppCompatActivity implements View.OnClickListener {  //implements View.OnClickListener

    private static final char Q = 'Q';
    private static final int LINE = 13;
    private TextView q_num, teban, level_text;
    private ImageView goban_13;
    private ImageView stone_b;
    private ImageView stone_w;
    private ImageButton undo, redo, reload, previous, next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_screen);

        // UI部品の設定
        goban_13 = (ImageView) findViewById(R.id.goban_13);
        stone_b = (ImageView) findViewById(R.id.stone_b);
        stone_w = (ImageView) findViewById(R.id.stone_w);
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

        Intent intent = getIntent();
        int id = intent.getIntExtra("_id", 0);
        String level = intent.getStringExtra("level");
        String goData = intent.getStringExtra("goData");

        q_num.setText(Q + id);
        level_text.setText(level);
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
}

// addStone();
/*        goData = new GoData();

        int col = goData.getColP();
        int row = goData.getRowP();
        int stoneColor = goData.getStoneColorP();

        Goban goban = new Goban();
        goban.update(col, row, stoneColor);

        //碁盤の描画
        goban_13.setImageBitmap(goban13);

        //碁石の描画
        int[][] ban = goban.getBoard();
        for(int c = 0; c < LINE; c++) {
            for (int r = 0; r < LINE; r++) {
                switch (ban[r][c]){
                    case 1 :
                        stone_b.setImageBitmap(stoneB);
                    case 2 :
                        stone_w.setImageBitmap(stoneW);
                }
            }
        }*/

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