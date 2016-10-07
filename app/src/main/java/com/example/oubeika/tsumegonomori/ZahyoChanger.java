package com.example.oubeika.tsumegonomori;

import android.util.Log;

import static com.example.oubeika.tsumegonomori.GameConst.BLACK;
import static com.example.oubeika.tsumegonomori.GameConst.TAG;
import static com.example.oubeika.tsumegonomori.GameConst.WHITE;

public class ZahyoChanger {

    private int colP;
    private int rowP;
    private int stoneColorP;
    private int colA;
    private int rowA;
    private int stoneColorA;

    GoData data = new GoData();

    private void ProblemStone(String problems, int stoneColor) {

        this.colP = intChanger(problems.charAt(0));
        this.rowP = intChanger(problems.charAt(1));
        this.stoneColorP = stoneColor;

        data.setColP(String.valueOf(colP));
        data.setRowP(String.valueOf(rowP));
        data.setStoneColorP(stoneColorP);
    }

    private void AnswerMove(String answers, int stoneColor) {

        this.colA = intChanger(answers.charAt(0));
        this.rowA = intChanger(answers.charAt(1));
        this.stoneColorA = stoneColor;

        data.setColA(String.valueOf(colA));
        data.setRowA(String.valueOf(rowA));
        data.setStoneColorA(stoneColorA);
    }

    //ここでsを受け取って不要な文字を除去する
    public void GoDataSeparate(String goData) {

        int start;
        int end;
        int stoneColor;
        String[] split = (goData.split("@", 0));

        for (String data : split) {
            if (data.startsWith("AB::")) {       //ここで黒かどうかを判定

                stoneColor = BLACK;
                start = data.indexOf("AB::");
                if (start != -1) {
                    end = data.indexOf("@", start);
                    if (end != -1) {
                        String stone = data.substring(start, end);   //黒の初期配置座標の文字列を取得
                        String spritZahyo[] = (stone.split(":", 0));    //":"で区切ってString型配列に要素を入れる
                        for (String zahyo : spritZahyo) {   //座標を1つずつ読み込むループ文
                            Log.d(TAG, zahyo);
                            ProblemStone(zahyo, stoneColor);
                        }
                    }
                }
            } else if (data.startsWith("AW::")) {           //ここで白かどうかを判定

                stoneColor = WHITE;
                start = data.indexOf("AW::");       //AWの位置を取得
                if (start != -1) {
                    end = data.indexOf("@", start);     //セミコロンの位置を取得
                    if (end != -1) {
                        String stone = data.substring(start, end);   //セミコロン間の文字列を取得
                        String[] spritZahyo = (stone.split(":", 0));    //":"で区切ってString型配列に要素を入れる
                        for (String zahyo : spritZahyo) {   //座標を1つずつ読み込むループ文
                            Log.d(TAG, zahyo);
                            ProblemStone(zahyo, stoneColor);
                        }
                    }
                }
            } else if (data.startsWith("B;;")) {

                stoneColor = BLACK;
                start = data.indexOf("B;;");
                if (start != -1) {
                    end = data.indexOf("@", start);
                    if (end != -1) {
                        String zahyo = data.substring(start, end);   //黒の初期配置座標の文字列を取得
                        Log.d(TAG, zahyo);
                        AnswerMove(zahyo, stoneColor);
                    }
                }
            } else if (data.startsWith("W;;")) {

                stoneColor = WHITE;
                start = data.indexOf("W;;");
                if (start != -1) {
                    end = data.indexOf("@", start);
                    if (end != -1) {
                        String zahyo = data.substring(start, end);   //黒の初期配置座標の文字列を取得
                        Log.d(TAG, zahyo);
                        AnswerMove(zahyo, stoneColor);
                    }
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