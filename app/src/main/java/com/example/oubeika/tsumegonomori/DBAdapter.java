package com.example.oubeika.tsumegonomori;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class DBAdapter {

    private static final String DB_NAME = "mynote.db";
    private static final int DB_VER = 1;

    protected final Context context;
    protected GoDataOpenHelper goDataOpenHelper;
    protected SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        goDataOpenHelper = new GoDataOpenHelper(this.context);
    }

    public DBAdapter open() {
        db = goDataOpenHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        goDataOpenHelper.close();
    }

    public boolean deleteAllNotes(){
        return db.delete("goDataInfo", null, null) > 0;
    }

    public boolean deleteNote(int id){
        return db.delete("goDataInfo", "_id" + "=" + id, null) > 0;
    }
    public Cursor getAllNotes(){
        return db.query("goDataInfo", null, null, null, null, null, null);
    }

    public void saveNote(String q_num, String q_level){
        ContentValues values = new ContentValues();
        values.put("q_num", q_num);
        values.put("q_level", q_level);
        db.insertOrThrow("goDataInfo", null, values);
    }
}
