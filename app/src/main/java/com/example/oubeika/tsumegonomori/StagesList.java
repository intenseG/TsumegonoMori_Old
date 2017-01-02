package com.example.oubeika.tsumegonomori;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StagesList extends ListActivity implements AdapterView.OnItemClickListener {

    private SQLiteDatabase db;
    private List<GoData> goDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_list);

        ListView stage_list = (ListView) findViewById(android.R.id.list);
        stage_list.setOnItemClickListener(this);

        // Database接続
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        GoDataDao goDataDao = new GoDataDao(db);

        goDataList = goDataDao.findAll();

        ListAdapter adapter = new ListAdapter(this, goDataList);

        setListAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        ListView listView = (ListView) parent;

        GoData data = (GoData) listView.getItemAtPosition(pos);
        Intent intent = new Intent(StagesList.this, Problem.class);
        intent.putExtra("number", data.getNumber());
        intent.putExtra("level", data.getLevel());
        intent.putExtra("goDataP", data.getGoDataP());
        intent.putExtra("goDataA", data.getGoDataA());

        Log.d("number", data.getNumber());
        Log.d("level", data.getLevel());
        Log.d("goDataP", data.getGoDataP());
        Log.d("goDataA", data.getGoDataA());

   /*     Cursor c = (Cursor) listView.getItemAtPosition(pos);
        Intent intent = new Intent(StagesList.this, Problem.class);
        intent.putExtra("number", c.getString(c.getColumnIndex(GoDataDao.COL_NUMBER)));
        intent.putExtra("level", c.getString(c.getColumnIndex(GoDataDao.COL_LEVEL)));
        intent.putExtra("goDataP", c.getString(c.getColumnIndex(GoDataDao.COL_GODATA_P)));
        intent.putExtra("goDataA", c.getString(c.getColumnIndex(GoDataDao.COL_GODATA_A)));*/

        startActivity(intent);
    }
}