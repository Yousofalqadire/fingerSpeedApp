package com.example.fingerspeedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level2Activity extends AppCompatActivity {
    private TextView timer,thousend;
    private Button button;
    private CountDownTimer countDownTimer2;
    private int countDownInterval = 1000;
    private long millisInFuture = 60000;
    private int timerText = 60;
    private int thousendText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         initWidget();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        int n = bundle.getInt("number");
        thousendText = n;
        thousend.setText(thousendText + "");
        timer.setText(timerText + "");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thousendText--;
                thousend.setText(thousendText + "");
            }
        });
        countDownTimer2 = new CountDownTimer(millisInFuture,countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText = (int) millisUntilFinished/1000;
                timer.setText(timerText + "");
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer2.start();
    }
    public void initWidget(){
        timer = findViewById(R.id.timer2);
        thousend = findViewById(R.id.thousend2);
        button = findViewById(R.id.button2);
    }

}