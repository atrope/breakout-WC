package com.project.shenkar.breakout_wc;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v7.preference.SeekBarPreference;

import android.widget.SeekBar;
public class SettingsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getFragmentManager().findFragmentById(R.xml.preferences) == null)
            getFragmentManager().beginTransaction().add(android.R.id.content, new SettingsFragment()).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
        static public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
            {
                if (key.equals(getString(R.string.volume_key))) {
                    float volume = (float) sharedPreferences.getInt(key,100);
                    MainActivity.musicPlayer.setVolume(volume);
                }
                else if (key.equals(getString(R.string.pref_sfx_buttons))) {
                    MainActivity.playSFX = sharedPreferences.getBoolean(key,true);
            }

            }
            @Override
            public void onResume() {
                super.onResume();
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

            }
            @Override
            public void onPause() {
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
                super.onPause();
            }


    }

}


