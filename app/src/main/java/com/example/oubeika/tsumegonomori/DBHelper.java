package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "godata.db";
    private static final int DB_VER = 1;
    public static final String TABLE_NAME = "goData";

    public static final String COL_ID = "_id";
    public static final String COL_LEVEL = "level";
    public static final String COL_GODATA_P = "goDataP";
    public static final String COL_GODATA_A = "goDataA";

    //private Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
       //mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

/*        String createTable = "create table " + TABLE_NAME + " ("
                + COL_ID + " integer primary key autoincrement,"
                + COL_LEVEL + " text not null,"
                + COL_GODATA + " text not null"
                + ");";*/

        //db.execSQL(createTable);

        db.execSQL(GoDataDao.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

   /*     db.execSQL("drop table if exists" + TABLE_NAME);

        onCreate(db);*/
    }
}

   /* @Override
    public void onCreate(SQLiteDatabase db) {

        // create table
        try {
            loadSQL(db, "sql/create");
            db.setTransactionSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop table
        try {
            loadSQL(db, "sql/drop");
            db.setTransactionSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        onCreate(db);
    }

    private void loadSQL(SQLiteDatabase db, String assetsDir) throws IOException {

        AssetManager as = mContext.getResources().getAssets();
        try {
            String files[] = as.list(assetsDir);
            for (int i = 0; i < files.length; i++) {
                String str = readFile(as.open(assetsDir + "/" + files[i]));
                for (String sql : str.split("/")) {
                    db.execSQL(sql);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(InputStream is) throws IOException {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }
            return sb.toString();
        } finally {
            if (br != null) br.close();
        }
    }*/

   /* @Override
    public void onCreate(SQLiteDatabase db) {

        // create table
        try {
            loadSQL(db, "sql/create");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop table
        try {
            loadSQL(db, "sql/drop");
        } catch (IOException e) {
            e.printStackTrace();
        }
        onCreate(db);
    }

    private void loadSQL(SQLiteDatabase db, String assetsDir) throws IOException {

        AssetManager as = mContext.getResources().getAssets();
        try {
            String files[] = as.list(assetsDir);
            for (int i = 0; i < files.length; i++) {
                String str = readFile(as.open(assetsDir + "/" + files[i]));
                for (String sql : str.split("/")) {
                    db.execSQL(sql);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(InputStream is) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }
            return sb.toString();
        } finally {
            if (br != null) br.close();
        }
    }*/
