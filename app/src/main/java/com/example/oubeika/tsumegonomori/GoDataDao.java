package com.example.oubeika.tsumegonomori;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GoDataDao {

    static final String TABLE_NAME = "goData";
    static final String COL_ID = "id";
    static final String COL_NUMBER = "number";
    static final String COL_LEVEL = "level";
    static final String COL_GODATA_P = "goDataP";
    static final String COL_GODATA_A = "goDataA";
    static final String[] COLUMNS = { COL_NUMBER, COL_LEVEL, COL_GODATA_P, COL_GODATA_A };
    public static final String CREATE_SQL = "create table "
            + TABLE_NAME + " ("
            + COL_ID + " integer primary key autoincrement,"
            + COL_NUMBER + " text not null,"
            + COL_LEVEL + " text not null,"
            + COL_GODATA_P + " text not null,"
            + COL_GODATA_A + " text not null"
            + ");";

    private SQLiteDatabase db;

    public GoDataDao(SQLiteDatabase db) {
        this.db = db;
    }

    public List<GoData> findAll() {
        List<GoData> list = new ArrayList<>();
        // query
        Cursor c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null, "5"); //1ステージごとに取得する問題数

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
        Cursor c = db.query(TABLE_NAME, COLUMNS, COL_NUMBER + " = ?",
                new String[] { num }, null, null, COL_NUMBER);
        GoData goData = null;

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
            String where = COL_NUMBER + " = ?";
            String[] arg = {goData.getNumber()};
            return db.update(TABLE_NAME, values, where, arg);
        } else {
            values.put(COL_NUMBER, goData.getNumber());
            return db.insert(TABLE_NAME, null, values);
        }
    }

  /*  public long save(GoData goData) {
        if (!goData.validate()) {
            return -1;
        }
        ContentValues values = new ContentValues();
        //values.put(COL_ID, goData.getId());
        values.put(COL_LEVEL, goData.getLevel());
        values.put(COL_GODATA, goData.getGoData());
        if (exists()) {
            String where = COL_ID + " = ?";
            String[] arg = { String.valueOf(goData.getId()) };
            return db.update(TABLE_NAME, values, where, arg);
        } else {
            values.put(COL_ID, goData.getId());
            return db.insert(TABLE_NAME, null, values);
        }
    }*/

    public boolean exists(String num) {
        return find(num) != null;
    }

    public boolean exists() {
        return findAll().size() > 0;
    }
}
