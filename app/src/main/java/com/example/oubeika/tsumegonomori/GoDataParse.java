package com.example.oubeika.tsumegonomori;

import static com.example.oubeika.tsumegonomori.Disc.*;

public class GoDataParse {

    private Zahyo zahyo;

/*    private int colP;
    private int rowP;
    private int stoneColorP;
    private int colA;
    private int rowA;
    private int stoneColorA;*/

    private int start;
    private int end;

    public GoDataParse(String goDataP, String goDataA) {
        
  /*      colP = 0;
        rowP = 0;
        stoneColorP = 0;
        colA = 0;
        rowA = 0;
        stoneColorA = 0;*/
        splitZahyoP(goDataP);
        splitZahyoA(goDataA);
    }

    private void splitZahyoP(String goDataP) {

        String zahyo;
        String splitZahyo[];

        String[] split = (goDataP.split("@", 0));

        for (String data : split) {
            if (data.startsWith("AB::")) {       //ここで黒かどうかを判定

                //stoneColorP = BLACK;
                start = data.indexOf("AB::");
                if (start != -1) {
                    end = data.indexOf(start + 4);
                    if (end != -1) {
                        zahyo = data.substring(end);   //黒の初期配置座標の文字列を取得
                        splitZahyo = (zahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                        for (String zahyoP : splitZahyo) {   //座標を1つずつ読み込むループ文
                            initStoneP(zahyoP, BLACK);
                        }
                    }
                }
            } else if (data.startsWith("AW::")) {           //ここで白かどうかを判定

                //stoneColorP = WHITE;
                start = data.indexOf("AW::");       //AWの位置を取得
                if (start != -1) {
                    end = data.indexOf("@", start + 4);     //セミコロンの位置を取得
                    if (end != -1) {
                        zahyo = data.substring(end);   //セミコロン間の文字列を取得
                        splitZahyo = (zahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                        for (String zahyoP : splitZahyo) {   //座標を1つずつ読み込むループ文
                            initStoneP(zahyoP, WHITE);
                        }
                    }
                }
            }
        }
    }

    private void splitZahyoA (String goDataA){

        String zahyo;
        String[] answerList;

        String[] split = (goDataA.split("@", 0));

        for (String data : split) {
            if (data.startsWith("B;")) {

                //stoneColorA = BLACK;
                start = data.indexOf("B;");
                if (start != -1) {
                    end = data.indexOf(start + 2);
                    if (end != -1) {
                        zahyo = data.substring(end);   //黒の初期配置座標の文字列を取得
                        answerList = (zahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                        for (String zahyoA : answerList) {   //座標を1つずつ読み込むループ文
                            initStoneA(zahyoA, BLACK);
                        }
                    }
                }
            } else if (data.startsWith("W;")) {

                //stoneColorA = WHITE;
                start = data.indexOf("W;");
                if (start != -1) {
                    end = data.indexOf(start + 2);
                    if (end != -1) {
                        zahyo = data.substring(end);   //白の初期配置座標の文字列を取得
                        answerList = (zahyo.split(";", 0));    //";"で区切ってString型配列に要素を入れる
                        for (String zahyoA : answerList) {   //座標を1つずつ読み込むループ文
                            initStoneA(zahyoA, WHITE);
                        }
                    }
                }
            }
        }
    }

    private void initStoneP(String zahyoP, int stoneColorP) {

        zahyo = new Zahyo(stoneColorP, intChanger(zahyoP.charAt(0)), intChanger(zahyoP.charAt(1)));
    }

    private void initStoneA(String zahyoA, int stoneColorA) {

        zahyo = new Zahyo(stoneColorA, intChanger(zahyoA.charAt(0)), intChanger(zahyoA.charAt(1)));
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

/*    public int getColP() {
        return colP;
    }

    public int getRowP() {
        return rowP;
    }

    public int getStoneColorP() {
        return stoneColorP;
    }

    public int getColA() {
        return colA;
    }

    public int getRowA() {
        return rowA;
    }

    public int getStoneColorA() {
        return stoneColorA;
    }*/
}
