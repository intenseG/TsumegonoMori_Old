package com.example.oubeika.tsumegonomori;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class DBAdapter {

    private static final String DB_NAME = "goData.db";
    private static final int DB_VER = 1;
    private static final String DB_TABLE = "goDataInfo";

    static final String Q_NUM = "_id";
    static final String Q_LEVEL = "q_level";
    static final String Q_TURN = "q_turn";
    static final String P_COL = "colP";
    static final String P_ROW = "rowP";
    static final String P_COLOR = "colorP";
    static final String A_COL = "colA";
    static final String A_ROW = "rowA";
    static final String A_COLOR = "colorA";


    private SQLiteDatabase db = null;
    private DBHelper dbHelper = null;
    protected final Context context;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DBHelper(this.context);
    }

    public DBAdapter open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public DBAdapter read() {
        db = dbHelper.getReadableDatabase();        // DBの読み込み
        return this;
    }

    public void close() {
        dbHelper.close();
    }

/*    public boolean deleteAllNotes(){
        return db.delete("goDataInfo", null, null) > 0;
    }

    public boolean deleteNote(int id){
        return db.delete("goDataInfo", "_id" + "=" + id, null) > 0;
    }
    public Cursor getAllNotes(){
        return db.query("goDataInfo", null, null, null, null, null, null);
    }*/

    public void saveDB(int q_num, String q_level, String q_turn, int colP, int rowP, int colorP, int colA, int rowA, int colorA){
        ContentValues values = new ContentValues();
        values.put(Q_NUM, q_num);
        values.put(Q_LEVEL, q_level);
        values.put(Q_TURN, q_turn);
        values.put(P_COL, colP);
        values.put(P_ROW, rowP);
        values.put(P_COLOR, colorP);
        values.put(A_COL, colA);
        values.put(A_ROW, rowA);
        values.put(A_COLOR, colorA);

        db.insertOrThrow(DB_TABLE, null, values);
    }

    public Cursor getDB(String[] columns) {

        // queryメソッド DBのデータを取得
        // 第1引数：DBのテーブル名
        // 第2引数：取得するカラム名
        // 第3引数：選択条件(WHERE句)
        // 第4引数：第3引数のWHERE句において?を使用した場合に使用
        // 第5引数：集計条件(GROUP BY句)
        // 第6引数：選択条件(HAVING句)
        // 第7引数：ソート条件(ORDERBY句)
        return db.query(DB_TABLE, columns, null, null, null, null, null);
    }

    public Cursor searchDB(String[] columns, String column, String[] name) {
        return db.query(DB_TABLE, columns, column + " like ?", name, null, null, null);
    }

    /**
     * DBのレコードを全削除
     * allDelete()
     */
    public void allDelete() {

        db.beginTransaction();                      // トランザクション開始
        try {
            // deleteメソッド DBのレコードを削除
            // 第1引数：テーブル名
            // 第2引数：削除する条件式 nullの場合は全レコードを削除
            // 第3引数：第2引数で?を使用した場合に使用
            db.delete(DB_TABLE, null, null);        // DBのレコードを全削除
            db.setTransactionSuccessful();          // トランザクションへコミット
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();                    // トランザクションの終了
        }
    }

    private static class DBHelper extends SQLiteOpenHelper {

        Context mContext;

        DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VER);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // create table
            try {
                execSql(db, "sql/create");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // drop table
            try {
                execSql(db, "sql/drop");
            } catch (IOException e) {
                e.printStackTrace();
            }
            onCreate(db);
        }

        private void execSql(SQLiteDatabase db, String assetsDir) throws IOException {

            AssetManager as = mContext.getResources().getAssets();
            try {
                String files[] = as.list(assetsDir);
                for (int i = 0; i < files.length; i++) {
                    String str = readFile(as.open(assetsDir + "/" + files[i]));
                    for (String sql : str.split("/")) {
                        db.execSQL(sql);
                        db.setTransactionSuccessful();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
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
        }
    }
}
