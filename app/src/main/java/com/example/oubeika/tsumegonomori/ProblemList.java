package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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

    //public static final String EXTRA_GODATA = "com.example.oubeika.tsumegonomori.GoData";

    private Realm realm;
    private RealmResults<GoData> results;
    private RealmChangeListener realmListener = new RealmChangeListener() {

        @Override
        public void onChange(Object element) {
            reloadGridView();
        }
    };

    private GridView gridView;
    private GoDataAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_list);

        //Realmの設定
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(realmListener);
        results = realm.where(GoData.class).findAll();

        //ListViewの設定
        gridView = (GridView) findViewById(R.id.problems_list);

        if (adapter == null) {
            List<GoData> goDataList = null;
            try {
                goDataList = loadGoData();
            } catch (IOException e) {
                e.printStackTrace();
            }

            adapter = new GoDataAdapter(this);
            adapter.setGoData(goDataList);

            //ListViewに表示
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            gridView.invalidate();
        }

        //ListViewをタップしたときの処理
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                GoData goData = (GoData) parent.getAdapter().getItem(pos);

                Intent intent = new Intent(ProblemList.this, Problem.class);
                intent.putExtra("godata_q_num", goData.getQNum());
                intent.putExtra("godata_level", goData.getLevel());
                intent.putExtra("godata_turn", goData.getTeban());

                startActivity(intent);
            }
        });
        reloadGridView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private List<GoData> loadGoData() throws IOException {

        loadJsonFromStream();
        realmSync();

        return realm.where(GoData.class).findAll();
    }

    private void loadJsonFromStream() throws IOException {

        InputStream is = getAssets().open("sgfdata.json");

        //realm.beginTransaction();
        try {
            String json = "";
            // ファイルの読み込み
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String s;
            while ((s = br.readLine()) != null) {
                json += s;
            }
            JSONObject jsonObject = new JSONObject(json);

            // 問題データ追加
            //JSONArray easy = problems.getJSONArray("easy");
            ZahyoChanger changer = new ZahyoChanger();
            JSONArray normal_problem = jsonObject.getJSONObject("problems").getJSONArray("normal");
            for (int i = 0; i < normal_problem.length(); i++) {
                Log.d(TAG, String.valueOf(normal_problem.length()));

                String value1 = normal_problem.getString(i);
                changer.GoDataSeparate(value1);
                //realm.createAllFromJson(GoData.class, normal_problem);
            }
            //答えデータ追加
            //JSONArray easy_answers = answers.getJSONArray("easy");
            JSONArray normal_answers = jsonObject.getJSONObject("answers").getJSONArray("normal");
            for (int j = 0; j < normal_answers.length(); j++) {
                String value2 = normal_answers.getString(j);
                changer.GoDataSeparate(value2);
                //realm.createAllFromJson(GoData.class, normal_answers);
            }
            //realm.commitTransaction();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            //realm.cancelTransaction();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private void realmSync() {

        GoData data = new GoData();

        for (int i = 0; i < results.size(); i++) {
            Log.d("results_size1", String.valueOf(results.size()));
            data.setQNum("Q" + (i + 1));
            data.setLevel(results.get(i).getLevel());
            data.setTeban(results.get(i).getTeban());
            data.setColP(results.get(i).getColP());
            data.setRowP(results.get(i).getRowP());
            data.setStoneColorP(results.get(i).getStoneColorP());
            data.setColA(results.get(i).getColA());
            data.setRowA(results.get(i).getRowA());
            data.setStoneColorA(results.get(i).getStoneColorA());
        }
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(data);
        realm.commitTransaction();
    }

/*    Dog fido = realm.createObject(Dog.class);
    fido.name = "fido";
    for (int i = 0; i < 10; i++) {
        Person person = realm.createObject(Person.class);
        person.setId(i);
        person.setName("Person no. " + i);
        person.setAge(i);
        person.setDog(fido);*/

/*    // Iterate over all objects
    for (Person pers : realm.where(Person.class).findAll()) {
        String dogName;
        if (pers.getDog() == null) {
            dogName = "None";
        } else {
            dogName = pers.getDog().name;
        }
        status += "\n" + pers.getName() + ":" + pers.getAge() + " : " + dogName + " : " + pers.getCats().size();
    }*/

    private void reloadGridView() {

        ArrayList<GoData> goDataArrayList = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            Log.d("results_size2", String.valueOf(results.size()));
            Log.d(TAG, String.valueOf(results.get(0)));

            GoData data = new GoData();

            data.setQNum("Q" + (i + 1));
            data.setLevel(results.get(0).getLevel());

            goDataArrayList.add(data);
        }

        //adapter = new GoDataAdapter(this);
        adapter.setGoData(goDataArrayList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}