package com.goozix.githubdisplay.presentation.screen.list.item.user;

import android.databinding.ObservableField;

import com.goozix.domain.entity.User;
import com.goozix.githubdisplay.presentation.base.recycler.BaseItemViewModel;

public class UserItemViewModel extends BaseItemViewModel<User> {
    public ObservableField<String> login = new ObservableField<>("");

    public User user;
    public int position = 0;

    @Override
    public void setItem(User user, int position) {
        this.login.set(user.getLogin());
    }
}
