package com.example.fingerspeedgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   private TextView timer,thousend;
   private Button button;
   private CountDownTimer countDownTimer;
   private int countDownInterval = 1000;
   private long millisInFuture = 60000;
   private int timerText = 60;
   private int thousendText = 10;
   private final String THOUSEND_TEXT_KEY = "thousendText";
   private final String TIMER_TEXT_KEY = "timerText";


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(THOUSEND_TEXT_KEY,thousendText);
        outState.putInt(TIMER_TEXT_KEY, timerText);
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        timer.setText(timerText + "");
        thousend.setText(thousendText + "");
        if(savedInstanceState != null){
            timerText = savedInstanceState.getInt(TIMER_TEXT_KEY);
            thousendText = savedInstanceState.getInt(THOUSEND_TEXT_KEY);
            timer.setText(timerText + "");
            thousend.setText(thousendText + "");
                countDownTimer = new CountDownTimer((long) timerText * 1000, countDownInterval) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerText = (int) millisUntilFinished / 1000;
                        timerText--;
                        timer.setText(timerText + "");
                    }

                    @Override
                    public void onFinish() {
                        timer.setText(60 + "");
                        Toast.makeText(MainActivity.this, "timer is finished", Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();

        }

        button.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            @Override
            public void onClick(View v) {
                alertDialog.setTitle("end of the game");
                thousendText--;
                thousend.setText(thousendText + "");
                if(timerText > 0 && thousendText == 0){
                    alertDialog.setMessage("Do You Want To ReStart the game ?");
                    alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                        }
                    });
                    alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alertDialog.create().show();
                }
            }
        });

        if(savedInstanceState == null) {

            countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerText = (int) millisUntilFinished / 1000;
                    timerText--;
                    timer.setText(timerText + "");

                }

                @Override
                public void onFinish() {
                    timer.setText(60 + "");
                    Toast.makeText(MainActivity.this, "timer is finished", Toast.LENGTH_LONG).show();

                }
            };
            countDownTimer.start();
        }
    }
    public void initWidget(){
        timer = findViewById(R.id.timer);
        thousend = findViewById(R.id.thousend);
        button = findViewById(R.id.button);
    }
    private void reset(){
        thousendText = 10;
        thousend.setText(String.valueOf(thousendText));
        timer.setText(timerText + "");
        countDownTimer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Toast.makeText(MainActivity.this,"settings is selected",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}