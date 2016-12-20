package com.example.oubeika.tsumegonomori;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static com.example.oubeika.tsumegonomori.GameConst.*;

public class ZahyoChanger {

    private int colP, rowP, colA, rowA = 0;
    private int stoneColor;
    private GoData goData;

    public ZahyoChanger(String goDataText) {

        goData = new GoData();

        goDataSeparate(goDataText);
    }

    //ここでデータを受け取って不要な文字を除去する
    private void goDataSeparate(String goDataText) {

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
                        problemStone(zahyo, stoneColor);
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
                        problemStone(zahyo, stoneColor);
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
                        answerStone(zahyo, stoneColor);
                    }
                }
            } else if (data.startsWith("W;")) {

                stoneColor = WHITE;
                start = data.indexOf("W;");
                if (start != -1) {
                    String allZahyo = data.substring(start + 2);   //黒の初期配置座標の文字列を取得
                    String[] splitZahyo = (allZahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                    for (String zahyo : splitZahyo) {   //座標を1つずつ読み込むループ文
                        Log.d(TAG, zahyo);
                        answerStone(zahyo, stoneColor);
                    }
                }
            } else if (data.startsWith("QN::")) {  //ここは必要ないから後で消す

                start = data.indexOf("QN::");
                if (start != -1) {
                    String qNum = data.substring(start + 4);
                    //後で数値をQ1などの形式に変換する
                    Log.d(TAG, qNum);
                    //setQNum(qNum);
                }
            } else if (data.startsWith("TU::")) {

                start = data.indexOf("TU::");
                if (start != -1) {
                    String teban = data.substring(start + 4);
                    //後で手番情報を日本語に変換する
                    Log.d(TAG, teban);
                    //setTeban(teban);
                }
            } else if (data.startsWith("LV::")) {
                start = data.indexOf("LV::");
                if (start != -1) {
                    String level = data.substring(start + 4);
                    //後で数値を○級、○段のように変換する
                    Log.d(TAG, level);
                    //setLevel(level);
                }
            }
        }
    }

    private void problemStone(String separatedGoData, int stoneColor) {

        int colP = intChanger(separatedGoData.charAt(0));
        int rowP = intChanger(separatedGoData.charAt(1));

        Log.d(TAG, String.valueOf(colP));
        Log.d(TAG, String.valueOf(rowP));
        Log.d(TAG, String.valueOf(stoneColor));
    }

    private void answerStone(String separatedGoData, int stoneColor) {

        int colA = intChanger(separatedGoData.charAt(0));
        int rowA = intChanger(separatedGoData.charAt(1));

        Log.d(TAG, String.valueOf(colA));
        Log.d(TAG, String.valueOf(rowA));
        Log.d(TAG, String.valueOf(stoneColor));

        this.colA = colA;
        this.rowA = rowA;
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
