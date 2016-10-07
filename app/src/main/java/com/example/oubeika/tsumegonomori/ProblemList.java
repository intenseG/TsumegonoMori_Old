package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class ProblemList extends AppCompatActivity{

    private Realm mRealm;
    private RealmResults<GoData> mGoDataRealmResults;
    private RealmChangeListener mRealmListener = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            reloadListView();
        }
    };

    private ListView mListView;
    private GoDataAdapter mGoDataAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_list);

        JsonReader jsonReader = new JsonReader();   //問題データと解答データを読み込む

        //Realmの設定
        mRealm = Realm.getDefaultInstance();
        mGoDataRealmResults = mRealm.where(GoData.class).findAll();
        mGoDataRealmResults.sort("date", Sort.DESCENDING);
        mRealm.addChangeListener(mRealmListener);

        //ListViewの設定
        mGoDataAdapter = new GoDataAdapter(ProblemList.this);
        mListView = (ListView) findViewById(R.id.listview1);

        //ListViewをタップしたときの処理
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProblemList.this, Problem.class);
                startActivity(intent);
            }
        });
        addGoData();
    }

        private void reloadListView(){

            ArrayList<GoData> goDataArrayList = new ArrayList<GoData>();

            for(int i = 0; i < mGoDataRealmResults.size(); i++) {
                GoData data = new GoData();

                data.setColP(mGoDataRealmResults.get(i).getColP());
                data.setRowP(mGoDataRealmResults.get(i).getRowP());
                data.setStoneColorP(mGoDataRealmResults.get(i).getStoneColorP());
                data.setColA(mGoDataRealmResults.get(i).getColA());
                data.setRowA(mGoDataRealmResults.get(i).getRowA());
                data.setStoneColorA(mGoDataRealmResults.get(i).getStoneColorA());

                goDataArrayList.add(data);
            }

            mGoDataAdapter.setmTaskArrayList(goDataArrayList);
            mListView.setAdapter(mGoDataAdapter);
            mGoDataAdapter.notifyDataSetChanged();
        }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        mRealm.close();
    }

    private void addGoData(){
        for(int i = 0; i < mGoDataRealmResults.size(); i++) {
            GoData data = new GoData();
            data.setColP(mGoDataRealmResults.get(i).getColP());
            data.setRowP(mGoDataRealmResults.get(i).getRowP());
            data.setStoneColorP(mGoDataRealmResults.get(i).getStoneColorP());
            data.setColA(mGoDataRealmResults.get(i).getColA());
            data.setRowA(mGoDataRealmResults.get(i).getRowA());
            data.setStoneColorA(mGoDataRealmResults.get(i).getStoneColorA());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(data);
        mRealm.commitTransaction();
        }
    }
}