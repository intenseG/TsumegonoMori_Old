package com.example.oubeika.tsumegonomori;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.oubeika.tsumegonomori.GameConst.TAG;

public class JsonReader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ファイルの読み込み
        InputStream is;
        BufferedReader br;
        String line = "";

        try {
            is = this.getAssets().open("sgfdata.json");
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
            for(int i = 0; i < normal.length(); i++) {
                String value1 = normal.getString(i);
                changer.GoDataSeparate(value1);
                Log.d(TAG, "バリューは " + value1 + " です!");
            }
            //答えデータ追加
            JSONObject answers = jsonObject.getJSONObject("answers");
            //JSONArray easy_answers = answers.getJSONArray("easy");
            JSONArray normal_answers = answers.getJSONArray("normal");
            for(int j = 0; j < normal_answers.length(); j++) {
                String value2 = normal_answers.getString(j);
                changer.GoDataSeparate(value2);
                Log.d(TAG, "バリューは " + value2 + " です!");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

//問題データを格納
    /* private ArrayList<String[]> problemSet = new ArrayList<String[]>(); */

   /* public void SgfRead() {

        InputStream is = null;
        BufferedReader br = null;

        try {
            is = this.getAssets().open("sgfdata.json");
            br = new BufferedReader(new InputStreamReader(is));
            String s;
            while ((s = br.readLine()) != null) {
                problemSet.add(s.split(";;"));
            }
            //例外処理
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SgfFirstStone(String str, int stoneColor) {

        int Row = ZahyoChanger(str.charAt(0));
        int Col = ZahyoChanger(str.charAt(1));
        int StoneColor = stoneColor;

        //他のメソッドに値を渡す処理を書く
    }

    private int ZahyoChanger(char ch) {
        int i;
        switch (ch) {
            case 'a':
                i = 0;
                break;
            case 'b':
                i = 1;
                break;
            case 'c':
                i = 2;
                break;
            case 'd':
                i = 3;
                break;
            case 'e':
                i = 4;
                break;
            case 'f':
                i = 5;
                break;
            case 'g':
                i = 6;
                break;
            case 'h':
                i = 7;
                break;
            case 'i':
                i = 8;
                break;
            case 'j':
                i = 9;
                break;
            case 'k':
                i = 10;
                break;
            case 'l':
                i = 11;
                break;
            case 'm':
                i = 12;
                break;
            case 'n':
                i = 13;
                break;
            case 'o':
                i = 14;
                break;
            case 'p':
                i = 15;
                break;
            case 'q':
                i = 16;
                break;
            case 'r':
                i = 17;
                break;
            case 's':
                i = 18;
                break;
            case 't':
                i = -1;
                break;
            default:
                i = -1;
                break;
        }
        return i;
    }


    //ここでsを受け取って不要な文字を除去する
    public void SgfSeparate(String sgfData) {

        int start;
        int end;
        int stoneColor;
        String[] sprit = (sgfData.split("@", 0));

        if (sprit[0].startsWith("AB:")) {       //ここで黒かどうかを判定

            stoneColor = BLACK;
            for (int i = 0; i < sprit.length; i++) {
                start = sprit[i].indexOf("AB:");
                if (start != -1) {
                    end = sprit[i].indexOf("@", start);
                    if (end != -1) {
                        String stone = sprit[i].substring(start, end);   //黒の初期配置座標の文字列を取得
                        String spritZahyo[] = (stone.split(":", 0));    //":"で区切ってString型配列に要素を入れる
                        for (String zahyo : spritZahyo) {   //座標を1つずつ読み込むループ文
                            SgfFirstStone(zahyo, stoneColor);
                        }
                    }
                }
            }
        } else if (sprit[1].startsWith("AW:")) {           //ここで白かどうかを判定

            stoneColor = WHITE;
            for (int j = 0; j < sprit.length; j++) {
                start = sprit[j].indexOf("AW:");       //AWの位置を取得
                if (start != -1) {
                    end = sprit[j].indexOf("@", start);     //セミコロンの位置を取得
                    if (end != -1) {
                        String stone = sprit[j].substring(start, end);   //セミコロン間の文字列を取得
                        String[] spritZahyo = (stone.split(":", 0));    //":"で区切ってString型配列に要素を入れる
                        for (String zahyo : spritZahyo) {   //座標を1つずつ読み込むループ文
                            SgfFirstStone(zahyo, stoneColor);
                        }
                    }
                }
            }
        }
    }
} */