package com.example.oubeika.tsumegonomori;

public class GoData {

    public enum S_STATUS {
        EMPTY,
        BLACK,
        WHITE,
        OUT
    }

    private int id;
    private String number;
    private String level;
    private String goDataP;
    private String goDataA;
    private S_STATUS status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGoDataP() {
        return goDataP;
    }

    public void setGoDataP(String goDataP) {
        this.goDataP = goDataP;
    }

    public String getGoDataA() {
        return goDataA;
    }

    public void setGoDataA(String goDataA) {
        this.goDataA = goDataA;
    }

    public S_STATUS getStatus() {
        return status;
    }

    public void setStatus(S_STATUS status) {
        this.status = status;
    }

    // validationチェック
    public boolean validate() {
        if (number == null || number.length() == 0) {
            return false;
        }
        if (level == null || level.length() == 0) {
            return false;
        }
        if (goDataP == null || goDataP.length() == 0) {
            return false;
        }
        if (goDataA == null || goDataA.length() == 0) {
            return false;
        }
        return true;
    }
}
