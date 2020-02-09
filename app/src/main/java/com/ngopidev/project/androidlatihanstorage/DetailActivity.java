package com.ngopidev.project.androidlatihanstorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ngopidev.project.androidlatihanstorage.untukRoom.BookModel;


/**
 * created by Irfan Assidiq on 2020-02-09
 * email : assidiq.irfan@gmail.com
 **/
public class DetailActivity extends AppCompatActivity {

    TextView tvJudulBuku, tvNamaPenulis, tvDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);
        tvJudulBuku = findViewById(R.id.tvJudulBuku);
        tvNamaPenulis = findViewById(R.id.tvNamaPenulis);
        tvDescription = findViewById(R.id.tvDescription);

        BookModel bookModel = (BookModel) getIntent().getSerializableExtra("data");
        if(bookModel != null){
            tvJudulBuku.setText(bookModel.bookName);
            tvNamaPenulis.setText(bookModel.bookWriter);
            tvDescription.setText(bookModel.describeBook);
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DetailActivity.class);
    }
}
