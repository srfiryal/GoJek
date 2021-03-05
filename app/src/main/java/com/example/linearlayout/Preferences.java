package com.example.linearlayout;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    String PREFERENCE = "pref";
    SharedPreferences.Editor editor;

    public boolean getStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getBoolean("status", false);
    }

    public void setStatus(Context context, boolean login) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean("status", login);
        editor.apply();
    }

    public String getEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString("email", "user");
    }

    public void setEmail(Context context, String login) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("email", login);
        editor.apply();
    }
}