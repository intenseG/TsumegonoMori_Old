package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import static com.example.oubeika.tsumegonomori.Board.BOARD_SIZE;
import static com.example.oubeika.tsumegonomori.Disc.BLACK;
import static com.example.oubeika.tsumegonomori.Disc.WHITE;

//問題出題画面を表示するクラス
public class Problem extends AppCompatActivity implements View.OnClickListener {

    private int start;

    private SQLiteDatabase db;
    private Board board;

    private TextView q_num, level_text;
    private ImageView goban_13;
    private ImageView stone;
    private ImageButton undo, redo, reload, previous, next;

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
        //GoDataParse gdp = new GoDataParse();

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

        splitZahyoP(goDataP); // 石の初期配置データを分解したのち配列に格納
        splitZahyoA(goDataA); // 解答データを分解したのち配列に格納

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

    public void splitZahyoP(String goDataP) {

        String zahyo;
        String splitZahyo[];

        String[] split = (goDataP.split("@", 0));

        for (String data : split) {
            if (data.startsWith("AB::")) {       //ここで黒かどうかを判定

                start = data.indexOf("AB::");
                if (start != -1) {
                    zahyo = data.substring(start + 4);   //黒の初期配置座標の文字列を取得
                    splitZahyo = (zahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyoP : splitZahyo) {   //座標を1つずつ読み込むループ文
                        board.initStone(BLACK, intChanger(zahyoP.charAt(0)), intChanger(zahyoP.charAt(1)));
                    }
                }
            } else if (data.startsWith("AW::")) {           //ここで白かどうかを判定

                start = data.indexOf("AW::");       //AWの位置を取得
                if (start != -1) {
                    zahyo = data.substring(start + 4);   //セミコロン間の文字列を取得
                    splitZahyo = (zahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyoP : splitZahyo) {   //座標を1つずつ読み込むループ文
                        board.initStone(WHITE, intChanger(zahyoP.charAt(0)), intChanger(zahyoP.charAt(1)));
                    }
                }
            }
        }
    }

    public void splitZahyoA(String goDataA) {

        String zahyo;
        String[] answerList;

        String[] split = (goDataA.split("@", 0));

        for (String data : split) {
            if (data.startsWith("B;")) {

                start = data.indexOf("B;");
                if (start != -1) {
                    zahyo = data.substring(start + 2);   //黒の初期配置座標の文字列を取得
                    answerList = (zahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyoA : answerList) {   //座標を1つずつ読み込むループ文
                        board.initStone(BLACK,intChanger(zahyoA.charAt(0)), intChanger(zahyoA.charAt(1)));
                    }
                }
            } else if (data.startsWith("W;")) {

                start = data.indexOf("W;");
                if (start != -1) {
                    zahyo = data.substring(start + 2);   //白の初期配置座標の文字列を取得
                    answerList = (zahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyoA : answerList) {   //座標を1つずつ読み込むループ文
                        board.initStone(WHITE, intChanger(zahyoA.charAt(0)), intChanger(zahyoA.charAt(1)));
                    }
                }
            }
        }
    }

    private int intChanger(char c) {
        int i;
        i = (int)c - (int)'a'; // +1すれば座標が1から始まる
        Log.d("intTest", String.valueOf(i));

        return i;
    }
}