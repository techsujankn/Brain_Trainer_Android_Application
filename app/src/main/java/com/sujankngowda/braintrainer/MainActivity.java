package com.sujankngowda.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView sumTextView;
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView resultText;
    TextView scoreView;
    TextView timerText;
    int locationOfCorrectAnswer;
    CountDownTimer countDownTimer;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    int score=0;
    int numberOfQuestions=0;
    Button playAgainButton;
    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        timerText.setText(30+"s");
        scoreView.setText(score+"/"+numberOfQuestions);
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        countDownTimer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                resultText.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultText.setText("Correct!");
            score++;
        }
        else{
            resultText.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreView.setText(score+"/"+numberOfQuestions);
        newQuestion();
    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timer));

    }
    public void newQuestion(){
        Random random=new Random();
        int a= random.nextInt(21);
        int b=random.nextInt(21);
        sumTextView.setText(a+"+"+b);
        locationOfCorrectAnswer=random.nextInt(4);
        answer.clear();
        for (int i=0;i<4;i++){
            if (i==locationOfCorrectAnswer){
                answer.add(a+b);
            }
            else{
                int wrongAnswer=random.nextInt(41);
                while (wrongAnswer==a+b){
                    wrongAnswer=random.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText=findViewById(R.id.resultView);
        scoreView=findViewById(R.id.marksView);
         button0=findViewById(R.id.button0);
         button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        timerText=findViewById(R.id.timer);
        sumTextView=findViewById(R.id.sumText);
        goButton=findViewById(R.id.button);
        playAgainButton=findViewById(R.id.playAgain);
        gameLayout=findViewById(R.id.gameLayout);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}