package com.solodilov.userlistapp.data.mapper;

import com.solodilov.userlistapp.data.model.ListUsersItem;
import com.solodilov.userlistapp.data.model.UserDb;
import com.solodilov.userlistapp.domain.entity.User;

import javax.inject.Inject;

public class UserMapper {
    @Inject
    public UserMapper() {
    }

    public User mapListUserItemToUser(ListUsersItem item) {
      return new User(item.getUser(), item.getUid());
    }

    public User mapUserDbToUser(UserDb userDb) {
        return new User(userDb.name, userDb.uid);
    }
}
