package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

import static com.example.oubeika.tsumegonomori.GameConst.BLACK;
import static com.example.oubeika.tsumegonomori.GameConst.TAG;
import static com.example.oubeika.tsumegonomori.GameConst.WHITE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SQLiteDatabase db;
    private String goDataText;
    private GoData goData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.normal);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Database接続
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        GoDataDao goDataDao = new GoDataDao(db);

        try {
            goDataText = loadGoData();
            String[] splitGoData = goDataText.split("/", 0);
            for (String newGoData : splitGoData) {
                goDataSeparate(newGoData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, StagesList.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }

    //ここでデータを受け取って不要な文字を除去する
    public void goDataSeparate(String goDataText) {

        int start;
        int stoneColor;
        String[] split = (goDataText.split("@", 0));

        for (String data : split) {
            if (data.startsWith("AB::")) {       //ここで黒かどうかを判定

                stoneColor = BLACK;
                start = data.indexOf("AB::");
                if (start != -1) {
                    String allZahyo = data.substring(start + 4);   //黒の初期配置座標の文字列を取得
                    String splitZahyo[] = (allZahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyo : splitZahyo) {   //座標を1つずつ読み込むループ文
                        Log.d(TAG, zahyo);
                    }
                }
            } else if (data.startsWith("AW::")) {           //ここで白かどうかを判定

                stoneColor = WHITE;
                start = data.indexOf("AW::");       //AWの位置を取得
                if (start != -1) {
                    String allZahyo = data.substring(start + 4);   //黒の初期配置座標の文字列を取得
                    String splitZahyo[] = (allZahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyo : splitZahyo) {   //座標を1つずつ読み込むループ文
                        Log.d(TAG, zahyo);
                    }
                }
            } else if (data.startsWith("B;")) {

                stoneColor = BLACK;
                start = data.indexOf("B;");
                if (start != -1) {
                    String allZahyo = data.substring(start + 2);   //黒の初期配置座標の文字列を取得
                    String[] splitZahyo = (allZahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyo : splitZahyo) {   //座標を1つずつ読み込むループ文
                        Log.d(TAG, zahyo);
                    }
                }
            }  else if (data.startsWith("W;")) {

                stoneColor = WHITE;
                start = data.indexOf("W;");
                if (start != -1) {
                    String allZahyo = data.substring(start + 2);   //黒の初期配置座標の文字列を取得
                    String[] splitZahyo = (allZahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyo : splitZahyo) {   //座標を1つずつ読み込むループ文
                        Log.d(TAG, zahyo);
                    }
                }
            } else if (data.startsWith("QN::")) {  //ここは必要ないから後で消す

                start = data.indexOf("QN::");
                if (start != -1) {
                    String qNum = data.substring(start + 4);
                    Log.d(TAG, qNum);
                }
            } else if (data.startsWith("LV::")) {
                start = data.indexOf("LV::");
                if (start != -1) {
                    String level = data.substring(start + 4);
                    Log.d(TAG, level);
                }
            }
        }
    }

    private String loadGoData() throws IOException {

        String str = "";

        AssetManager as = this.getResources().getAssets();
        try {
            str = readFile(as.open("goData.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private String readFile(InputStream is) throws IOException {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                    sb.append(str); //.append("\n")
            }
            return sb.toString();
        } finally {
            if (br != null) br.close();
        }
    }
}
