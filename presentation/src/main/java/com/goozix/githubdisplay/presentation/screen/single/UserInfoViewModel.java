package com.goozix.githubdisplay.presentation.screen.single;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ImageView;

import com.goozix.domain.entity.DomainModel;
import com.goozix.domain.entity.Organization;
import com.goozix.domain.entity.UserInfo;
import com.goozix.domain.usecase.GetUserOrganizationUseCase;
import com.goozix.domain.usecase.GetUserUseCase;
import com.goozix.githubdisplay.app.App;
import com.goozix.githubdisplay.presentation.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserInfoViewModel extends BaseViewModel<UserInfoRouter, DomainModel> {
    public ObservableField<String> avatarUrl = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> organizations = new ObservableField<>("");
    public ObservableField<String> following = new ObservableField<>("");
    public ObservableField<String> followers = new ObservableField<>("");
    public ObservableField<String> createdAt = new ObservableField<>("");

    public String login;

    @Inject
    public GetUserUseCase userUseCase;

    @Inject
    public GetUserOrganizationUseCase userOrganizationUseCase;

    @Override
    protected void runInject() {
        {
            App.getAppComponent().runInject(this);
        }
    }

    public UserInfoViewModel() {

    }

    public void getUserInfo(String login) {
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
                        Log.d("get User", "Get user work!!!!!!!!! " + userInfo.getAvatarUrl());
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

    public void getUserOrganization(String login) {
        userOrganizationUseCase
                .getUserOrganization(login)
                .subscribe(new Observer<List<Organization>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Organization> organizations) {
                        Log.d("org", "organization " + organizations.size());
                        setUserOrganization(organizations);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Error get user", "organization get error !!!!!!!!!!!!! " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setUserInfo(UserInfo userInfo) {
        getUserOrganization(userInfo.getLogin());
        avatarUrl.set(userInfo.getAvatarUrl());
        name.set(userInfo.getName());
        email.set(userInfo.getEmail());
        following.set(userInfo.getFollowing().toString());
        followers.set(userInfo.getFollowers().toString());
        createdAt.set(userInfo.getCreatedAt());
    }

    public void setUserOrganization(List<Organization> organizationList) {
        if (!organizationList.isEmpty()) {

            StringBuilder orgs = new StringBuilder();
            for (Organization organization : organizationList) {
                orgs.append(organization.getLogin());
                orgs.append(" ");
            }
            organizations.set(orgs.toString());
        }
    }
}
