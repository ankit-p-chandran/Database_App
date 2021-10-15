package com.example.databaseapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.databaseapp.database.dao.StudentDao;
import com.example.databaseapp.database.entity.Student;

@Database(entities = {Student.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();


}
