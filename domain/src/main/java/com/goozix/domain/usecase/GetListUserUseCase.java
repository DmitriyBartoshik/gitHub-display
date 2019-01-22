package com.goozix.domain.usecase;

import com.goozix.domain.entity.User;
import com.goozix.domain.executors.PostExecutionThread;
import com.goozix.domain.repository.UserRepository;
import com.goozix.domain.usecase.base.BaseUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetListUserUseCase extends BaseUseCase {
    private UserRepository userRepository;

    @Inject
    public GetListUserUseCase(UserRepository userRepository,
                              PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository= userRepository;
    }

    public Observable<List<User>> getUserList(int userId) {
        return  userRepository
                .getUserList(userId)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
