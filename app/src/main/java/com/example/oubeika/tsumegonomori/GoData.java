package com.example.oubeika.tsumegonomori;

public class GoData {

    protected int id;
    private String level;
    private String goData;

    /*public GoData(int id, String level, String goData) {

        this.id = id;
        this.level = level;
        this.goData = goData;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGoData() {
        return goData;
    }

    public void setGoData(String goData) {
        this.goData = goData;
    }

    // validationチェック
    public boolean validate() {
        if (id < 0) {
            return false;
        }
        if (level == null || level.length() == 0) {
            return false;
        }
        if (goData == null || goData.length() == 0) {
            return false;
        }
        return true;
    }
}
