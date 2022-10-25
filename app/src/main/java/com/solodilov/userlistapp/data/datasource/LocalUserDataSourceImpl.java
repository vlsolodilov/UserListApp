package com.solodilov.userlistapp.data.datasource;

import com.solodilov.userlistapp.data.datasource.database.UserDao;
import com.solodilov.userlistapp.data.model.UserDb;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class LocalUserDataSourceImpl implements LocalUserDataSource {

    private final UserDao dao;

    @Inject
    public LocalUserDataSourceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void insertLoggedInUser(String name, String uid) {
        dao.insertLoggedInUser(new UserDb(name, uid));
    }

    @Override
    public Single<List<UserDb>> getLoggedInUserList() {
        return dao.getLoggedInUserList();
    }
}
