package com.example.oubeika.tsumegonomori;

import android.util.Log;

import static com.example.oubeika.tsumegonomori.Disc.*;

public class GoDataParse {

    private Board board = new Board();

    private int start = 0;

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
