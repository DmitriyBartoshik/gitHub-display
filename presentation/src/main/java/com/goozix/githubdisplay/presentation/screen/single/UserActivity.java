package com.goozix.githubdisplay.presentation.screen.single;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.goozix.githubdisplay.R;
import com.goozix.githubdisplay.databinding.ActivityUserBinding;
import com.goozix.githubdisplay.presentation.base.BaseMvvmActivity;

import static com.goozix.githubdisplay.presentation.util.Extras.EXTRA_USER_LOGIN;

public class UserActivity extends BaseMvvmActivity<UserViewModel,
        ActivityUserBinding, UserRouter> {

    public static Intent getIntent(Activity activity, String email) {
        Intent intent = new Intent(activity, UserActivity.class);
        intent.putExtra(EXTRA_USER_LOGIN, email);
        return intent;
    }

    @Override
    protected UserViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected UserRouter provideRouter() {
        return new UserRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userEmail = getIntent().getExtras().getString(EXTRA_USER_LOGIN);
        if (userEmail != null) {
            viewModel.getUser(userEmail);
        }
    }
}
