package com.solodilov.userlistapp.presentation.userlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.solodilov.userlistapp.domain.usecase.GetLoggedInUserListUseCase;
import com.solodilov.userlistapp.presentation.common.Response;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserListViewModel extends ViewModel {

    private final GetLoggedInUserListUseCase getLoggedInUserListUseCase;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Response> response = new MutableLiveData<>();

    @Inject
    public UserListViewModel(GetLoggedInUserListUseCase getLoggedInUserListUseCase) {
        this.getLoggedInUserListUseCase = getLoggedInUserListUseCase;
    }

    MutableLiveData<Response> response() {
        return response;
    }

    void getUserList() {
        disposables.add(getLoggedInUserListUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> response.setValue(Response.loading()))
                .subscribe(
                        userList -> response.setValue(Response.success(userList)),
                        throwable -> response.setValue(Response.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
