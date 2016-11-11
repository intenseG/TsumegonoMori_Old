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

    private GridView gridView;
    private GoDataAdapter adapter;
    private List<GoData> goDataList = new ArrayList<GoData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_list);

        //データベースヘルパーのインスタンスを作成する（まだデータベースはできない）
        GoDataOpenHelper dbHelper = new GoDataOpenHelper(this);
        //データベースオブジェクトを取得する（データベースにアクセスすると作成される。）
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        gridView = (GridView) findViewById(R.id.problems_list);

        String sqlstr = "select * " +
                "from goDataInfo " +
                "inner join goDataZahyoP " +
                "on goDataInfo._id = goDataZahyoP.numP";

        Cursor cur = db.rawQuery(sqlstr, null);

        /*Cursor cur =
                db.query("goDataInfo", new String[] { "_id", "q_level" },
                        null, null, null, null, null);*/
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
        db.close();

   /*     //GridViewをタップしたときの処理
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                GoData goData = (GoData) parent.getAdapter().getItem(pos);

                Intent intent = new Intent(ProblemList.this, Problem.class);
                intent.putExtra(EXTRA_GODATA, goData);

                startActivity(intent);
            }
        });*/
    }

/*    public static final String EXTRA_GODATA = "com.example.oubeika.tsumegonomori.GoData";

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

        //GridViewの設定
        adapter = new GoDataAdapter(this);
        gridView = (GridView) findViewById(R.id.problems_list);

        //if (adapter == null) {
        //List<GoData> goDataList = null;
        try {
            results = loadGoData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(results.size() == 0) {
            //アプリ起動時に問題データが無かった場合は問題データを読み込む
            addProblems();
        }

        adapter.setGoDataList(results);

        //GridViewに表示
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //gridView.invalidate();
        //}

        //GridViewをタップしたときの処理
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                GoData goData = (GoData) parent.getAdapter().getItem(pos);

                Intent intent = new Intent(ProblemList.this, Problem.class);
                intent.putExtra(EXTRA_GODATA, goData);

                startActivity(intent);
            }
        });

        reloadGridView();
    }

    private RealmResults<GoData> loadGoData() throws IOException {

                InputStream is = null;

                //realm.beginTransaction();
                try {
                    String json = "";
                    // ファイルの読み込み
                    is = getAssets().open("sgfdata.json");
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
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
                    }
                    //答えデータ追加
                    //JSONArray easy_answers = answers.getJSONArray("easy");
                    JSONArray normal_answers = jsonObject.getJSONObject("answers").getJSONArray("normal");
                    for (int j = 0; j < normal_answers.length(); j++) {
                        String value2 = normal_answers.getString(j);
                        changer.GoDataSeparate(value2);
                    }
                    //realm.commitTransaction();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    //realm.cancelTransaction();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        return realm.where(GoData.class).findAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void addProblems() {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                //ArrayList<GoData> addGoDataArrayList = new ArrayList<>();
                GoData data = new GoData();

                for (int i = 0; i < results.size(); i++) {

                    Log.d("results_size1", String.valueOf(results.size()));
                    data.setId(results.get(i).getId());
                    data.setQNum(results.get(i).getQNum());
                    data.setLevel(results.get(i).getLevel());
                    data.setTeban(results.get(i).getTeban());
                    data.setColP(results.get(i).getColP());
                    data.setRowP(results.get(i).getRowP());
                    data.setStoneColorP(results.get(i).getStoneColorP());
                    data.setColA(results.get(i).getColA());
                    data.setRowA(results.get(i).getRowA());
                    data.setStoneColorA(results.get(i).getStoneColorA());

                    //addGoDataArrayList.add(data);
                }
            }
        });
      realm.beginTransaction();
        realm.copyToRealmOrUpdate(addGoDataArrayList);
        realm.commitTransaction();

    }

    private void reloadGridView() {

        ArrayList<GoData> goDataArrayList = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            if (!results.get(i).isValid()) continue;
            Log.d("results_size2", String.valueOf(results.size()));

            GoData data = new GoData();

            data.setId(results.get(i).getId());
            data.setQNum(results.get(i).getQNum());
            data.setLevel(results.get(i).getLevel());
            data.setTeban(results.get(i).getTeban());
            data.setColP(results.get(i).getColP());
            data.setRowP(results.get(i).getRowP());
            data.setStoneColorP(results.get(i).getStoneColorP());
            data.setColA(results.get(i).getColA());
            data.setRowA(results.get(i).getRowA());
            data.setStoneColorA(results.get(i).getStoneColorA());

            goDataArrayList.add(data);
        }

        adapter.setGoDataList(goDataArrayList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }*/
}