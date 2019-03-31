package com.example.guesstheword;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;

import android.os.Bundle;

import android.support.constraint.ConstraintLayout;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
Button Startbutton;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.root_layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        animationDrawable.setEnterFadeDuration(2000);

        animationDrawable.setExitFadeDuration(4000);

        animationDrawable.start();

        Startbutton = findViewById(R.id.startbutton);

        Startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnPush", "onClick: Listener Invoked");
                Intent a = new Intent(MainActivity.this,GamePage.class);
                startActivity(a);
            }
        });

    }


}