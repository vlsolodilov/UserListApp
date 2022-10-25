package com.solodilov.userlistapp.presentation.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.solodilov.userlistapp.domain.entity.User;
import com.solodilov.userlistapp.domain.usecase.GetUserListUseCase;
import com.solodilov.userlistapp.domain.usecase.LoginUseCase;
import com.solodilov.userlistapp.domain.usecase.SaveLoggedInUserUseCase;
import com.solodilov.userlistapp.presentation.common.Response;
import com.solodilov.userlistapp.presentation.common.SingleLiveEvent;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private final GetUserListUseCase getUserListUseCase;
    private final SaveLoggedInUserUseCase saveLoggedInUserUseCase;
    private final LoginUseCase loginUseCase;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Response> response = new MutableLiveData<>();

    private final SingleLiveEvent<Void> _loginSuccessEvent = new SingleLiveEvent<>();
    public LiveData<Void> loginSuccessEvent = _loginSuccessEvent;

    private final SingleLiveEvent<Void> _loginErrorEvent = new SingleLiveEvent<>();
    public LiveData<Void> loginErrorEvent = _loginErrorEvent;

    private final MutableLiveData<Boolean> loginAvailable = new MutableLiveData<>(true);

    @Inject
    public LoginViewModel(GetUserListUseCase getUserListUseCase, SaveLoggedInUserUseCase saveLoggedInUserUseCase, LoginUseCase loginUseCase) {
        this.getUserListUseCase = getUserListUseCase;
        this.saveLoggedInUserUseCase = saveLoggedInUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    MutableLiveData<Response> response() {
        return response;
    }

    MutableLiveData<Boolean> loginAvailable() {
        return loginAvailable;
    }

    void getUserList(String imei) {
        disposables.add(getUserListUseCase.execute(imei)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> response.postValue(Response.loading()))
                .subscribe(
                        userList -> response.postValue(Response.success(userList)),
                        throwable -> response.postValue(Response.error(throwable))
                ));
    }

    void login(String imei, String name, String uid, String pass) {

        if (name.isEmpty() || pass.trim().isEmpty()) {
            _loginErrorEvent.call();
            return;
        }

        disposables.add(loginUseCase.execute(imei, uid, pass)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> loginAvailable.postValue(false))
                .subscribe(
                        isLoginSuccess -> {
                            if (isLoginSuccess) {
                                saveLoggedInUserUseCase.execute(new User(name, uid));
                                _loginSuccessEvent.postValue(null);
                            } else {
                                _loginErrorEvent.postValue(null);
                            }
                            loginAvailable.postValue(true);
                        },
                        throwable -> {
                            response.postValue(Response.error(throwable));
                            loginAvailable.postValue(true);
                        }
                ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
