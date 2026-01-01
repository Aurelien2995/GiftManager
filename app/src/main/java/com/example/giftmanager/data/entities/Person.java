package com.example.giftmanager.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String birthday; // ISO yyyy-MM-dd ou vide

    public Person(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }
}
