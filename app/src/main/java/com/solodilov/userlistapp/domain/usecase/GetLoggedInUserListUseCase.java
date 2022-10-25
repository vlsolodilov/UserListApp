package com.solodilov.userlistapp.domain.usecase;

import com.solodilov.userlistapp.domain.entity.User;
import com.solodilov.userlistapp.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetLoggedInUserListUseCase {
    private final UserRepository repository;

    @Inject
    public GetLoggedInUserListUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public Single<List<User>> execute(){
        return repository.getLoggedInUserList();
    }
}
