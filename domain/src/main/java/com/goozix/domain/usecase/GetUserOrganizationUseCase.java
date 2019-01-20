package com.goozix.domain.usecase;

import com.goozix.domain.entity.Organization;
import com.goozix.domain.entity.UserInfo;
import com.goozix.domain.executors.PostExecutionThread;
import com.goozix.domain.repository.UserRepository;
import com.goozix.domain.usecase.base.BaseUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetUserOrganizationUseCase extends BaseUseCase {
    private UserRepository userRepository;

    @Inject
    public GetUserOrganizationUseCase(UserRepository userRepository,
                                      PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<List<Organization>> getUserOrganization(String login) {
        return userRepository
                .getUserOrganization(login)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
