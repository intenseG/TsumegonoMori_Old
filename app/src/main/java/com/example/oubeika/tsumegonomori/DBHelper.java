package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "godata.db";
    private static final int DB_VER = 1;
    public static final String CREATE_SQL = "create table "
            + GoDataDao.TABLE_NAME + " ("
            + GoDataDao.COL_ID + " integer primary key autoincrement,"
            + GoDataDao.COL_NUMBER + " text not null,"
            + GoDataDao.COL_LEVEL + " text not null,"
            + GoDataDao.COL_GODATA_P + " text not null,"
            + GoDataDao.COL_GODATA_A + " text not null"
            + ");";

    public DBHelper(Context c) {
        super(c, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

   /*     db.execSQL("drop table if exists" + TABLE_NAME);

        onCreate(db);*/
    }
}
