package com.example.oubeika.tsumegonomori;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DBAdapter {

    private static final String DB_NAME = "godata.db";
    private static final String TABLE_NAME = "goData";
    private static final int DB_VER = 1;

    public static final String COL_ID = "_id";
    public static final String COL_LEVEL = "level";
    public static final String COL_GODATA = "godata";

    private SQLiteDatabase db = null;
    private DBHelper dbHelper = null;
    protected Context context;

    DBAdapter(Context context) {
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
        db.close();
        db = null;
    }

/*    public boolean deleteAllNotes(){
        return db.delete("goDataInfo", null, null) > 0;
    }

    public boolean deleteNote(int id){
        return db.delete("goDataInfo", "_id" + "=" + id, null) > 0;
    }
    public Cursor getAllNotes(){
        return db.query("goDataInfo", null, null, null, null, null, null);
    }
*/

    public void saveDB(int id, String level, String goData) {

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(COL_ID, id);
            values.put(COL_LEVEL, level);
            values.put(COL_GODATA, goData);

            db.insert(TABLE_NAME, null, values);      //レコードへ登録

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
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
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    public Cursor searchDB(String[] columns, String column, String[] name) {
        return db.query(TABLE_NAME, columns, column + " like ?", name, null, null, null);
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
            db.delete(TABLE_NAME, null, null);        // DBのレコードを全削除
            db.setTransactionSuccessful();          // トランザクションへコミット
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();                    // トランザクションの終了
        }
    }

    public void selectDelete(String pos) {

        db.beginTransaction();                      // トランザクション開始
        try {
            db.delete(TABLE_NAME, COL_ID + "=?", new String[]{pos});
            db.setTransactionSuccessful();          // トランザクションへコミット
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();                    // トランザクションの終了
        }
    }

    private static class DBHelper extends SQLiteOpenHelper {

        private Context mContext;

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VER);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                loadSql(db,"sql/create");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("drop table if exists" + TABLE_NAME);

            onCreate(db);
        }

        private void loadSql(SQLiteDatabase db,String assetsDir) throws IOException {
            AssetManager as = mContext.getResources().getAssets();
            try {
                String files[] = as.list(assetsDir);
                for (int i = 0; i < files.length; i++) {
                    String str = readFile(as.open(assetsDir + "/" + files[i]));
                    for (String sql: str.split("/")){
                        db.execSQL(sql);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String readFile(InputStream is) throws IOException{
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(is,"SJIS"));

                StringBuilder sb = new StringBuilder();
                String str;
                while((str = br.readLine()) != null){
                    sb.append(str +"\n");
                }
                return sb.toString();
            } finally {
                if (br != null) br.close();
            }
        }
    }
}
