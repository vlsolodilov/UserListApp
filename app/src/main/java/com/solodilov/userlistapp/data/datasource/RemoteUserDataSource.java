package com.solodilov.userlistapp.data.datasource;

import com.solodilov.userlistapp.data.model.AuthResponse;
import com.solodilov.userlistapp.data.model.UserResponse;

import java.util.List;

import io.reactivex.Single;

public interface RemoteUserDataSource {

    Single<UserResponse> getUserList(String imei);

    Single<AuthResponse> login(String imei, String uid, String pass);
}
