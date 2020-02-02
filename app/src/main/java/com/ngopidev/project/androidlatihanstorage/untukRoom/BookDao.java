package com.ngopidev.project.androidlatihanstorage.untukRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * created by Irfan Assidiq on 2020-02-02
 * email : assidiq.irfan@gmail.com
 **/

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(BookModel bookmodel);

    @Query("select * from booktable")
    BookModel[] selectAllData();
}
