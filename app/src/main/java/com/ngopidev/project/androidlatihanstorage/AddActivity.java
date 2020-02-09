package com.ngopidev.project.androidlatihanstorage;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.ngopidev.project.androidlatihanstorage.untukRoom.BookModel;
import com.ngopidev.project.androidlatihanstorage.untukRoom.DatabaseExec;

/**
 * created by Irfan Assidiq on 2020-02-02
 * email : assidiq.irfan@gmail.com
 **/
public class AddActivity extends AppCompatActivity {
    DatabaseExec database;
    EditText etNamaPenulis, etJudulBuku, etDesc;
    Button btnClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_act);

        database = Room.databaseBuilder(this, DatabaseExec.class,
                "dbbuku").build();

        etJudulBuku = findViewById(R.id.judulbuku);
        etNamaPenulis = findViewById(R.id.penulisbuku);
        etDesc = findViewById(R.id.desc);
        btnClick = findViewById(R.id.btnClick);

        final BookModel bModel = (BookModel) getIntent().getSerializableExtra("data");

        if(bModel != null){
            etJudulBuku.setText(bModel.bookName);
            etNamaPenulis.setText(bModel.bookWriter);
            etDesc.setText(bModel.describeBook);
            btnClick.setText("Update Data");
            btnClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bModel.bookName = etJudulBuku.getText().toString();
                    bModel.bookWriter = etNamaPenulis.getText().toString();
                    bModel.describeBook = etDesc.getText().toString();

                    updateBook(bModel);
                }
            });
        }else{
            btnClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BookModel bookModel= new BookModel();
                    String nama = etNamaPenulis.getText().toString();
                    String judul = etJudulBuku.getText().toString();
                    String desc = etDesc.getText().toString();
                    if(nama.isEmpty() || judul.isEmpty() || desc.isEmpty()){
                        Toast.makeText(AddActivity.this,
                                "Silahkan isi setiap kolom",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        bookModel.bookName = judul;
                        bookModel.bookWriter = nama;
                        bookModel.describeBook = desc;
                        inserData(bookModel);
                        startActivity(new Intent(AddActivity.this,
                                MainActivity.class));
                        finish();
                    }
                }
            });
        }

    }

    private void inserData(final BookModel bookModel){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.bookDao().insertData(bookModel);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(AddActivity.this,
                        "Data Berhasi Ditambahkan", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void updateBook(final BookModel bookModel){
        new AsyncTask<Void, Void, Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long updatedata = database.bookDao().updateBook(bookModel);
                return updatedata;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toast.makeText(AddActivity.this, "updated data ke-"+aLong, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddActivity.this, MainActivity.class));
                finish();
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, AddActivity.class);
    }
}
