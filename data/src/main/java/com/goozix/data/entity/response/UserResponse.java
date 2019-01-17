package com.goozix.data.entity.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse implements DataModel {
    @SerializedName("login")
    @Expose
    private String login;

    public UserResponse() {
    }

    public UserResponse(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
