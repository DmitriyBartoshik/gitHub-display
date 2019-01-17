package com.goozix.githubdisplay.presentation.screen.single;

import android.databinding.ObservableField;
import android.util.Log;

import com.goozix.domain.entity.DomainModel;
import com.goozix.domain.entity.User;
import com.goozix.domain.usecase.GetUserUseCase;
import com.goozix.githubdisplay.app.App;
import com.goozix.githubdisplay.presentation.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserViewModel extends BaseViewModel<UserRouter, DomainModel> {
    public ObservableField<String> login = new ObservableField<>("");
    public ObservableField<String> id = new ObservableField<>("");
    public ObservableField<String> following = new ObservableField<>("");
    public ObservableField<String> followers = new ObservableField<>("");

    @Inject
    public GetUserUseCase listUserUseCase;

    @Override
    protected void runInject() {
        {
            App.getAppComponent().runInject(this);
        }
    }

    public UserViewModel() {

    }

    public void getUser(String userEmail) {
        listUserUseCase
                .getUser(userEmail)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d("get User", "Get user work!!!!!!!!! ");
                        login.set(user.getLogin());
                        id.set(user.getId().toString());
                        following.set(user.getFollowing().toString());
                        followers.set(user.getFollowers().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Error get user", "Error get user " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
