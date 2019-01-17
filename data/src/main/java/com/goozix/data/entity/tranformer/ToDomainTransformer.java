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
        return user;
    }
}
