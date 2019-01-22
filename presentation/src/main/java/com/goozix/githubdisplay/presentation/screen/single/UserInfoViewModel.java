package com.goozix.githubdisplay.presentation.screen.single;

import android.databinding.ObservableField;

import com.goozix.domain.entity.DomainModel;
import com.goozix.domain.entity.Organization;
import com.goozix.domain.entity.UserInfo;
import com.goozix.domain.usecase.GetUserOrganizationUseCase;
import com.goozix.domain.usecase.GetUserUseCase;
import com.goozix.githubdisplay.app.App;
import com.goozix.githubdisplay.presentation.base.BaseViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        showProgressBar();
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
                    }

                    @Override
                    public void onError(Throwable e) {
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
                        setUserOrganization(organizations);
                    }

                    @Override
                    public void onError(Throwable e) {
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
        createdAt.set(convertDate(userInfo.getCreatedAt()));

        if (email.get() == null)
            hideEmailContainer();
    }

    public void setUserOrganization(List<Organization> organizationList) {
        if (!organizationList.isEmpty()) {

            StringBuilder orgs = new StringBuilder();
            for (Organization organization : organizationList) {
                orgs.append(organization.getLogin());
                orgs.append(" ");
            }
            organizations.set(orgs.toString());
        } else hideOrganizationContainer();
        hideProgressBar();
    }

    public String convertDate(String stringDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(stringDate);
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void onClickBack() {
        router.goBack();
    }

}
