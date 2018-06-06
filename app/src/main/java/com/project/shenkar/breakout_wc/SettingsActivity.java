package com.project.shenkar.breakout_wc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity{
    private static MyMusicRunnable musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  //      musicPlayer.run();
    }

    @Override
    public boolean onSupportNavigateUp() {
//        musicPlayer.run();
        onBackPressed();
        return true;
    }

}

