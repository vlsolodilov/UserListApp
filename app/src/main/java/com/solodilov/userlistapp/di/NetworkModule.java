package com.solodilov.userlistapp.di;

import com.solodilov.userlistapp.data.datasource.network.CustomOkHttpClient;
import com.solodilov.userlistapp.data.datasource.network.SitecApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://dev.sitec24.ru/UKA_TRADE/hs/MobileClient/";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return CustomOkHttpClient.getUnsafeOkHttpClient();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    SitecApi provideApiService(Retrofit retrofit) {
        return retrofit.create(SitecApi.class);
    }

}
