package com.example.oubeika.tsumegonomori;

import java.io.Serializable;

public class Move implements Serializable{

    private int stoneColor;
    private int row;
    private int col;

    public Move(){
        stoneColor = 0;
        row = 0;
        col = 0;
    }

    public Move(int stoneColor, int row, int col){
        this.stoneColor = stoneColor;
        this.row = row;
        this.col = col;
    }

    public void setMove(int stoneColor, int row, int col){
        this.stoneColor = stoneColor;
        this.row = row;
        this.col = col;
    }

    public int getStoneColor(){
        return stoneColor;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
}
