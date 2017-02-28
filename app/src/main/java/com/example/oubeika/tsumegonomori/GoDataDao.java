package com.example.oubeika.tsumegonomori;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GoDataDao {

    public static final String TABLE_NAME = "goData";
    public static final String COL_ID = "_id";
    public static final String COL_NUMBER = "number";
    public static final String COL_LEVEL = "level";
    public static final String COL_GODATA_P = "goDataP";
    public static final String COL_GODATA_A = "goDataA";
    public static final String[] COLUMNS = {COL_NUMBER, COL_LEVEL, COL_GODATA_P, COL_GODATA_A};

    private SQLiteDatabase db;

    public GoDataDao(SQLiteDatabase db) {
        this.db = db;
    }

    public List<GoData> findAll() {
        List<GoData> list = new ArrayList<>();

        // query生成
        Cursor c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null); //1ステージごとに取得する問題数

        while (c.moveToNext()) {
            GoData goData = new GoData();
            goData.setNumber(c.getString(c.getColumnIndex(COL_NUMBER)));
            goData.setLevel(c.getString(c.getColumnIndex(COL_LEVEL)));
            goData.setGoDataP(c.getString(c.getColumnIndex(COL_GODATA_P)));
            goData.setGoDataA(c.getString(c.getColumnIndex(COL_GODATA_A)));
            list.add(goData);
        }
        // cursorのclose
        c.close();

        return list;
    }

    public GoData find(String num) {
        GoData goData = null;

        Cursor c = db.query(TABLE_NAME, COLUMNS, COL_NUMBER + " = ?",
                new String[]{num}, null, null, null);

        if (c.moveToFirst()) {
            goData = new GoData();
            goData.setNumber(c.getString(c.getColumnIndex(COL_NUMBER)));
            goData.setLevel(c.getString(c.getColumnIndex(COL_LEVEL)));
            goData.setGoDataP(c.getString(c.getColumnIndex(COL_GODATA_P)));
            goData.setGoDataA(c.getString(c.getColumnIndex(COL_GODATA_A)));
        }
        // cursorのclose
        c.close();

        return goData;
    }

    public long save(GoData goData) {
        if (!goData.validate()) {
            return -1;
        }

        ContentValues values = new ContentValues();
        values.put(COL_LEVEL, goData.getLevel());
        values.put(COL_GODATA_P, goData.getGoDataP());
        values.put(COL_GODATA_A, goData.getGoDataA());

        if (exists(goData.getNumber())) {
            //データが既に存在するなら更新
            String where = COL_NUMBER + " = ?";
            String[] arg = {goData.getNumber()};
            return db.update(TABLE_NAME, values, where, arg);
        } else {
            //データがまだ無いなら挿入
            values.put(COL_NUMBER, goData.getNumber());
            return db.insert(TABLE_NAME, null, values);
        }
    }

    public boolean exists(String num) {
        return find(num) != null;
    }

    public boolean exists() {
        return findAll().size() > 0;
    }
}
