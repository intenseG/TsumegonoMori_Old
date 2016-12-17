package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ProblemList extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;

    private DBAdapter dbAdapter;
    private ListView stagesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_list);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        //stagesList = (ListView) findViewById(R.id.stages_list);

        items = new ArrayList<>();

        String[] columns = {DBAdapter.COL_ID, DBAdapter.COL_LEVEL};

        Cursor c = dbAdapter.getDB(columns);

        if (c.moveToFirst()) {
            do {
                items.add(c.getInt(0),
                        c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        dbAdapter.close();

        adapter = new ArrayAdapter<>
                (this, R.layout.list_item, items);

        stagesList.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        //loadGoDataList();

        stagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                ListView listView = (ListView) parent;

                Cursor cur = (Cursor) listView.getItemAtPosition(pos);

                int q_num = cur.getInt(cur.getColumnIndex(DBAdapter.COL_ID));
                String level = cur.getString(cur.getColumnIndex(DBAdapter.COL_LEVEL));
                String goData = cur.getString(cur.getColumnIndex(DBAdapter.COL_GODATA));

                Intent intent = new Intent(ProblemList.this, Problem.class);
                intent.putExtra("_id", q_num);
                intent.putExtra("level", level);
                intent.putExtra("goData", goData);

                startActivity(intent);
            }
        });
    }
}

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

   /* private void loadGoDataList() {

        db = new DBHelper(this).getWritableDatabase();
        Cursor c = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_expandable_list_item_2,
                c,
                new String[] {DBHelper.COL_ID, DBHelper.COL_LEVEL},
                new int[] {R.id.q_num, R.id.level},
                0);

        c.close();
        stagesList.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

       /* if (c.moveToFirst()) {
            do {
                goData = new GoData(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2));

                Log.d("取得したCursor(ID):", String.valueOf(c.getInt(0)));
                Log.d("取得したCursor(レベル):", c.getString(1));
                Log.d("取得したCursor(データ):", c.getString(2));

                items.add(goData);

            } while (c.moveToNext());
        }
        c.close();
        dbAdapter.close();
        stageList.setAdapter(goDataAdapter);
        goDataAdapter.notifyDataSetChanged();*/