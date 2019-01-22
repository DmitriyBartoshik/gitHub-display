package com.goozix.githubdisplay.presentation.screen.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.goozix.githubdisplay.R;
import com.goozix.githubdisplay.databinding.ActivityUserListBinding;
import com.goozix.githubdisplay.presentation.base.BaseMvvmActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

public class UserListActivity extends BaseMvvmActivity<UserListViewModel,
        ActivityUserListBinding, UserListRouter> {

    @Override
    protected UserListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected UserListRouter provideRouter() {
        return new UserListRouter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.toolbar);

        binding.userList.setLayoutManager(new LinearLayoutManager(this));
        binding.userList.setHasFixedSize(true);
        binding.userList.setAdapter(viewModel.adapter);

/**
 *  Standard google container implementation supports only pull-up refresh
 *  This container supports pull up refresh and pull down refresh
 */
        binding.swipeLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                viewModel.getUserList();
                binding.swipeLayout.setRefreshing(false);
            }
        });
    }
}
