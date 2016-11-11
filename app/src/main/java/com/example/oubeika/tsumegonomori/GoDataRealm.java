package com.example.oubeika.tsumegonomori;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GoDataRealm extends RealmObject implements Serializable {

    private String qNum;
    private String teban;
    private String level;
    private int colP;
    private int rowP;
    private int stoneColorP;
    private int colA;
    private int rowA;
    private int stoneColorA;

    @PrimaryKey
    private int id;

    public String getQNum() {
        return qNum;
    }

    public void setQNum(String qNum) {
        this.qNum = qNum;
    }

    public String getTeban() {
        return teban;
    }

    public void setTeban(String teban) {
        this.teban = teban;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}