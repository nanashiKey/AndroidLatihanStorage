package com.ngopidev.project.androidlatihanstorage.apphelpers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.ngopidev.project.androidlatihanstorage.AddActivity;
import com.ngopidev.project.androidlatihanstorage.DetailActivity;
import com.ngopidev.project.androidlatihanstorage.R;
import com.ngopidev.project.androidlatihanstorage.untukRoom.BookModel;
import com.ngopidev.project.androidlatihanstorage.untukRoom.DatabaseExec;

import java.util.ArrayList;

/**
 * created by Irfan Assidiq on 2020-02-02
 * email : assidiq.irfan@gmail.com
 **/
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private Context ctx;
    private ArrayList<BookModel> bookModels;
    private DatabaseExec database;

    public BookAdapter(){}
    public BookAdapter(Context ctx, ArrayList<BookModel> bookModels){
        this.ctx = ctx;
        this.bookModels = bookModels;
        database = Room.databaseBuilder(ctx.getApplicationContext(), DatabaseExec.class, "dbbuku").allowMainThreadQueries().build();
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.booklist,
                parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, final int position) {
        BookModel bookModel = bookModels.get(position);
        holder.tvNamaPenulis.setText(bookModel.bookWriter);
        holder.tvJudulBuku.setText(bookModel.bookName);

        holder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookModel bModel = database.bookDao().detailbyId(bookModels.get(position).bookId);
                ctx.startActivity(DetailActivity.getActIntent((Activity) ctx).putExtra("data", bModel));
            }
        });

        //memberikan fungsi onlongclick ke view Linear Layout
        holder.llView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                Toast.makeText(ctx, "Onlongclick berhasil", Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(ctx);
                dialog.setContentView(R.layout.dialog_detail);
                dialog.setCancelable(true);
                dialog.show();
                final Button btn_edit = dialog.findViewById(R.id.btn_edit);
                final Button btn_delete = dialog.findViewById(R.id.btn_delete);
                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editBook(position);
                        dialog.dismiss();
                    }
                });

                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteBook(position);
                        dialog.dismiss();
                    }
                });

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookModels.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaPenulis, tvJudulBuku;
        LinearLayout llView;
        public BookViewHolder(View itemView){
            super(itemView);
            tvNamaPenulis = itemView.findViewById(R.id.tvNamaPenulis);
            tvJudulBuku = itemView.findViewById(R.id.tvJudulBuku);
            llView = itemView.findViewById(R.id.llView);
        }
    }

    public void editBook(int position){
        ctx.startActivity(AddActivity.getActIntent((Activity) ctx).putExtra("data", bookModels.get(position) ));
    }

    public void deleteBook(int position){
        database.bookDao().deleteBook(bookModels.get(position));
        bookModels.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, bookModels.size());
        Toast.makeText(ctx, "data ke-"+(position+1)+" telah dihapus", Toast.LENGTH_SHORT).show();
    }
}
