package com.example.oubeika.tsumegonomori;

public class Zahyo {

    private int colP;
    private int rowP;
    private int stoneColorP;
    private int colA;
    private int rowA;
    private int stoneColorA;

    public Zahyo() {

    }

    public Zahyo(int stoneColor, int col, int row) {
        this.stoneColorP = stoneColor;
        this.colP = col;
        this.rowP = row;
    }

    public int getColP() {
        return colP;
    }

    public void setColP(int colP) {
        this.colP = colP;
    }

    public int getRowP() {
        return rowP;
    }

    public void setRowP(int rowP) {
        this.rowP = rowP;
    }

    public int getStoneColorP() {
        return stoneColorP;
    }

    public void setStoneColorP(int stoneColorP) {
        this.stoneColorP = stoneColorP;
    }

    public int getColA() {
        return colA;
    }

    public void setColA(int colA) {
        this.colA = colA;
    }

    public int getRowA() {
        return rowA;
    }

    public void setRowA(int rowA) {
        this.rowA = rowA;
    }

    public int getStoneColorA() {
        return stoneColorA;
    }

    public void setStoneColorA(int stoneColorA) {
        this.stoneColorA = stoneColorA;
    }
}
