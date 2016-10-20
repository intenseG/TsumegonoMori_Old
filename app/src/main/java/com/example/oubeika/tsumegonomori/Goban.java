package com.example.oubeika.tsumegonomori;

import android.support.v7.app.AppCompatActivity;

public class Goban extends AppCompatActivity{

    private static final int LINE = 15;
   // private static final int ALLBOARDLINE = LINE * LINE;
    private int[][] board = new int[LINE][LINE];
/*
    private int bhama;
    private int whama;
*/

    private int row;
    private int col;
    //private int lastTurn;
/*    private int dame;
    private int[][] agehamaPT;
    private int nxtR;
    private int nxtC;
    private int ixAG;        //アゲハマ着点テーブル用インデックス初期化
    private int ixAGMax;     //アゲハマ着点テーブルデータ数最大値初期化*/

    Goban() {
        initializeBan();
/*        bhama = 0;
        whama = 0;
        agehamaPT = new int[ALLBOARDLINE][2];*/
    }

    public void initialize() {
        initializeBan();
/*        bhama = 0;
        whama = 0;
        agehamaPT = new int[ALLBOARDLINE][2];*/
    }

    private void initializeBan() {
        for (int r = 0; r < LINE; r++) {
            for (int c = 0; c < LINE; c++) {
                board[r][c] = 0;
            }
        }
    }

    public void update(int stoneColor, int row, int col) {

        //lastTurn = stoneColor;
       // returnValue();
        if (board[row][col] == 0) {
            board[row][col] = stoneColor;
        }
       // translateValue(col, row, stoneColor);
    }

    private void returnValue() {
        for (int row = 0; row < LINE; row++) {
            for (int col = 0; col < LINE; col++) {
                if (board[row][col] > 10) {
                    board[row][col] = board[row][col] - 10;
                }
            }
        }
    }
    private void translateValue(int col, int row, int color){
        board[row][col] = color + 10;
    }

    public int[][] getBoard(){
        return board;
    }

    public void setBoard(int[][] board){
        this.board = board;
    }

   /* private void procAgehama(){           //盤上に配置された石すべてについて
        for(row=0;row<LINE;row++){
            for(col=0;col<LINE;col++){
                if(board[row][col]!=0 & board[row][col]!=lastTurn){
                    checkAgehama();
                }
            }
        }
    }

    private void checkAgehama(){
        countDame();
        removeAgehama();
    }


    private void countDame(){
        //アゲハマ着点テーブル初期化
        for(int i=0;i<ALLBOARDLINE;i++){
            agehamaPT[i][0]=-1;
            agehamaPT[i][1]=-1;
        }
        agehamaPT[0][0]=row;  //検索開始着点設定
        agehamaPT[0][1]=col;
        dame=0;             //ダメ個数初期化
        ixAG=0;             //アゲハマ着点テーブル用インデックス初期化
        ixAGMax=0;          //アゲハマ着点テーブルデータ数最大値初期化

        while(ixAG<=ixAGMax){
            if(dame==0){
                nxtR=agehamaPT[ixAG][0]-1;   //検索基点の上の点
                nxtC=agehamaPT[ixAG][1];
                countDameSub();
            }
            if(dame==0){
                nxtR=agehamaPT[ixAG][0]+1;   //検索基点の下の点
                nxtC=agehamaPT[ixAG][1];
                countDameSub();
            }
            if(dame==0){
                nxtR=agehamaPT[ixAG][0];
                nxtC=agehamaPT[ixAG][1]-1;   //検索基点の左の点
                countDameSub();
            }
            if(dame==0){
                nxtR=agehamaPT[ixAG][0];
                nxtC=agehamaPT[ixAG][1]+1;   //検索基点の右の点
                countDameSub();
            }
            ixAG=ixAG+1;
        }

    }

    private void countDameSub(){
        boolean isNotMatch=true;
        if(nxtR>=0 && nxtR<LINE &&   //盤上の着点の場合のみ処理続行
                nxtC>=0 && nxtC<LINE){
            if(board[nxtR][nxtC]==0){   //検索点に石がないときはダメをカウント
                dame++;
            }
            else{
                if(board[nxtR][nxtC]!=lastTurn){  //最終手番の反対色のとき処理続行
                    for(int i=0;i<ALLBOARDLINE;i++){
                        if(nxtR==agehamaPT[i][0] && nxtC==agehamaPT[i][1]){
                            isNotMatch=false;
                        }
                    }
                    if(isNotMatch){
                        ixAGMax=ixAGMax+1;
                        agehamaPT[ixAGMax][0]=nxtR;
                        agehamaPT[ixAGMax][1]=nxtC;
                    }
                }
            }
        }
    }

    private void removeAgehama(){
        if(dame==0){ //石群のダメが空いていないとき
            for(ixAG=0;ixAG<=ixAGMax;ixAG++){
                switch(board[agehamaPT[ixAG][0]][agehamaPT[ixAG][1]]){
                    case 1:whama=whama+1;
                        break;
                    case 2:bhama=bhama+1;
                        break;
                }
                board[agehamaPT[ixAG][0]][agehamaPT[ixAG][1]]=0;
            }
        }
    }*/

/*    public void SgfRead() {

        InputStream is = null;
        BufferedReader br = null;
        String s = "";

        try {
            is = this.getAssets().open("sgfdata.json");
            br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                s = s.concat(line);

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
    }*/
}
