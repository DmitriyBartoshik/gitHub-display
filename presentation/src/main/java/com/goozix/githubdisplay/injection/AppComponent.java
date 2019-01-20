package com.goozix.githubdisplay.injection;

import com.goozix.githubdisplay.presentation.screen.list.UserListViewModel;
import com.goozix.githubdisplay.presentation.screen.single.UserInfoViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void runInject(UserListViewModel userListViewModel);
    void runInject(UserInfoViewModel userListViewModel);

}
