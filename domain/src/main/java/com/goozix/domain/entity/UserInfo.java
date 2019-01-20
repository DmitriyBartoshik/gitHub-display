package com.goozix.domain.entity;

public class UserInfo {
    private String login;
    private String avatarUrl;
    private String name;
    private String email;
    private Integer following;
    private Integer followers;
    private String createdAt;

    public UserInfo() {
    }

    public String getLogin() {
        return login;
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

    public Integer getFollowing() {
        return following;
    }

    public Integer getFollowers() {
        return followers;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
