package com.example.lyaure.puzzle15;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        time = findViewById(R.id.time);


        int secondsToRun = 999;

        ValueAnimator timer = ValueAnimator.ofInt(secondsToRun);
        timer.setDuration(secondsToRun * 1000).setInterpolator(new LinearInterpolator());
        timer.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                int elapsedSeconds = (int) animation.getAnimatedValue();
                int minutes = elapsedSeconds / 60;
                int seconds = elapsedSeconds % 60;

                time.setText(String.format("Time: %02d:%02d", minutes, seconds));
            }
        });
        timer.start();



    }


}
