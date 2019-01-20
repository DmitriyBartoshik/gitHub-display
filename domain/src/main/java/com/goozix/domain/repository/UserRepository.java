package com.goozix.domain.repository;

import com.goozix.domain.entity.User;
import com.goozix.domain.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<List<User>> getUserList(int USER_PER_PAGE, int userId);
    Observable<UserInfo> getUser(String login);
}
