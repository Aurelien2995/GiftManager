package com.example.giftmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.giftmanager.data.entities.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM Person ORDER BY name")
    List<Person> getAll();

    @Insert
    long insert(Person person);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("SELECT * FROM Person WHERE id = :id")
    Person getById(int id);
}
