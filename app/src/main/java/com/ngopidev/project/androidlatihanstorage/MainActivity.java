package com.ngopidev.project.androidlatihanstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.ngopidev.project.androidlatihanstorage.apphelpers.PrefsHelper;

public class MainActivity extends AppCompatActivity {

    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = findViewById(R.id.tvTest);
        String nama = PrefsHelper.sharedInstance(this).getNama();

        tvTest.setText(nama);

    }

    //menambahkan menu kedalam aplikasi main kita
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulogout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            PrefsHelper.sharedInstance(this).setStatusLogin(false);
            startActivity(new Intent(MainActivity.this,
                    LoginActivity.class));
            finish();
        }
        return true;
    }
}
