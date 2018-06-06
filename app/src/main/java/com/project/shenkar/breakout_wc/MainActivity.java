package com.project.shenkar.breakout_wc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static MyMusicRunnable musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (musicPlayer == null) musicPlayer = new MyMusicRunnable(this);


        musicPlayer.run();
        //Bensound.com

    }

    public void onSettingsClicked(View v) {
        startActivity(new Intent(MainActivity.this,SettingsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}