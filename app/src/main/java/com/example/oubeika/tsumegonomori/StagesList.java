package com.example.oubeika.tsumegonomori;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class StagesList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_list);

        GoData goData = new GoData();
        goData.setId(1);
        //goData.setLevel("5級");
        goData.setGoData("AB::pp;pq;np;mq;ro;po;rm;qo;mr;kr@AW::qp;oq;qq;pr;qr;nr,B;sr");

        List<GoData> list = new ArrayList<GoData>();
        list.add(goData);

        ListAdapter adapter = new ListAdapter(getApplicationContext(),list);

        setListAdapter(adapter);
    }
}
