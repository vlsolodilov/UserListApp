package com.solodilov.userlistapp;

import android.app.Application;

import com.solodilov.userlistapp.di.ApplicationComponent;
import com.solodilov.userlistapp.di.DaggerApplicationComponent;

public class App extends Application {
    public ApplicationComponent appComponent = DaggerApplicationComponent.factory().create(this);
}
