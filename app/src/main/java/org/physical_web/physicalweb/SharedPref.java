package org.physical_web.physicalweb;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class SharedPref {

    public SharedPreferences preferences;
    public SharedPref(Context context){
        preferences=context.getSharedPreferences("urlprefs",Context.MODE_APPEND);
    }

    public Map<String,?> getAllPrefs() {
        return preferences.getAll();
    }

    public void putListString(String key, String url) {
        preferences.edit().putString(key, url).apply();
    }
}


