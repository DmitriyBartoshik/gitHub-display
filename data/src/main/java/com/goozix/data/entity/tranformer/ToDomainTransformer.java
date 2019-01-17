package com.goozix.data.entity.tranformer;

import com.goozix.data.entity.response.UserResponse;
import com.goozix.domain.entity.User;

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
        user.setName(userResponse.getName());
        user.setEmail(userResponse.getEmail());
        user.setCompany(userResponse.getCompany());
        user.setFollowing(userResponse.getFollowing());
        user.setFollowers(userResponse.getFollowers());
        user.setCreatedAt(userResponse.getCreatedAt());
        return user;
    }
}
