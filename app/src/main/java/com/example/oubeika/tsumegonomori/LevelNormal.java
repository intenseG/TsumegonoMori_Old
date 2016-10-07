package com.example.oubeika.tsumegonomori;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static com.example.oubeika.tsumegonomori.GameConst.TAG;

public class LevelNormal extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_normal);

        JsonReader jsonReader = new JsonReader();   //問題データと解答データを読み込む

        GoData goData = new GoData();


    }
}
