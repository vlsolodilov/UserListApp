package com.solodilov.userlistapp.di;

import android.app.Application;

import androidx.room.Room;

import com.solodilov.userlistapp.data.datasource.LocalUserDataSource;
import com.solodilov.userlistapp.data.datasource.LocalUserDataSourceImpl;
import com.solodilov.userlistapp.data.datasource.RemoteUserDataSource;
import com.solodilov.userlistapp.data.datasource.RemoteUserDataSourceImpl;
import com.solodilov.userlistapp.data.datasource.database.UserDao;
import com.solodilov.userlistapp.data.datasource.database.UserDatabase;
import com.solodilov.userlistapp.data.repository.UserRepositoryImpl;
import com.solodilov.userlistapp.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public interface DataModule {

    @Singleton
    @Binds
    LocalUserDataSource bindLocalUserDataSource(LocalUserDataSourceImpl impl);

    @Singleton
    @Binds
    RemoteUserDataSource bindRemoteUserDataSource(RemoteUserDataSourceImpl impl);

    @Singleton
    @Binds
    UserRepository bindUserRepository(UserRepositoryImpl impl);


    @Provides
    @Singleton
    static UserDatabase provideUserDatabase(Application application) {
        return UserDatabase.getDatabase(application);
    }

    @Singleton
    @Provides
    static UserDao provideUserDao(UserDatabase userDatabase) {
        return userDatabase.userDao();
    }

}
