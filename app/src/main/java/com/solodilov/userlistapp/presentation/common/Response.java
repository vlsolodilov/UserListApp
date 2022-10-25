package com.solodilov.userlistapp.presentation.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.solodilov.userlistapp.domain.entity.User;

import java.util.List;

public class Response {
    public final Status status;

    @Nullable
    public final List<User> data;

    @Nullable
    public final Throwable error;

    private Response(Status status, @Nullable List<User> data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Response loading() {
        return new Response(Status.LOADING, null, null);
    }

    public static Response success(@NonNull List<User> data) {
        return new Response(Status.SUCCESS, data, null);
    }

    public static Response error(@NonNull Throwable error) {
        return new Response(Status.ERROR, null, error);
    }
}
