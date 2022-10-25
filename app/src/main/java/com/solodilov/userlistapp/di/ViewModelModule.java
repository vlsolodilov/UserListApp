package com.solodilov.userlistapp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.solodilov.userlistapp.presentation.ViewModelFactory;
import com.solodilov.userlistapp.presentation.login.LoginViewModel;
import com.solodilov.userlistapp.presentation.userlist.UserListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    ViewModel bindsLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel.class)
    ViewModel bindsUserListViewModel(UserListViewModel viewModel);

    @Binds
    ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

}
