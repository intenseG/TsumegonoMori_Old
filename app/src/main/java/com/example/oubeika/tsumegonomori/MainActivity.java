package com.example.oubeika.tsumegonomori;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.normal);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ProblemList.class);
        startActivity(intent);
    }
}
