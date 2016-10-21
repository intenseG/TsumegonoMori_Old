package com.example.oubeika.tsumegonomori;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static com.example.oubeika.tsumegonomori.GameConst.BLACK;
import static com.example.oubeika.tsumegonomori.GameConst.WHITE;
import static com.example.oubeika.tsumegonomori.GameConst.TAG;

class ZahyoChanger extends AppCompatActivity {

    private GoData goData = new GoData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void ProblemStone(String problems, int stoneColor) {

        int colP = intChanger(problems.charAt(0));
        int rowP = intChanger(problems.charAt(1));

        Log.d(TAG, String.valueOf(colP));
        Log.d(TAG, String.valueOf(rowP));
        Log.d(TAG, String.valueOf(stoneColor));

        goData.setColP(colP);
        goData.setRowP(rowP);
        goData.setStoneColorP(stoneColor);
    }

    private void AnswerMove(String answers, int stoneColor) {

        int colA = intChanger(answers.charAt(0));
        int rowA = intChanger(answers.charAt(1));

        Log.d(TAG, String.valueOf(colA));
        Log.d(TAG, String.valueOf(rowA));
        Log.d(TAG, String.valueOf(stoneColor));

        goData.setColA(colA);
        goData.setRowA(rowA);
        goData.setStoneColorA(stoneColor);
    }

    //ここでsを受け取って不要な文字を除去する
    public void GoDataSeparate(String goData) {

        GoData mGoData = new GoData();

        int start;
        int end;
        int stoneColor;
        String[] split = (goData.split("@", 0));

        for (String data : split) {
            if (data.startsWith("AB::")) {       //ここで黒かどうかを判定

                stoneColor = BLACK;
                start = data.indexOf("AB::");
                if (start != -1) {
    /*                end = data.indexOf("@", start + 1);
                    if (end != -1) {*/
                    String allZahyo = data.substring(start + 4);   //黒の初期配置座標の文字列を取得
                    String splitZahyo[] = (allZahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyo : splitZahyo) {   //座標を1つずつ読み込むループ文
                        Log.d(TAG, zahyo);
                        ProblemStone(zahyo, stoneColor);
                    }
                    //}
                }
            } else if (data.startsWith("AW::")) {           //ここで白かどうかを判定

                stoneColor = WHITE;
                start = data.indexOf("AW::");       //AWの位置を取得
                if (start != -1) {
                    end = data.indexOf("@", start + 1);     //セミコロンの位置を取得
                    if (end != -1) {
                        String stone = data.substring(start, end);   //セミコロン間の文字列を取得
                        String[] splitZahyo = (stone.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                        for (String zahyo : splitZahyo) {   //座標を1つずつ読み込むループ文
                            Log.d(TAG, zahyo);
                            ProblemStone(zahyo, stoneColor);
                        }
                    }
                }
            } else if (data.startsWith("B;")) {

                stoneColor = BLACK;
                start = data.indexOf("B;");
                if (start != -1) {
 /*                   end = data.indexOf("@", start + 1);
                    if (end != -1) {*/
                    String allZahyo = data.substring(start + 2);   //黒の初期配置座標の文字列を取得
                    String[] splitZahyo = (allZahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyo : splitZahyo) {   //座標を1つずつ読み込むループ文
                        Log.d(TAG, zahyo);
                        AnswerMove(zahyo, stoneColor);
                    }
                }
            } else if (data.startsWith("W;")) {

                stoneColor = WHITE;
                start = data.indexOf("W;");
                if (start != -1) {
                    end = data.indexOf("@", start + 1);
                    if (end != -1) {
                        String zahyo = data.substring(start, end);   //白の初期配置座標の文字列を取得
                        Log.d(TAG, zahyo);
                        AnswerMove(zahyo, stoneColor);
                    }
                }
            } else if (data.startsWith("QN::")) {

                start = data.indexOf("QN::");
                if (start != -1) {
    /*                end = data.indexOf("@", start + 1);
                    if (end != -1) {*/
                    String qNum = data.substring(start + 4);
                    //後で数値をQ1などの形式に変換する
                    Log.d(TAG, qNum);
                    mGoData.setQNum(qNum);
                    //}
                }
            } else if (data.startsWith("TU::")) {

                start = data.indexOf("TU::");
                if (start != -1) {
                    /*end = data.indexOf("@", start + 1);
                    if (end != -1) {*/
                    String teban = data.substring(start + 4);
                    //後で手番情報を日本語に変換する
                    Log.d(TAG, teban);
                    mGoData.setTeban(teban);
                    //}
                }
            } else if (data.startsWith("LV::")) {
                start = data.indexOf("LV::");
                if (start != -1) {
                   /* end = data.indexOf("@", start + 1);
                    if (end != -1) {*/
                    String level = data.substring(start + 4);
                    //後で数値を○級、○段のように変換する
                    Log.d(TAG, level);
                    mGoData.setLevel(level);
                }
            }
        }
    }

    private int intChanger(char ch) {
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
}