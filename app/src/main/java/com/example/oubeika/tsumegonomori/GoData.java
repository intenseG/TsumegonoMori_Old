package com.example.oubeika.tsumegonomori;

public class GoData {

    private String id;
    private String level;
    private String goDataP;
    private String goDataA;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    // validationチェック
    public boolean validate() {
        if (id == null || id.length() == 0) {
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
