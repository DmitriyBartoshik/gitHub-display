package com.goozix.domain.repository;

import com.goozix.domain.entity.User;

import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<List<User>> getUserList();

    Observable<User> getUser(String login);

}
