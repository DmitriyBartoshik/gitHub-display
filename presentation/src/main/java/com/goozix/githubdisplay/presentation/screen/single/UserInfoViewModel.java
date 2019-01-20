package com.goozix.githubdisplay.presentation.screen.single;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ImageView;

import com.goozix.domain.entity.DomainModel;
import com.goozix.domain.entity.UserInfo;
import com.goozix.domain.usecase.GetUserUseCase;
import com.goozix.githubdisplay.app.App;
import com.goozix.githubdisplay.presentation.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserInfoViewModel extends BaseViewModel<UserInfoRouter, DomainModel> {
    public ObservableField<String> avatarUrl = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> company = new ObservableField<>("");
    public ObservableField<String> following = new ObservableField<>("");
    public ObservableField<String> followers = new ObservableField<>("");
    public ObservableField<String> createdAt = new ObservableField<>("");

    @Inject
    public GetUserUseCase userUseCase;

    @Override
    protected void runInject() {
        {
            App.getAppComponent().runInject(this);
        }
    }

    public UserInfoViewModel() {

    }

    public void getUser(String login) {
        userUseCase
                .getUser(login)
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                       setUserInfo(userInfo);
                        Log.d("get User", "Get user work!!!!!!!!! ");
                        Log.d("get User", "Get user work!!!!!!!!! "+ userInfo.getAvatarUrl());
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

    public void setUserInfo(UserInfo userInfo){
        avatarUrl.set(userInfo.getAvatarUrl());
        name.set(userInfo.getName());
        email.set(userInfo.getEmail());
        company.set(userInfo.getCompany());
        following.set(userInfo.getFollowing().toString());
        followers.set(userInfo.getFollowers().toString());
        createdAt.set(userInfo.getCreatedAt());
    }
}
