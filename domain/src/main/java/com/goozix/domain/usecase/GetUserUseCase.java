package com.goozix.domain.usecase;

import com.goozix.domain.entity.User;
import com.goozix.domain.executors.PostExecutionThread;
import com.goozix.domain.repository.UserRepository;
import com.goozix.domain.usecase.base.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetUserUseCase extends BaseUseCase {
    private UserRepository userRepository;

    @Inject
    public GetUserUseCase(UserRepository userRepository,
                             PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<User> getUser(String user) {
        return userRepository
                .getUser(user)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
