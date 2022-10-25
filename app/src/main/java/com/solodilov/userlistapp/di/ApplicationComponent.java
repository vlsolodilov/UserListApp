package com.solodilov.userlistapp.di;

import android.app.Application;

import com.solodilov.userlistapp.presentation.login.LoginFragment;
import com.solodilov.userlistapp.presentation.userlist.UserListFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {
        DataModule.class,
        NetworkModule.class,
        ViewModelModule.class,
})
@Singleton
public interface ApplicationComponent {

    void inject(LoginFragment fragment);
    void inject(UserListFragment fragment);

    @Component.Factory
    interface Factory {
        ApplicationComponent create(@BindsInstance Application application);
    }
}
