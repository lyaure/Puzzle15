package com.example.lyaure.puzzle15;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity implements OnClickListener{
    private Switch music;
    private Button start;
    private SharedPreferences sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        music = findViewById(R.id.switch_music);
        boolean m = sp.getBoolean("music", false);
        music.setChecked(m);
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("music", true);
                    editor.commit();
                }
                else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("music", false);
                    editor.commit();
                }
            }
        });

        start = findViewById(R.id.btnStartID);
        start.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuItem menuAbout = menu.add("About");
        MenuItem menuExit = menu.add("Exit");

        menuAbout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                showAboutDialog();
                return true;
            }
        });

        menuExit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                showExitDialog();
                return true;
            }
        });
        return true;
    }

    private void showAboutDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("About Puzzle 15");
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setMessage("This app implements the Game of Fifteen\n\nBy Lyaure & Samuel (c)");
        alertDialog.show();
    }

    private void showExitDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Exit Puzzle 15");
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setMessage("Do you really want to exit?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //mp.stop();
                finish();  // destroy this activity
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        alertDialog.show();
    }

    public void onClick(View view)
    {
        if(view == this.start)
        {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
    }

}
