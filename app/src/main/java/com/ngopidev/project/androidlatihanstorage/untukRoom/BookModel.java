package com.ngopidev.project.androidlatihanstorage.untukRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * created by Irfan Assidiq on 2020-02-02
 * email : assidiq.irfan@gmail.com
 **/

//untuk menyimpan nama table
@Entity(tableName = "booktable")
public class BookModel  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int bookId;

    @ColumnInfo(name = "nama_buku")
    public String bookName;

    @ColumnInfo(name = "nama_penulis")
    public String bookWriter;

    @ColumnInfo(name = "description")
    public String describeBook;
}
