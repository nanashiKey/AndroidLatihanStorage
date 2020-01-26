package com.ngopidev.project.androidlatihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngopidev.project.androidlatihanstorage.apphelpers.PrefsHelper;

public class LoginActivity extends AppCompatActivity {

    EditText etname, etPassword;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boolean cekstatuslogin = PrefsHelper.sharedInstance(this).getStatusLogin();
        if(cekstatuslogin){ // bentuk seperti ini saat nilainya akan bersifat true
            startActivity(new Intent(LoginActivity.this,
                    MainActivity.class));
            finish();
        }

        etname = findViewById(R.id.etname);
        etPassword = findViewById(R.id.etPassword);
        btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etname.getText().toString();
                String pass = etPassword.getText().toString();
                if(nama.equals("irfan") && pass.equals("irfan123")){
                    startActivity(new Intent(LoginActivity.this,
                            MainActivity.class));
                    //menyimpan status login menjadi true
                    PrefsHelper.sharedInstance(LoginActivity.this)
                            .setStatusLogin(true);
                    finish();
                }else if(nama.isEmpty() || pass.isEmpty() || nama.equals("")
                        || pass.equals("")){
                    Toast.makeText(LoginActivity.this,
                            "isi form yang kosong!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "nama atau password salah",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
