package com.solodilov.userlistapp.data.datasource.network;

import com.solodilov.userlistapp.data.model.AuthResponse;
import com.solodilov.userlistapp.data.model.UserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SitecApi {

    @GET("{imei}/form/users")
    Single<UserResponse> getUserList(@Path(value = "imei") String imei);

    @GET("{imei}/authentication?copyFromDevice=false")
    Single<AuthResponse> login(
            @Path(value = "imei") String imei,
            @Query("uid") String uid,
            @Query("pass") String pass
    );

}
