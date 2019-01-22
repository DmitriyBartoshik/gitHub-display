package com.goozix.data.repository;

import com.goozix.data.entity.response.OrganizationResponse;
import com.goozix.data.entity.response.UserResponse;
import com.goozix.data.entity.response.UserInfoResponse;
import com.goozix.data.entity.tranformer.ToDomainTransformer;
import com.goozix.data.net.RestService;
import com.goozix.domain.entity.Organization;
import com.goozix.domain.entity.User;
import com.goozix.domain.entity.UserInfo;
import com.goozix.domain.repository.UserRepository;

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
    public Observable<List<User>> getUserList(int userId) {
        return restService
                .getUserList(userId)
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
    public Observable<UserInfo> getUser(String login) {
        return restService
                .getUser(login)
                .map(new Function<UserInfoResponse, UserInfo>() {
                    @Override
                    public UserInfo apply(UserInfoResponse userInfoResponse) throws Exception {
                        return ToDomainTransformer.getInstance().userInfoTransformer(userInfoResponse);
                    }
                });
    }

    @Override
    public Observable<List<Organization>> getUserOrganization(String login) {
        return restService.
                getUserOrganization(login)
                .map(new Function<List<OrganizationResponse>, List<Organization>>() {
                    @Override
                    public List<Organization> apply(List<OrganizationResponse> organizationResponses) throws Exception {
                        List<Organization> organizations = new ArrayList<>();
                        for (OrganizationResponse organizationResponse : organizationResponses) {
                            Organization organization = ToDomainTransformer.getInstance().organizationTransformer(organizationResponse);
                            organizations.add(organization);
                        }
                        return organizations;
                    }
                });
    }
}

