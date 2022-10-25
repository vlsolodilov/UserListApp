package com.solodilov.userlistapp.domain.entity;

public class User {

    private final String name;
    private final String uid;

    public User(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return name;
    }
}
