package com.solodilov.userlistapp.domain.usecase;

import com.solodilov.userlistapp.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoginUseCase {
    private final UserRepository repository;

    @Inject
    public LoginUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public Single<Boolean> execute(String imei, String uid, String pass){
        return repository.login(imei, uid, pass);
    }
}
