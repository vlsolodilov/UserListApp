package com.solodilov.userlistapp.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserDb {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String uid;
    public long enteredAt;

    public UserDb(String name, String uid) {
        this.name = name;
        this.uid = uid;
        this.enteredAt = System.currentTimeMillis();
    }
}