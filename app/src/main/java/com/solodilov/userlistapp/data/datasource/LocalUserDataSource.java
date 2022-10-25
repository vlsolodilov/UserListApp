package com.solodilov.userlistapp.data.datasource;

import com.solodilov.userlistapp.data.model.UserDb;

import java.util.List;

import io.reactivex.Single;

public interface LocalUserDataSource {

    void insertLoggedInUser(String name, String uid);

    Single<List<UserDb>> getLoggedInUserList();
}
