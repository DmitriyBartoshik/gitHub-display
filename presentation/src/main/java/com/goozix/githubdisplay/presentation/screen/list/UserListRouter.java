package com.goozix.githubdisplay.presentation.screen.list;

import com.goozix.githubdisplay.presentation.base.BaseRouter;
import com.goozix.githubdisplay.presentation.screen.single.UserInfoActivity;

public class UserListRouter extends BaseRouter<UserListActivity> {
    public UserListRouter(UserListActivity activity) {
        super(activity);
    }

    public void showSingleUser(String login){
        activity.startActivity(UserInfoActivity.getIntent(activity,login));
    }
}
