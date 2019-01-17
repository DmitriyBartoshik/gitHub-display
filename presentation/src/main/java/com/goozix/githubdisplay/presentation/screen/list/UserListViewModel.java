package com.goozix.githubdisplay.presentation.screen.list;

import android.util.Log;

import com.goozix.domain.entity.DomainModel;
import com.goozix.domain.entity.User;
import com.goozix.domain.usecase.GetListUserUseCase;
import com.goozix.githubdisplay.app.App;
import com.goozix.githubdisplay.presentation.base.BaseViewModel;
import com.goozix.githubdisplay.presentation.base.recycler.ClickedItemModel;
import com.goozix.githubdisplay.presentation.screen.list.item.user.UserListAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListViewModel extends BaseViewModel<UserListRouter, DomainModel> {
    public UserListAdapter adapter = new UserListAdapter();

    @Inject
    public GetListUserUseCase listUserUseCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public UserListViewModel() {
        getUserList();
        adapterClickObserver();
    }

    public void getUserList() {
        listUserUseCase.getUserList().subscribe(new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<User> users) {
                Log.d("User list", "size " + users.size());
                adapter.setItems(users);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Error get userList", "Error get userList " + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void adapterClickObserver() {
        adapter.observeItemClick().subscribe(new Observer<ClickedItemModel<DomainModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ClickedItemModel<DomainModel> domainModelClickedItemModel) {
                String login=((User) domainModelClickedItemModel.getEntity()).getLogin();
                router.showSingleUser(login);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
