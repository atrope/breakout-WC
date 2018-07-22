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
import android.widget.TextView;

public class InstructionsActivity extends AppCompatActivity {
    protected TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        instructions = findViewById(R.id.instructions);
        instructions.setText(" The endgame here is really easy!\n " +
                "Finnish All Blocks without losing all lives :) \n " +
                "To move the paddle, just press with the finger \n " +
                "on the side you want. \n " +
                "Keep it pressed until you want the paddle to stop.");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


