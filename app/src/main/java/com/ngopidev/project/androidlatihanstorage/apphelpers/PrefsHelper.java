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
    private String KEYNAMA = "nama";

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

    //untuk menyimpan nilai baru dari status login
    public void setStatusLogin(boolean status){
        SharedPreferences.Editor edit = shared.edit();
        edit.putBoolean(STATUSLOGIN, status);
        edit.apply();
    }

    //untuk mengambil data status login
    public boolean getStatusLogin(){
        return shared.getBoolean(STATUSLOGIN, false);
    }

    //untuk menyimpan nama
    public void setNama(String nama){
        SharedPreferences.Editor edit = shared.edit();
        edit.putString(KEYNAMA, nama);
        edit.apply();
    }

    //untuk mengambil nama
    public String getNama(){
        return shared.getString(KEYNAMA, "belum ada nama");
    }

}
