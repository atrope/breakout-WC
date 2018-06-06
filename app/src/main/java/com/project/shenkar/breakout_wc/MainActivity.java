package com.project.shenkar.breakout_wc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    protected static MyMusicRunnable musicPlayer;
    protected static MySFxRunnable effects;
    protected static boolean playSFX = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(PreferenceManager.getDefaultSharedPreferencesName(this), MODE_PRIVATE);
        if (effects == null ) effects = new MySFxRunnable(this);
        if (musicPlayer == null) musicPlayer = new MyMusicRunnable(this,settings.getInt(getString(R.string.volume_key),100));
        playSFX = settings.getBoolean(getString(R.string.pref_sfx_buttons),true);
        effects.run();
        musicPlayer.run();
        //Bensound.com
    }

    public void onSettingsClicked(View v) {
        if (playSFX) effects.play(R.raw.cartoon);
        startActivity(new Intent(MainActivity.this,SettingsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    public void onStartClicked(View v) {
        if (playSFX) effects.play(R.raw.cartoon);
        startActivity(new Intent(MainActivity.this,GameActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }



}