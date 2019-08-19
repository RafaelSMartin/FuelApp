package com.rsmartin.fuelapp.presentation.internal.android;

import android.content.Context;
import android.content.SharedPreferences;

import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.IExtras;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SharedPref {

    private static SharedPref instance;
    private SharedPreferences prefs;

    public static synchronized SharedPref getInstance(){
        if (instance == null){
            instance = new SharedPref();
        }
        return instance;
    }

    private SharedPref(){
        prefs = App.getInstance()
                .getSharedPreferences(IExtras.PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void saveStringPreferences(String key, String value){
        prefs.edit().putString(key, value).apply();
    }

    public String getStringPreferences(String key){
        return prefs.getString(key, "");
    }

    public void saveBooleanPreferences(String key, boolean value){
        prefs.edit().putBoolean(key, value).apply();
    }

    public boolean getBooleanPreferences(String key){
        return prefs.getBoolean(key, false);
    }

    public void saveIntPreferences(String key, int value){
        prefs.edit().putInt(key, value).apply();
    }

    public int getIntPreferences(String key){
        return prefs.getInt(key, -1);
    }

    public void saveLongPreferences(String key, long value){
        prefs.edit().putLong(key, value).apply();
    }

    public long getLongPreferences(String key){
        return prefs.getLong(key, -1);
    }

    public void saveFloatPreferences(String key, float value){
        prefs.edit().putFloat(key, value).apply();
    }

    public float getFloatPreferences(String key){
        return prefs.getFloat(key, -1f);
    }

    /**
     * Sample
     * Set<String> defValues = new HashSet<String>();
     *
     * defValues.add("string 1");
     * defValues.add("string 2");
     * defValues.add("string 3");
     *
     * Sample
     * SharedPreferences ss = getSharedPreferences(key, 0);
     * Set<String> hs = ss.getStringSet(key, new HashSet<String>());
     * hs.add(String.valueOf(hs.size()+1));
     * Editor edit = ss.edit();
     * edit.clear();
     * edit.putStringSet(key, hs);
     * edit.commit();
     *
     * @param key
     * @param stringSet
     */
    public void saveSetStringPreferences(String key, Set<String> stringSet){
        prefs.edit().putStringSet(key, stringSet).apply();
    }

    public Set<String> getSetStringPreferences(String key){
        return prefs.getStringSet(key, new HashSet<String>());
    }

    public Map<String, ?> getAllPreferences(){
        return prefs.getAll();
    }

}
