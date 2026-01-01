package com.example.giftmanager.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity
public class GiftIdea {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int personId;
    public String title;
    @ColumnInfo(name = "description")
    public String description;
    public double price;
    public String link;
    public String occasion; // "Anniversaire" ou "Noël"
    public boolean offered;  // vrai si offert

    public GiftIdea(int personId, String title, String description, double price, String link, String occasion, boolean offered) {
        this.personId = personId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.link = link;
        this.occasion = occasion;
        this.offered = offered;
    }
}
