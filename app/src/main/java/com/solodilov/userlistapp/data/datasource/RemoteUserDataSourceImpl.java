package com.solodilov.userlistapp.data.datasource;

import com.solodilov.userlistapp.data.datasource.network.SitecApi;
import com.solodilov.userlistapp.data.model.AuthResponse;
import com.solodilov.userlistapp.data.model.UserResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RemoteUserDataSourceImpl implements RemoteUserDataSource {

    private final SitecApi api;

    @Inject
    public RemoteUserDataSourceImpl(SitecApi api) {
        this.api = api;
    }

    @Override
    public Single<UserResponse> getUserList(String imei) {
        return api.getUserList(imei);
    }

    @Override
    public Single<AuthResponse> login(String imei, String uid, String pass) {
        return api.login(imei, uid, pass);
    }
}
