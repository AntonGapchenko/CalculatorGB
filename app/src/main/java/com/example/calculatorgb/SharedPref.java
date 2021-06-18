package com.example.calculatorgb;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static final String APP_PREFERENCES = "my settings";
    private static final String NIGHT_MODE = "NightMode";

    private SharedPreferences mySharedPref;

    public SharedPref(Context context) {
        mySharedPref = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean value) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean(NIGHT_MODE, value);
        editor.apply();
    }

    public Boolean loadNightModeState() {
        return mySharedPref.getBoolean(NIGHT_MODE, false);
    }
}

