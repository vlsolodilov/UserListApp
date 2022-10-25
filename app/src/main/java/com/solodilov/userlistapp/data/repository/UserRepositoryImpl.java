package com.solodilov.userlistapp.data.repository;

import com.solodilov.userlistapp.data.datasource.LocalUserDataSource;
import com.solodilov.userlistapp.data.datasource.RemoteUserDataSource;
import com.solodilov.userlistapp.data.mapper.UserMapper;
import com.solodilov.userlistapp.domain.entity.User;
import com.solodilov.userlistapp.domain.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepositoryImpl implements UserRepository {

    private final RemoteUserDataSource remoteDataSource;
    private final LocalUserDataSource localDataSource;
    private final UserMapper mapper;

    public static final int SUCCESS_CODE = 1022;

    @Inject
    public UserRepositoryImpl(
            RemoteUserDataSource remoteDataSource,
            LocalUserDataSource localDataSource,
            UserMapper mapper
    ) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.mapper = mapper;
    }

    @Override
    public Single<List<User>> getUserList(String imei) {
        return remoteDataSource.getUserList(imei)
                .map(userResponse -> userResponse.getUsers().getListUsers().stream()
                        .map(mapper::mapListUserItemToUser)
                        .collect(Collectors.toList()));
    }

    @Override
    public Single<Boolean> login(String imei, String uid, String pass) {
        return remoteDataSource.login(imei, uid, pass)
                .map(authResponse -> authResponse.getCode() == SUCCESS_CODE);
    }

    @Override
    public void saveLoggedInUser(User user) {
        localDataSource.insertLoggedInUser(user.getName(), user.getUid());
    }

    @Override
    public Single<List<User>> getLoggedInUserList() {
        return localDataSource.getLoggedInUserList()
                .map(userDbs ->
                        userDbs.stream().map(mapper::mapUserDbToUser).collect(Collectors.toList()));
    }
}
