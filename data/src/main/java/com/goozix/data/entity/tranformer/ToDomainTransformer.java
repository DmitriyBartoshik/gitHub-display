package com.goozix.data.entity.tranformer;

import com.goozix.data.entity.response.OrganizationResponse;
import com.goozix.data.entity.response.UserResponse;
import com.goozix.data.entity.response.UserInfoResponse;
import com.goozix.domain.entity.Organization;
import com.goozix.domain.entity.User;
import com.goozix.domain.entity.UserInfo;

public class ToDomainTransformer {
    private static ToDomainTransformer instance = null;

    private ToDomainTransformer() {
    }

    public static ToDomainTransformer getInstance() {
        if (instance == null) {
            return new ToDomainTransformer();
        }
        return instance;
    }

    public User userTransformer(UserResponse userResponse) {
        User user = new User();
        user.setLogin(userResponse.getLogin());
        user.setId(userResponse.getId());
        user.setAvatarUrl(userResponse.getAvatarUrl());
        return user;
    }

    public UserInfo userInfoTransformer(UserInfoResponse userInfoResponse) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLogin(userInfoResponse.getLogin());
        userInfo.setAvatarUrl(userInfoResponse.getAvatarUrl());
        userInfo.setName(userInfoResponse.getName());
        userInfo.setEmail(userInfoResponse.getEmail());
        userInfo.setFollowing(userInfoResponse.getFollowing());
        userInfo.setFollowers(userInfoResponse.getFollowers());
        userInfo.setCreatedAt(userInfoResponse.getCreatedAt());
        return userInfo;
    }

    public Organization organizationTransformer(OrganizationResponse organizationResponse){
        Organization organization=new Organization();
        organization.setLogin(organizationResponse.getLogin());
        return organization;
    }
}
