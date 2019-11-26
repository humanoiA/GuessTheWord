package com.example.guesstheword;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GamePage extends AppCompatActivity {
TextView loremipsum;
EditText inputText;
Button Check2;
Button quit;
TextView Score;
public int globalInt = 0,h=0;
public String finalTemp;
private StringBuilder text = new StringBuilder();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        BufferedReader reader = null;
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.gameLayout);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        animationDrawable.setEnterFadeDuration(2000);

        animationDrawable.setExitFadeDuration(4000);

        animationDrawable.start();
        loremipsum = findViewById(R.id.textView2);
        inputText = findViewById(R.id.editText3);
        Score=findViewById(R.id.Score);
        quit=findViewById(R.id.QUIT);

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("texts.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
            String a=text.toString();
            String[] attributeArray = a.split("\\d+");
            ArrayList<String> attributeList = new ArrayList<String>(Arrays.asList(attributeArray));
            attributeList.removeAll(Arrays.asList(null,""));
            String wordListtt[] = new String[attributeList.size()+1];
            for(int k=0;k<attributeList.size();k++)
            {
                wordListtt[k] = attributeList.get(k);
                Log.d("-_-", "Arr: "+wordListtt[k]);
            }
            Collections.shuffle(Arrays.asList(wordListtt));
            String[] wordListt = Arrays.stream(wordListtt)
                    .filter(value ->
                            value != null && value.length() > 0
                    )
                    .toArray(size -> new String[size]);
            for(int k=0;k<attributeList.size();k++)
            {
                Log.d(":)", "Arreee: "+wordListt[k]);
            }
            String[] wordArray = wordListt[0].split("\\s+");
            ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(wordArray));
            int halfWordCount = 1;
            int tracker = 0;
            Random random = new Random();
            int randomIndex=0;
            String temp="";
            Pattern regex = Pattern.compile("[$&+,:;=?@.#()|]");
            while(tracker < halfWordCount){
                randomIndex = random.nextInt(wordList.size());
                Matcher matcher = regex.matcher(wordList.get(randomIndex));
                if (wordList.get(randomIndex).length() > 3 && !matcher.find()) {
                    Log.d(String.valueOf(matcher.find()), "check: " + wordList.get(randomIndex).length());
                    temp = wordList.get(randomIndex);
                    Score.setText(temp);
                    wordList.set(randomIndex, "__________");
                    tracker++;
                }
            }
            Log.d("", "Array:"+wordList);
            loremipsum.setText(wordList.toString().replace(",","").replace("[","").replace(".","").replace("]",""));

            //Log.d("-_-: ", b);
            Check2 = findViewById(R.id.button2);
            //final int finalRandomIndex = randomIndex;
            finalTemp = temp;
            //final String finalTemp1 = temp;
            Check2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(h!=12)
                    {
                        Log.d("h val:  ", "h- "+Integer.toString(h));
                        String b = inputText.getText().toString();
                        Log.d(b, finalTemp);
                        if(b.equals(finalTemp)){
                            globalInt++;
                           // Score.setText(Integer.toString(globalInt));
                            String[] wordArray = wordListt[h].split("\\s+");
                            ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(wordArray));
                            int halfWordCount = 1;
                            int tracker = 0;
                            Random random = new Random();
                            int randomIndex=0;
                            String temp="";
                            while(tracker < halfWordCount){
                                randomIndex = random.nextInt(wordList.size());
                                temp=wordList.get(randomIndex);
                                Score.setText(temp);
                                wordList.set(randomIndex,"__________");
                                tracker++;
                            }
                            finalTemp = temp;
                        //    Log.d("", "Array:"+wordList);
                            loremipsum.setText(wordList.toString().replace(",","").replace("[","").replace(".","").replace("]",""));

                            //loremipsum.setText(wordList[].toString().replace(",","").replace("[","").replace(".","").replace("]",""));
                            Toast.makeText(getApplicationContext(),"Correct!!",Toast.LENGTH_LONG).show();
                            inputText.setText("");
                        }
                        else{
                            String[] wordArray = wordListt[h].split("\\s+");
                            ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(wordArray));
                            int halfWordCount = 1;
                            int tracker = 0;
                            Random random = new Random();
                            int randomIndex=0;
                            String temp="";
                            while(tracker < halfWordCount){
                                randomIndex = random.nextInt(wordList.size());
                                temp=wordList.get(randomIndex);
                                Score.setText(temp);
                                wordList.set(randomIndex,"__________");
                                tracker++;
                            }
                            finalTemp = temp;
                            //    Log.d("", "Array:"+wordList);
                            loremipsum.setText(wordList.toString().replace(",","").replace("[","").replace(".","").replace("]",""));
                            inputText.setText("");
                        }
                        h++;
                    }
                    else{
                        Intent intent = new Intent(GamePage.this, ScorePage.class);
                        intent.putExtra("mytext", Integer.toString(globalInt));
                        startActivity(intent);
                    }
                }
            });
            h=0;
            }
            quit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    h=0;
                    Intent intent = new Intent(GamePage.this, ScorePage.class);
                    intent.putExtra("mytext", Integer.toString(globalInt));
                    globalInt=0;
                    startActivity(intent);
                    //
                     finish();
                }
            });
        }
    }