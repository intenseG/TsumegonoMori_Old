package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.oubeika.tsumegonomori.GameConst.TAG;

public class ProblemList extends AppCompatActivity {

    //public static final String EXTRA_GODATA = "com.example.oubeika.tsumegonomori.GoData";

    private DBAdapter dbAdapter;
    private GoDataAdapter adapter;
    private List<GoData> goDataList;
    private GridView gridView;
    protected GoData goData;

    private String[] columns = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_list);

        /* //データベースヘルパーのインスタンスを作成する（まだデータベースはできない）
        GoDataOpenHelper dbHelper = new GoDataOpenHelper(this);
        //データベースオブジェクトを取得する（データベースにアクセスすると作成される。）
        SQLiteDatabase db = dbHelper.getWritableDatabase();*/

        dbAdapter = new DBAdapter(this);

        goDataList = new ArrayList<>();

        adapter = new GoDataAdapter(this, goDataList);

        gridView = (GridView) findViewById(R.id.problems_list);

        loadGoDataList();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                gridView = (GridView) parent;

                Cursor item = (Cursor)gridView.getItemAtPosition(pos);

                int problemId = item.getInt(item.getColumnIndex("_id"));
                String level = item.getString(item.getColumnIndex("q_level"));
                String turn = item.getString(item.getColumnIndex("q_turn"));

                Intent intent = new Intent(ProblemList.this, Problem.class);
                intent.putExtra("_id", problemId);
                intent.putExtra("level", level);
                intent.putExtra("turn", turn);

                startActivity(intent);
            }
        });

 /*       String sqlstr = "select * " +
                "from goDataInfo " +
                "inner join goDataZahyoP " +
                "on goDataInfo._id = goDataZahyoP.numP";

        Cursor cur = db.rawQuery(sqlstr, null);

        *//*Cursor cur =
                db.query("goDataInfo", new String[] { "_id", "q_level" },
                        null, null, null, null, null);*//*
        cur.moveToFirst();

        String[] from = {"_id", "q_level"};
        int[] viewId = new int[]{R.id.q_num, R.id.level};

        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(this, R.layout.list_item, cur, from, viewId, 0);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        GridView gridView = (GridView) parent;

                        Cursor item = (Cursor)gridView.getItemAtPosition(position);

                        int problemId = item.getInt(item.getColumnIndex("_id"));
                        String level = item.getString(item.getColumnIndex("q_level"));
                        //String turn = item.getString(item.getColumnIndex("q_turn"));

                        Intent intent = new Intent(ProblemList.this, Problem.class);
                        intent.putExtra("_id", problemId);
                        intent.putExtra("level", level);
                        //intent.putExtra("turn", turn);

                        startActivity(intent);
                    }
                });

        //データベースを閉じる
        db.close();*/
    }

    private void loadGoDataList() {

        goDataList.clear();

        dbAdapter.open();

        Cursor c = dbAdapter.getDB(columns);

        if(c.moveToFirst()){
            do {
                goData = new GoData(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3),
                        c.getInt(4),
                        c.getInt(5),
                        c.getInt(6),
                        c.getInt(7),
                        c.getInt(8)
                );

                goDataList.add(goData);

            } while(c.moveToNext());
        }
        c.close();
        dbAdapter.close();
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}