package com.example.guesstheword;

import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScorePage extends AppCompatActivity {
    TextView Score, Score2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.scorelayout);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        animationDrawable.setEnterFadeDuration(2000);

        animationDrawable.setExitFadeDuration(4000);

        animationDrawable.start();
        Score=(TextView)findViewById(R.id.score);
        Score2 = (TextView) findViewById(R.id.score2);
        //Score.setText(message);
        String str=getIntent().getStringExtra("mytext");
        Log.d("Message passed: ", str);
        DBManager db = new DBManager(ScorePage.this);
        db.open();
        List<Integer> sc = new ArrayList<Integer>();
        db.insert("Kunal", str);
        Cursor scores = db.fetch();
        Score.setText("45");
        Score2.setText(str);
        if (scores.moveToFirst()) {
            while (!scores.isAfterLast()) {
                String name = scores.getString(scores.getColumnIndex("SCORE"));

                sc.add(Integer.parseInt(name));
                scores.moveToNext();
            }
        }
        Score.setText(Collections.max(sc).toString());
    }
}
