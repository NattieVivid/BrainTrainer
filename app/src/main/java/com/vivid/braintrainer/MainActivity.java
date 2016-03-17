package com.vivid.braintrainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    Button startButton;

    TextView tvTimeLeft;
    TextView tvScore;
    TextView tvQuestion;

    Button btnAnswer1;
    Button btnAnswer2;
    Button btnAnswer3;
    Button btnAnswer4;

    int A, B;
    int[] C = new int[4];

    int time, score;
    int tick;
    Timer myTimer;

    boolean timeIsUp;

    public void start(View view){

        time = Integer.parseInt(tvTimeLeft.getText().toString());

        startButton.setVisibility(View.INVISIBLE);
        show();
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        }, 0, 1000);

        GenerateNewQuestion();
        timeIsUp = false;

    }

    public void show(){

        btnAnswer1.setVisibility(View.VISIBLE);
        btnAnswer2.setVisibility(View.VISIBLE);
        btnAnswer3.setVisibility(View.VISIBLE);
        btnAnswer4.setVisibility(View.VISIBLE);

        tvTimeLeft.setVisibility(View.VISIBLE);
        tvScore.setVisibility(View.VISIBLE);
        tvQuestion.setVisibility(View.VISIBLE);

    }

    public void GenerateNewQuestion(){
        Random r = new Random();
        A = r.nextInt(49)+1;
        B = r.nextInt(49)+1;
        for (int i = 0; i < 4; i++){
            C[i] = r.nextInt(49)+1;
        }

        int a = r.nextInt(4);
        C[a] = A + B;

        tvQuestion.setText(Integer.toString(A)+"+"+Integer.toString(B));
        btnAnswer1.setText(Integer.toString(C[0]));
        btnAnswer2.setText(Integer.toString(C[1]));
        btnAnswer3.setText(Integer.toString(C[2]));
        btnAnswer4.setText(Integer.toString(C[3]));

    }

    public void OnClickBtn(View v){
        Button btn = (Button) v;
        if ((A + B) == Integer.parseInt(btn.getText().toString())) {
            score++;
            tvScore.setText(Integer.toString(score));
        }
        if (!timeIsUp)
          GenerateNewQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.VISIBLE);

        btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
        btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);
        btnAnswer3 = (Button) findViewById(R.id.btnAnswer3);
        btnAnswer4 = (Button) findViewById(R.id.btnAnswer4);

        tvTimeLeft = (TextView) findViewById(R.id.tvTimeLeft);
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        score = 0;

    }


    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            if (time != 0) {
                time--;
                tvTimeLeft.setText(Integer.toString(time));
            }
            else{
                tvTimeLeft.setText(Integer.toString(time));
                timeIsUp = true;
            }
        }
    };
}
