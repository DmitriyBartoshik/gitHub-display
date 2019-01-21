package com.goozix.githubdisplay.presentation.screen.single;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.goozix.githubdisplay.R;
import com.goozix.githubdisplay.databinding.ActivityUserInfoBinding;
import com.goozix.githubdisplay.presentation.base.BaseMvvmActivity;

public class UserInfoActivity extends BaseMvvmActivity<UserInfoViewModel,
        ActivityUserInfoBinding, UserInfoRouter> {
    public static final String EXTRA_USER_LOGIN = "EXTRA_USER_LOGIN";

    public static Intent getIntent(Activity activity, String login) {
        Intent intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(EXTRA_USER_LOGIN, login);
        return intent;
    }

    @Override
    protected UserInfoViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserInfoViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected UserInfoRouter provideRouter() {
        return new UserInfoRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.toolbar);
        String login = getIntent().getExtras().getString(EXTRA_USER_LOGIN);
        viewModel.getUserInfo(login);
    }
}
