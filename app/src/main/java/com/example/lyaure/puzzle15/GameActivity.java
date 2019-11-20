package com.example.lyaure.puzzle15;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9,
            cell10, cell11, cell12, cell13, cell14, cell15, cell16;

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

        cell1 = findViewById(R.id.cell1);
        cell2 = findViewById(R.id.cell2);
        cell3 = findViewById(R.id.cell3);
        cell4 = findViewById(R.id.cell4);
        cell5 = findViewById(R.id.cell5);
        cell6 = findViewById(R.id.cell6);
        cell7 = findViewById(R.id.cell7);
        cell8 = findViewById(R.id.cell8);
        cell9 = findViewById(R.id.cell9);
        cell10 = findViewById(R.id.cell10);
        cell11 = findViewById(R.id.cell11);
        cell12 = findViewById(R.id.cell12);
        cell13 = findViewById(R.id.cell13);
        cell14 = findViewById(R.id.cell14);
        cell15 = findViewById(R.id.cell15);
        cell16 = findViewById(R.id.cell16);

        TextView[][] txtV = new TextView[4][4];
        txtV[0][0] = cell1; txtV[0][1] = cell2; txtV[0][2] = cell3; txtV[0][3] = cell4;
        txtV[1][0] = cell5; txtV[1][1] = cell6; txtV[1][2] = cell7; txtV[1][3] = cell8;
        txtV[2][0] = cell9; txtV[2][1] = cell10; txtV[2][2] = cell11; txtV[2][3] = cell12;
        txtV[3][0] = cell13; txtV[3][1] = cell14; txtV[3][2] = cell15; txtV[3][3] = cell16;

        GameBoard board = new GameBoard();

        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++) {
                txtV[i][j].setText(board.returnString(i, j));
                if(board.returnString(i, j) == "")
                    txtV[i][j].setBackgroundResource(R.drawable.textview_border_blank);
                else
                    txtV[i][j].setBackgroundResource(R.drawable.textview_border);
            }


    }


}
