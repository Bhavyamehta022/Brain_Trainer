package com.bhavya.brain_trainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //todo
    Button goButton;
    TextView sum;
    int a,b;
    int correctPosition;
    int score=0;
    int totalQuestion=0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView result;
    TextView timer;
    TextView scores;
    int incorrect;
    int x;
    ConstraintLayout game;
    LinearLayout play;
    ArrayList<Integer> answers;
    TextView finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=(Button)findViewById(R.id.start);
        sum=(TextView)findViewById(R.id.sum);
        scores=(TextView)findViewById(R.id.score);
        result=(TextView)findViewById(R.id.correct) ;
        timer=(TextView)findViewById(R.id.seconds);
        button0=(Button)findViewById(R.id.button1);
        button1=(Button)findViewById(R.id.button2);
        button2=(Button)findViewById(R.id.button3);
        button3=(Button)findViewById(R.id.button4);
        game=(ConstraintLayout) findViewById(R.id.layout);
        play=(LinearLayout)findViewById(R.id.show);
        finalScore=(TextView)findViewById(R.id.finalScore);
        //timer



    }
    public void check(View v)
    {
        //check the answer and update the score
        if(v.getTag().equals(Integer.toString(correctPosition)))
        {
            result.setText("Correct!");
            score++;
        }
        else
        {
            result.setText("Wrong!");
        }
        totalQuestion++;
        scores.setText(String.valueOf(score)+"/"+String.valueOf(totalQuestion));
        generate(findViewById(R.id.show));
    }

    public void start(View v)
    {

        goButton.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);
        generate(v);
        //timer
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText((int)millisUntilFinished/1000+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                play.setVisibility(View.VISIBLE);
                // update final score
                finalScore.setText("Your score is : " + String.valueOf(score)+ "/" + String.valueOf(totalQuestion));
                game.setVisibility(View.INVISIBLE);
            }
        }.start();
    }
    public void generate(View v)
    {
        game.setVisibility(View.VISIBLE);
        play.setVisibility(View.INVISIBLE);
        answers=new ArrayList<Integer>();
        Random rand=new Random();
        //todo
        a=rand.nextInt(21);
        b=rand.nextInt(21);
        //updating question
        sum.setText(String.valueOf(a) + "+" + String.valueOf(b));
        //clearing answers array and updating it each time
        answers.clear();
        correctPosition=rand.nextInt(4);
        for(int i=0;i<4;i++)
        {
            if(i==correctPosition)
            {
                answers.add(a+b);
            }
            incorrect=a+b;
            //to check whether random generates 2 correct options; if this is the case, generate again;
            x=rand.nextInt(40);

            while(incorrect==x)
            {
                x=rand.nextInt(40);
            }
            answers.add(x);
        }
        //updating all the buttons
        button0.setText(String.valueOf(answers.get(0)));
        button1.setText(String.valueOf(answers.get(1)));
        button2.setText(String.valueOf(answers.get(2)));
        button3.setText(String.valueOf(answers.get(3)));

    }
}
