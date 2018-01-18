package com.example.android.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zifanfeng on 12/22/17.
 */

public class PrefManager{
    Context context;
    PrefManager(Context context){
        this.context = context;

    }

    public void saveLoginDetail(String email, String pwd){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Pwd", pwd);
        editor.commit();
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }

    public boolean isUserLogOut(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email","").isEmpty();
        boolean isPwdEmpty = sharedPreferences.getString("Pwd", "").isEmpty();
        return isEmailEmpty||isPwdEmpty;
    }
}
