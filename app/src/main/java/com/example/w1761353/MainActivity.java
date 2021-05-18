package com.example.w1761353;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "Value";
    public Switch switchTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchTimer = findViewById(R.id.timer);
    }

    //------------------------- onClick() methods used to launch each Activity -------------------------//
    public void launch_IdentifyCarMakeGameActivity(View view) {
        Intent intent = new Intent(this, IdentifyCarMakeGameActivity.class);
        intent.putExtra(EXTRA_MESSAGE,switchTimer.isChecked());
        startActivity(intent);
    }
    public void launch_HintsGameActivity(View view) {
        Intent intent = new Intent(this, HintsGameActivity.class);
        intent.putExtra(EXTRA_MESSAGE,switchTimer.isChecked());
        startActivity(intent);
    }
    public void launch_IdentifyCarImageGameActivity(View view) {
        Intent intent = new Intent(this, IdentifyCarImageGameActivity.class);
        intent.putExtra(EXTRA_MESSAGE,switchTimer.isChecked());
        startActivity(intent);
    }
    public void launch_AdvancedLevelGameActivity(View view) {
        Intent intent = new Intent(this, AdvancedLevelGameActivity.class);
        intent.putExtra(EXTRA_MESSAGE,switchTimer.isChecked());
        startActivity(intent);
    }

}