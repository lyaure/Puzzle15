package com.example.lyaure.puzzle15;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mp;

    private GameBoard board;
    private TextView[][] txtV;
    private TextView cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9,
            cell10, cell11, cell12, cell13, cell14, cell15, cell16;
    private TextView moves;
    private TextView time;

    private Button btnNewGame;

    private boolean isMusicOn = false;

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mp = MediaPlayer.create(this, R.raw.song);

        SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        isMusicOn = sp.getBoolean("music", false);
        if(isMusicOn){
            mp.setLooping(true);
            mp.start();
        }

        time = findViewById(R.id.txvTimeID);

        thread = createTimer();
        thread.start();

        this.btnNewGame = findViewById(R.id.btnNewID);
        this.btnNewGame.setOnClickListener(this);

        this.moves = findViewById(R.id.txvMovesID);

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

        txtV = new TextView[4][4];
        txtV[0][0] = cell1; txtV[0][1] = cell2; txtV[0][2] = cell3; txtV[0][3] = cell4;
        txtV[1][0] = cell5; txtV[1][1] = cell6; txtV[1][2] = cell7; txtV[1][3] = cell8;
        txtV[2][0] = cell9; txtV[2][1] = cell10; txtV[2][2] = cell11; txtV[2][3] = cell12;
        txtV[3][0] = cell13; txtV[3][1] = cell14; txtV[3][2] = cell15; txtV[3][3] = cell16;

        board = new GameBoard();
        setClick(true);
        moves.setText("Moves: " + this.board.getMoves());

        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++){
                txtV[i][j].setOnClickListener(this);
            }

        display();

    }

    public void display(){
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++) {
                txtV[i][j].setText(board.returnString(i, j));
                if(board.returnString(i, j) == "")
                    txtV[i][j].setBackgroundResource(R.drawable.textview_border_blank);
                else
                    txtV[i][j].setBackgroundResource(R.drawable.textview_border);
            }
        moves.setText("Moves: " + this.board.getMoves());
    }

    @Override
    public void onClick(View view) {
        if(view == this.btnNewGame) {
            this.board.newBoard();
            setClick(true);
            display();
        }
        else
        {
            for(int i=0; i<4; i++)
                for(int j=0; j<4; j++){
                    if(txtV[i][j] == view)
                        if(board.move(i, j)) {
                            display();
                            if(this.board.isGameOver()) {
                                Toast.makeText(this, "Game Over - Puzzle Solved", Toast.LENGTH_LONG).show();
                                setClick(false);
                                break;
                            }
                        }
                }
        }
    }

    public void setClick(boolean b){
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                txtV[i][j].setClickable(b);
    }

    @Override
    public void onPause(){
        super.onPause();
        if(isMusicOn)
            mp.pause();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(isMusicOn)
            mp.start();
    }

    public Thread createTimer(){
        return new Thread(new Runnable() {
            int s = 0, m = 0;
            @Override
            public void run() {
                while(true){
                    s++;
                    if(s == 60){
                        m++;
                        s = 0;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            time.setText("Time: " + String.format("%02d", m) + ":" + String.format("%02d", s));
                        }
                    });
                    SystemClock.sleep(1000);
                }
            }
        });
    }
}
