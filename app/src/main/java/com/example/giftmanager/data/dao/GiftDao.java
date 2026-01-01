package com.example.giftmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.giftmanager.data.entities.GiftIdea;

import java.util.List;

@Dao
public interface GiftDao {
    @Query("SELECT * FROM GiftIdea WHERE personId = :personId ORDER BY offered, title")
    List<GiftIdea> getForPerson(int personId);

    @Insert
    long insert(GiftIdea gift);

    @Update
    void update(GiftIdea gift);

    @Delete
    void delete(GiftIdea gift);

    @Query("SELECT * FROM GiftIdea WHERE id = :id LIMIT 1")
    GiftIdea getById(int id);
}
