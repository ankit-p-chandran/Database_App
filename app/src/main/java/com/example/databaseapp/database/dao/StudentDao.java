package com.example.databaseapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.databaseapp.database.entity.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Query("SELECT * FROM Student WHERE firstName LIKE '%' || :name || '%'")
    List<Student> getByName(String name);

    @Query("SELECT * FROM Student WHERE studentId = :studentId")
    Student getById(int studentId);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);
}
