package com.goozix.data.repository;

import com.goozix.data.entity.response.UserResponse;
import com.goozix.data.entity.tranformer.ToDomainTransformer;
import com.goozix.data.net.RestService;
import com.goozix.domain.entity.User;
import com.goozix.domain.repository.UserRepository;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserRepositoryImpl implements UserRepository {
    private RestService restService;

    @Inject
    public UserRepositoryImpl(RestService restService) {
        this.restService = restService;
    }

    @Override
    public Observable<List<User>> getUserList() {
        return restService
                .getUserList()
                .map(new Function<List<UserResponse>, List<User>>() {
                    @Override
                    public List<User> apply(List<UserResponse> userResponses) throws Exception {

                        List<User> users = new ArrayList<>();
                        for (UserResponse userResponse : userResponses) {
                            User user = ToDomainTransformer.getInstance().userTransformer(userResponse);
                            users.add(user);
                        }
                        return users;
                    }
                });
    }

    @Override
    public Observable<User> getUser(String login) {
        return restService
                .getUser(login)
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse userResponse) throws Exception {
                        return ToDomainTransformer.getInstance().userTransformer(userResponse);
                    }
                });
    }
}
