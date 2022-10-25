package com.solodilov.userlistapp.domain.repository;

import com.solodilov.userlistapp.domain.entity.User;

import java.util.List;

import io.reactivex.Single;

public interface UserRepository {
    Single<List<User>> getUserList(String imei);

    Single<Boolean> login(String imei, String uid, String pass);

    void saveLoggedInUser(User user);

    Single<List<User>> getLoggedInUserList();
}
