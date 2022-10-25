package com.solodilov.userlistapp.data.datasource.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.solodilov.userlistapp.data.model.UserDb;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDao {
    @Insert
    void insertLoggedInUser(UserDb userDb);

    @Query("SELECT * FROM userdb ORDER BY enteredAt DESC")
    Single<List<UserDb>> getLoggedInUserList();
}
