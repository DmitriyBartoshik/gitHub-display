package com.goozix.domain.entity;

public class User implements DomainModel {
    private String login;
    private Integer id;
    private String avatarUrl;
    private String name;
    private String email;
    private String company;
    private Integer following;
    private Integer followers;
    private String createdAt;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setCompany(String company) {
        this.company = company;
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
