package com.ngopidev.project.androidlatihanstorage.apphelpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * created by Irfan Assidiq on 2020-01-26
 * email : assidiq.irfan@gmail.com
 **/
public class PrefsHelper {
    private SharedPreferences shared;
    private Context ctx;
    private String sharedName = "STORAGELATIHAN";
    private String STATUSLOGIN = "statuslogin"; // akan bertindak sebagai key

    private static PrefsHelper instance;

    public static PrefsHelper sharedInstance(Context ctx){
        if(instance == null){
            instance = new PrefsHelper(ctx);
        }
        return instance;
    }
    private PrefsHelper(Context ctx){
        this.ctx = ctx;
        this.shared = ctx.getSharedPreferences(sharedName,
                Context.MODE_PRIVATE);
    }

    public void setStatusLogin(boolean status){
        SharedPreferences.Editor edit = shared.edit();
        edit.putBoolean(STATUSLOGIN, status);
        edit.apply();
    }

    public boolean getStatusLogin(){
        return shared.getBoolean(STATUSLOGIN, false);
    }

}
