package com.goozix.githubdisplay.presentation.screen.list.item.user;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.goozix.domain.entity.User;
import com.goozix.githubdisplay.presentation.base.recycler.BaseItemViewHolder;
import com.goozix.githubdisplay.presentation.base.recycler.BaseRecyclerViewAdapter;

public class UserListAdapter  extends BaseRecyclerViewAdapter<User, UserItemViewModel> {

    @NonNull
    @Override
    public BaseItemViewHolder<User, UserItemViewModel, ?> onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return UserItemViewHolder.create(parent, new UserItemViewModel());
    }
}
