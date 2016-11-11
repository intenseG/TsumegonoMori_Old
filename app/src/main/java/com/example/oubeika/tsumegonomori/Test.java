package com.example.oubeika.tsumegonomori;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.oubeika.tsumegonomori.GameConst.TAG;

public class Test extends AppCompatActivity {

/*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ファイルの読み込み
        InputStream is;
        BufferedReader br;
        String line = "";

        try {
            is = getAssets().open("sgfdata.json");
            br = new BufferedReader((new InputStreamReader(is)));
            String s;
            while ((s = br.readLine()) != null) {
                line += s;
            }
            // Json読み込み
            String json = line;
            JSONObject jsonObject = new JSONObject(json);

            // 問題データ追加
            ZahyoChanger changer = new ZahyoChanger();
            JSONObject problems = jsonObject.getJSONObject("problems");
            //JSONArray easy = problems.getJSONArray("easy");
            JSONArray normal = problems.getJSONArray("normal");
            for (int i = 0; i < normal.length(); i++) {
                String value1 = normal.getString(i);
                Log.d(TAG, "valueは " + value1 + " です!");
                changer.GoDataSeparate(value1);
            }
            //答えデータ追加
            JSONObject answers = jsonObject.getJSONObject("answers");
            //JSONArray easy_answers = answers.getJSONArray("easy");
            JSONArray normal_answers = answers.getJSONArray("normal");
            for (int j = 0; j < normal_answers.length(); j++) {
                String value2 = normal_answers.getString(j);
                Log.d(TAG, "value2は " + value2 + " です!");
                changer.GoDataSeparate(value2);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }*/
}