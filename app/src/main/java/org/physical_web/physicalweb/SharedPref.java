package org.physical_web.physicalweb;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;


public class SharedPref {

    public SharedPreferences preferences;


    public SharedPref(Context context){
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
    }


    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key,""),"++")));
    }


    public void putListString(String key, ArrayList<String> stringList) {
    String[] myStringList = stringList.toArray(new String[stringList.size()]);
    preferences.edit().putString(key, TextUtils.join("++", myStringList)).apply();
        }

    }


