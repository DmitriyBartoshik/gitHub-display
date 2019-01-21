package com.goozix.githubdisplay.presentation.screen.list;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.goozix.domain.entity.DomainModel;
import com.goozix.domain.entity.User;
import com.goozix.domain.usecase.GetListUserUseCase;
import com.goozix.githubdisplay.app.App;
import com.goozix.githubdisplay.presentation.base.BaseViewModel;
import com.goozix.githubdisplay.presentation.base.recycler.ClickedItemModel;
import com.goozix.githubdisplay.presentation.screen.list.item.user.UserListAdapter;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListViewModel extends BaseViewModel<UserListRouter, DomainModel> {
    public static final int USER_PER_PAGE = 30;
    public UserListAdapter adapter = new UserListAdapter();
    int lastUserId = 0;
    int page = 0;
    boolean isAvailableRequest = true;

    @Inject
    public GetListUserUseCase listUserUseCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public UserListViewModel() {
        showProgressBar();
        getUserList();
        adapterClickObserver();
        lastViewOnAttachObserver();
    }

    public void getUserList() {

        listUserUseCase.getUserList(USER_PER_PAGE, lastUserId).subscribe(new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(List<User> users) {
                Log.d("User list", "size " + users.size());
                lastUserId = getIdLastUser(users);
                adapter.addItems(users);
                Log.d("Last User id", "Last User id " + lastUserId);
                page++;
                isAvailableRequest = true;
                Log.d("isAvailableRequest", "isAvailableRequest " + isAvailableRequest);
                Log.d("page", "page " + page);
                hideProgressBar();
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

    public void lastViewOnAttachObserver() {

        adapter.lastViewPositionObserver().subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("lastUserPosition", "integer " + integer);
                Log.d("isAvailableRequest", "isAvailableRequest " + isAvailableRequest);
                if (isAvailableRequest && integer == (USER_PER_PAGE * page-1)) {
                    getUserList();
                    isAvailableRequest = false;
                }

                Log.d("isAvailableRequest", "isAvailableRequest " + isAvailableRequest);
                hideProgressBar();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("lastUserPosition", "integer onComplete ");
            }
        });
    }

    public void adapterClickObserver() {
        adapter.observeItemClick().subscribe(new Observer<ClickedItemModel<DomainModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(ClickedItemModel<DomainModel> domainModelClickedItemModel) {
                String login = ((User) domainModelClickedItemModel.getEntity()).getLogin();
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

    public int getIdLastUser(List<User> users) {
        User lastUsers = users.get(users.size() - 1);
        return lastUsers.getId();
    }
}
