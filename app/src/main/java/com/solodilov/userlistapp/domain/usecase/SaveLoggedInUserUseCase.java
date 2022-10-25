package com.solodilov.userlistapp.domain.usecase;

import com.solodilov.userlistapp.domain.entity.User;
import com.solodilov.userlistapp.domain.repository.UserRepository;

import javax.inject.Inject;

public class SaveLoggedInUserUseCase {
    private final UserRepository repository;

    @Inject
    public SaveLoggedInUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(User user) {
        repository.saveLoggedInUser(user);
    }
}
