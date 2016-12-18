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

import static android.support.v7.appcompat.R.styleable.View;

public class StagesList extends ListActivity {

    private SQLiteDatabase db;
    private List<GoData> goDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_list);

        ListView stage_list = (ListView) findViewById(android.R.id.list);

        // Database接続
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        GoDataDao goDataDao = new GoDataDao(db);

        goDataList = goDataDao.findAll();

        ListAdapter adapter = new ListAdapter(getApplicationContext(), goDataList);

        setListAdapter(adapter);

        stage_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                ListView listView = (ListView) parent;

                Cursor c = (Cursor) listView.getItemAtPosition(pos);
                Intent intent = new Intent(StagesList.this, Problem.class);
                intent.putExtra("number", c.getString(c.getColumnIndex(GoDataDao.COL_NUMBER)));
                intent.putExtra("level", c.getString(c.getColumnIndex(GoDataDao.COL_LEVEL)));
                intent.putExtra("goDataP", c.getString(c.getColumnIndex(GoDataDao.COL_GODATA_P)));
                intent.putExtra("goDataA", c.getString(c.getColumnIndex(GoDataDao.COL_GODATA_A)));

                startActivity(intent);
            }
        });
    }
}