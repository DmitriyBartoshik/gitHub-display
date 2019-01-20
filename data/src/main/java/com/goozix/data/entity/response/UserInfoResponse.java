package com.goozix.data.entity.response;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse implements DataModel {

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("company")
    private String company;

    @SerializedName("following")
    private Integer following;

    @SerializedName("followers")
    private Integer followers;

    @SerializedName("created_at")
    private String createdAt;

    public UserInfoResponse() {
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public Integer getFollowing() {
        return following;
    }

    public Integer getFollowers() {
        return followers;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
