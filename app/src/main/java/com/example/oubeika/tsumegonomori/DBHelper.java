package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "godata.db";
    private static final int DB_VER = 1;
    static final String TABLE_NAME = "goData";

    static final String COL_ID = "id";
    static final String COL_NUMBER = "number";
    static final String COL_LEVEL = "level";
    static final String COL_GODATA_P = "goDataP";
    static final String COL_GODATA_A = "goDataA";

    //private Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
       //mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GoDataDao.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

   /*     db.execSQL("drop table if exists" + TABLE_NAME);

        onCreate(db);*/
    }
}
