package com.ngopidev.project.androidlatihanstorage.untukRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * created by Irfan Assidiq on 2020-02-02
 * email : assidiq.irfan@gmail.com
 **/

@Database(entities = {BookModel.class}, version = 1)
public abstract class DatabaseExec extends RoomDatabase {
    public abstract BookDao bookDao();
}
