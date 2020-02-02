package com.ngopidev.project.androidlatihanstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ngopidev.project.androidlatihanstorage.apphelpers.BookAdapter;
import com.ngopidev.project.androidlatihanstorage.apphelpers.PrefsHelper;
import com.ngopidev.project.androidlatihanstorage.untukRoom.BookModel;
import com.ngopidev.project.androidlatihanstorage.untukRoom.DatabaseExec;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView tvTest;
    DatabaseExec database;
    RecyclerView rcView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<BookModel> bookModels;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = findViewById(R.id.tvTest);
//        String nama = PrefsHelper.sharedInstance(this).getNama();
//        tvTest.setText(nama);
        rcView = findViewById(R.id.rcView);
        bookModels = new ArrayList<>();
        database = Room.databaseBuilder(this, DatabaseExec.class,
                "dbbuku").allowMainThreadQueries().build();

        layoutManager = new LinearLayoutManager(this);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        rcView.addItemDecoration(divider);

        bookModels.addAll(Arrays.asList(database.bookDao().selectAllData()));

        if(bookModels.isEmpty()){
            rcView.setVisibility(View.GONE);
            tvTest.setVisibility(View.VISIBLE);
        }else{
            rcView.setVisibility(View.VISIBLE);
            tvTest.setVisibility(View.GONE);
        }

        bookAdapter = new BookAdapter(this, bookModels);
        rcView.setAdapter(bookAdapter);
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
        }else if(item.getItemId() == R.id.tambahdata){
            startActivity(new Intent(MainActivity.this,
                    AddActivity.class));
            finish();
        }else {
            Toast.makeText(this, "tidak ada yang dipilih",
                    Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
