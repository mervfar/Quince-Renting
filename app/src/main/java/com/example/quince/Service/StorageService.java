package com.example.quince.Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;


public class StorageService {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void storeToken(String token,Context context){
        preferences=PreferenceManager.getDefaultSharedPreferences(context);
        editor=preferences.edit();
        editor.putString("token",token);
        editor.commit();
    }
    public String gettoken(Context context){
        preferences=PreferenceManager.getDefaultSharedPreferences(context);
        System.out.println("DEPOOOOOO"+preferences.getString("token",""));
        return preferences.getString("token","");
    }
    public void storeCreds(String username,String password,Context context){
        preferences=PreferenceManager.getDefaultSharedPreferences(context);
        editor=preferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }
    public HashMap<String,String> getCreds(Context context){
        HashMap<String, String> creds = new HashMap<>();
        preferences=PreferenceManager.getDefaultSharedPreferences(context);
        creds.put("username",preferences.getString("username",""));
        creds.put("password",preferences.getString("password",""));
        return creds;
    }
    public void setRememberOption(Boolean option,Context context){
        preferences=PreferenceManager.getDefaultSharedPreferences(context);
        editor=preferences.edit();
        editor.putBoolean("rememberme",option);
        editor.commit();
    }
    public Boolean getRememberOption(Context context){
        preferences=PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("rememberme",false);
    }
}

