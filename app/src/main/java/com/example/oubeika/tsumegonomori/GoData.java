package com.example.oubeika.tsumegonomori;

import java.io.Serializable;

public class GoData implements Serializable{

    private String colP;
    private String rowP;
    private int stoneColorP;
    private String colA;
    private String rowA;
    private int stoneColorA;

    public GoData(){

    }

    public String getColP() {
        return colP;
    }

    public void setColP(String colP) {
        this.colP = colP;
    }

    public String getRowP() {
        return rowP;
    }

    public void setRowP(String rowP) {
        this.rowP = rowP;
    }

    public int getStoneColorP() {
        return stoneColorP;
    }

    public void setStoneColorP(int stoneColorP) {
        this.stoneColorP = stoneColorP;
    }

    public String getColA() {
        return colA;
    }

    public void setColA(String colA) {
        this.colA = colA;
    }

    public String getRowA() {
        return rowA;
    }

    public void setRowA(String rowA) {
        this.rowA = rowA;
    }

    public int getStoneColorA() {
        return stoneColorA;
    }

    public void setStoneColorA(int stoneColorA) {
        this.stoneColorA = stoneColorA;
    }
}
