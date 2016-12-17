package com.example.oubeika.tsumegonomori;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GoDataDao {

    static final String TABLE_NAME = "goData";
    static final String COL_ID = "_id";
    static final String COL_LEVEL = "level";
    static final String COL_GODATA = "godata";
    static final String[] COLUMNS = { COL_ID, COL_LEVEL, COL_GODATA };
    public static final String CREATE_SQL = "create table"
            + TABLE_NAME + " ("
            + COL_ID + " integer primary key autoincrement,"
            + COL_LEVEL + " text not null,"
            + COL_GODATA + " text not null"
            + ");";

    private SQLiteDatabase db;

    public GoDataDao(SQLiteDatabase db) {
        this.db = db;
    }

    public List<GoData> findAll() {
        List<GoData> list = new ArrayList<GoData>();
        // query
        Cursor c = db.query(TABLE_NAME, COLUMNS, null, null, null, null,
                null);
        // 1行ずつfetch
        while (c.moveToNext()) {
            GoData goData = new GoData();
            goData.setId(c.getInt(c.getColumnIndex(COL_ID)));
            goData.setLevel(c.getString(c.getColumnIndex(COL_LEVEL)));
            goData.setGoData(c.getString(c.getColumnIndex(COL_GODATA)));
            list.add(goData);
        }

        // cursorのclose
        c.close();

        return list;
    }

    public long save(GoData goData) {
        if (!goData.validate()) {
            return -1;
        }
        ContentValues values = new ContentValues();
        values.put(COL_ID, goData.getId());
        values.put(COL_LEVEL, goData.getLevel());
        values.put(COL_GODATA, goData.getGoData());

        if (exists()) {
            String where = COL_ID + " = ?";
            String[] arg = { String.valueOf(goData.getId()) };
            return db.update(TABLE_NAME, values, where, arg);
        } else {
            values.put(COL_ID, goData.getId());
            values.put(COL_LEVEL, goData.getLevel());
            values.put(COL_GODATA, goData.getGoData());
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

    public boolean exists() {
        return findAll().size() > 0;
    }
}
