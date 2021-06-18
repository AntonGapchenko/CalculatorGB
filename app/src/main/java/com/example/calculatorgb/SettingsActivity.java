package com.example.calculatorgb;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;


public class SettingsActivity extends AppCompatActivity {
    private Switch themeSwitcher;
    private SharedPref sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode());
        setContentView(R.layout.activity_settings);

        sharedPref = new SharedPref(this);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        themeSwitcher = findViewById(R.id.switch_theme);


        if (sharedPref.loadNightModeState()) {
            themeSwitcher.setChecked(true);
        }

        themeSwitcher.setOnClickListener(view -> {
            if (themeSwitcher.isChecked()) {
                sharedPref.setNightModeState(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                themeSwitcher.setChecked(true);

            } else {
                sharedPref.setNightModeState(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                themeSwitcher.setChecked(false);

            }
        });

        findViewById(R.id.button_Back).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }


}

