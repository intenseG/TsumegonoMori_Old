package com.example.oubeika.tsumegonomori;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.oubeika.tsumegonomori.GameConst.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SQLiteDatabase db;
    protected GoData goData;
    //private List<GoData> goDataList = new ArrayList<>();
    private boolean isLoadGoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLoadGoData = false;

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
            String goDataText = loadGoData();
            String[] splitGoData = goDataText.split("/", 0);
            for (String newGoData : splitGoData) {
                goData = goDataSeparate(newGoData);

                Log.d("GoData", goData.getNumber());
                Log.d("GoData", goData.getLevel());
                Log.d("GoData", goData.getGoDataP());
                Log.d("GoData", goData.getGoDataA());

                if (goDataDao.save(goData) < 0) {
                    throw new Exception("could not save GoData");
                }
            }
            if (!isLoadGoData) {
                Toast.makeText(this, "詰碁データの読み込みが完了しました！", Toast.LENGTH_LONG).show();
                isLoadGoData = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "詰碁データの読み込みに失敗しました。", Toast.LENGTH_LONG).show();
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
    private GoData goDataSeparate(String goDataText) {

        GoData goData2 = new GoData();
        int start;
        String allGoData;
        String[] split = (goDataText.split(",", 0));

        for (String data : split) {
            if (data.startsWith("AB::")) {       //ここで黒かどうかを判定
                start = data.indexOf("AB::");
                if (start != -1) {
                    allGoData = data.substring(start);   //黒の初期配置座標の文字列を取得
                    Log.d(TAG, allGoData);
                    goData2.setGoDataP(allGoData);
                }
            }
            if (data.startsWith("B;")) {
                start = data.indexOf("B;");
                if (start != -1) {
                    allGoData = data.substring(start);   //黒の初期配置座標の文字列を取得
                    Log.d(TAG, allGoData);
                    goData2.setGoDataA(allGoData);
                }
            }
            if (data.startsWith("QN::")) {
                start = data.indexOf("QN::");
                if (start != -1) {
                    String qNum = data.substring(start + 4);
                    Log.d(TAG, qNum);
                    goData2.setNumber(qNum);
                }
            }
            if (data.startsWith("LV::")) {
                start = data.indexOf("LV::");
                if (start != -1) {
                    String level = data.substring(start + 4);
                    Log.d(TAG, level);
                    goData2.setLevel(level);
                }
            }
        }
        return goData2;
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
