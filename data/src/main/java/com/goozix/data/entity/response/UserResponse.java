package com.goozix.data.entity.response;

import com.google.gson.annotations.SerializedName;
import com.goozix.domain.entity.DomainModel;

public class UserResponse implements DomainModel {
    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private Integer id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
