package com.example.oubeika.tsumegonomori;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.DataSnapshot;

public class SgfLoad extends AppCompatActivity {

    final static String TAG = "LOG";
    TextView sgf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sgf_reader);
        Firebase.setAndroidContext(this);

        sgf = (TextView) findViewById(R.id.sgf);

        Firebase ref = new Firebase("https://tsumegonomori.firebaseio.com/problems/normal");

        Query query = ref.orderByKey();
        query.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                SgfData sgfData = snapshot.getValue(SgfData.class);
                String problem = sgfData.getProblems();
                Log.d("Firebase", String.format("onChildAdded, problem:%s", problem));
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildKey) {
                String problem = (String) snapshot.child("problem").getValue();
                Log.d("TAG", problem);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                String problem = (String) snapshot.child("problem").getValue();
                Log.d("TAG", problem);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
       /* ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                SgfData sgfData = snapshot.getValue(SgfData.class);
                Log.d("problem: ", sgfData.getProblems());
                Log.d("answers: ", sgfData.getAnswers());
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildKey) {
                String problem = (String) snapshot.child("problem").getValue();
                Log.d("updated:", problem);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                String problem = (String) snapshot.child("problem").getValue();
                Log.d("removed:", problem);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        }); */