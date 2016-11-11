package com.example.oubeika.tsumegonomori;

public class GoData {

    protected int id;
    private String q_num;
    private String q_level;
    private String q_turn;
    private int colP;
    private int rowP;
    private int colorP;
    private int colA;
    private int rowA;
    private int colorA;

    public GoData(int id, String q_num, String q_level, String q_turn, int colP, int rowP, int colorP, int colA, int rowA, int colorA){

        this.id = id;
        this.q_num = q_num;
        this.q_level = q_level;
        this.q_turn = q_turn;
        this.colP = colP;
        this.rowP = rowP;
        this.colorP = colorP;
        this.colA = colA;
        this.rowA = rowA;
        this.colorA = colorA;
    }

    public int getColorA() {
        return colorA;
    }

    public int getId() {
        return id;
    }

    public String getQ_num() {
        return q_num;
    }

    public String getQ_level() {
        return q_level;
    }

    public String getQ_turn() {
        return q_turn;
    }

    public int getColP() {
        return colP;
    }

    public int getRowP() {
        return rowP;
    }

    public int getColorP() {
        return colorP;
    }

    public int getColA() {
        return colA;
    }

    public int getRowA() {
        return rowA;
    }
}
