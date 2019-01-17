package com.goozix.githubdisplay.presentation.screen.list;

import com.goozix.githubdisplay.presentation.base.BaseRouter;
import com.goozix.githubdisplay.presentation.screen.single.UserActivity;

public class UserListRouter extends BaseRouter<UserListActivity> {
    public UserListRouter(UserListActivity activity) {
        super(activity);
    }

    public void showSingleUser(String email){
        activity.startActivity(UserActivity.getIntent(activity,email));
    }
}
