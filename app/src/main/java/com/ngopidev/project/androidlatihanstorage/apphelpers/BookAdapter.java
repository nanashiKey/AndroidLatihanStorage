package com.ngopidev.project.androidlatihanstorage.apphelpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngopidev.project.androidlatihanstorage.R;
import com.ngopidev.project.androidlatihanstorage.untukRoom.BookModel;

import java.util.ArrayList;

/**
 * created by Irfan Assidiq on 2020-02-02
 * email : assidiq.irfan@gmail.com
 **/
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private Context ctx;
    private ArrayList<BookModel> bookModels;

    public BookAdapter(){}
    public BookAdapter(Context ctx, ArrayList<BookModel> bookModels){
        this.ctx = ctx;
        this.bookModels = bookModels;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.booklist,
                parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookModel bookModel = bookModels.get(position);
        holder.tvNamaPenulis.setText(bookModel.bookWriter);
        holder.tvJudulBuku.setText(bookModel.bookName);
    }

    @Override
    public int getItemCount() {
        return bookModels.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaPenulis, tvJudulBuku;
        public BookViewHolder(View itemView){
            super(itemView);
            tvNamaPenulis = itemView.findViewById(R.id.tvNamaPenulis);
            tvJudulBuku = itemView.findViewById(R.id.tvJudulBuku);
        }
    }
}
