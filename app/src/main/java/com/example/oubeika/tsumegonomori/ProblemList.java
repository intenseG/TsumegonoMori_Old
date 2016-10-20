package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.example.oubeika.tsumegonomori.GameConst.TAG;

public class ProblemList extends AppCompatActivity {

    public static final String EXTRA_GODATA = "com.example.oubeika.tsumegonomori.GoData";

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

        //Realmの設定
        mRealm = Realm.getDefaultInstance();
        //mGoDataRealmResults.sort("date", Sort.DESCENDING);
        mRealm.addChangeListener(mRealmListener);
        mGoDataRealmResults = mRealm.where(GoData.class).findAll();


        //ListViewの設定
        mGoDataAdapter = new GoDataAdapter(ProblemList.this);
        mListView = (ListView) findViewById(R.id.listView1);

        //ListViewをタップしたときの処理
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GoData goData = (GoData) parent.getAdapter().getItem(position);

                Intent intent = new Intent(ProblemList.this, Problem.class);
                intent.putExtra(EXTRA_GODATA, goData);

                startActivity(intent);
            }
        });
        addGoData();

        reloadListView();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mGoDataAdapter != null) {
            List<GoData> data = null;
            try {
                data = loadGoData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mGoDataAdapter = new GoDataAdapter(this);
            mGoDataAdapter.setGoData(data);
            mGoDataAdapter.notifyDataSetChanged();
        }
        addGoData();

        reloadListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRealm.close();
    }

    public List<GoData> loadGoData() throws IOException {

        loadJsonFromStream();
        //loadJsonFromJsonObject();
        //loadJsonFromString();

        return mRealm.where(GoData.class).findAll();
    }

/*    private void loadJsonFromStream() throws IOException {

        InputStream stream = getAssets().open("sgfdata.json");

        mRealm.beginTransaction();
        try {
            mRealm.createAllFromJson(GoData.class, stream);
            mRealm.commitTransaction();
        } catch (IOException e) {
            mRealm.cancelTransaction();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }*/

    private void loadJsonFromStream() throws IOException {

        BufferedReader br;
        String json = "";

        // ファイルの読み込み
        InputStream is = this.getAssets().open("sgfdata.json");

        try {
            //is = this.getAssets().open("sgfdata.json");
            //mRealm.createAllFromJson(GoData.class, is);
            br = new BufferedReader((new InputStreamReader(is)));
            String s;
            while ((s = br.readLine()) != null) {
                json += s;
            }
            Log.d(TAG, json);

            JSONObject jsonObject = new JSONObject(json);

            // 問題データ追加
            //JSONArray easy = problems.getJSONArray("easy");
            ZahyoChanger changer = new ZahyoChanger();
            JSONArray normal_problem = jsonObject.getJSONObject("problems").getJSONArray("normal");
            for (int i = 0; i < normal_problem.length(); i++) {
                String value1 = normal_problem.getString(i);
                changer.GoDataSeparate(value1);
                Log.d(TAG, "value1は " + value1 + " です!");
            }
            //答えデータ追加
            //JSONArray easy_answers = answers.getJSONArray("easy");
            JSONArray normal_answers = jsonObject.getJSONObject("answers").getJSONArray("normal");
            for (int j = 0; j < normal_answers.length(); j++) {
                String value2 = normal_answers.getString(j);
                changer.GoDataSeparate(value2);
                Log.d(TAG, "value2は " + value2 + " です!");
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

/*
    // if(mGoDataRealmResults.size() != 0){

    addGoData();
    // }

    reloadListView();
}
*/

/*    private void loadJsonFromJsonObject() {
        Map<String, String> data = new HashMap<>();
        data.put("qNum", "1");
        data.put("level", "-5");
        final JSONObject json = new JSONObject(data);

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObjectFromJson(GoData.class, json);
            }
        });
    }*/

/*    private void loadJsonFromString() {
        final String json = "{ qNum: 2, level: -3 }";

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObjectFromJson(GoData.class, json);
            }
        });
    }*/

    private void reloadListView() {

        ArrayList<GoData> goDataArrayList = new ArrayList<>();

        for (int i = 0; i < mGoDataRealmResults.size(); i++) {
            Log.d(TAG, String.valueOf(mGoDataRealmResults.size()));

            GoData data = new GoData();

            data.setQNum(mGoDataRealmResults.get(i).getQNum());
            data.setLevel(mGoDataRealmResults.get(i).getLevel());

            goDataArrayList.add(data);
        }

        mGoDataAdapter.setGoData(goDataArrayList);
        mListView.setAdapter(mGoDataAdapter);
        mGoDataAdapter.notifyDataSetChanged();
    }

    private void addGoData() {

        for (int j = 0; j < mGoDataRealmResults.size(); j++) {
            Log.d(TAG, String.valueOf(mGoDataRealmResults.size()));
            GoData data = new GoData();

            data.setQNum(mGoDataRealmResults.get(j).getQNum());
            data.setTeban(mGoDataRealmResults.get(j).getTeban());
            data.setLevel(mGoDataRealmResults.get(j).getLevel());

            data.setColP(mGoDataRealmResults.get(j).getColP());
            data.setRowP(mGoDataRealmResults.get(j).getRowP());
            data.setStoneColorP(mGoDataRealmResults.get(j).getStoneColorP());
            data.setColA(mGoDataRealmResults.get(j).getColA());
            data.setRowA(mGoDataRealmResults.get(j).getRowA());
            data.setStoneColorA(mGoDataRealmResults.get(j).getStoneColorA());

            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(data);
            mRealm.commitTransaction();

            mRealm.close();
        }
    }
}