package com.goozix.githubdisplay.presentation.screen.list.item.user;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.goozix.domain.entity.User;
import com.goozix.githubdisplay.databinding.ItemUserBinding;
import com.goozix.githubdisplay.presentation.base.recycler.BaseItemViewHolder;

public class UserItemViewHolder extends BaseItemViewHolder<User, UserItemViewModel, ItemUserBinding> {
        public static UserItemViewHolder userItemViewHolder;

        public UserItemViewHolder(UserItemViewModel viewModel, ItemUserBinding binding) {
            super(viewModel, binding);
        }

        public static UserItemViewHolder create(ViewGroup parent, UserItemViewModel viewModel) {
            ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false);
            return new UserItemViewHolder(viewModel, binding);
        }

        @Override
        public UserItemViewModel getViewModel() {
            return super.getViewModel();
        }

        public static UserItemViewHolder getViewHolder() {
            return userItemViewHolder;
        }
    }

