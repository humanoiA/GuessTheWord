package com.example.guesstheword;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ScorePage extends AppCompatActivity {
TextView Score;
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
        //Score.setText(message);
        String str=getIntent().getStringExtra("mytext");
        Log.d("Message passed: ", str);
        Score.setText("45");
        Score.setText(str);

    }
}
