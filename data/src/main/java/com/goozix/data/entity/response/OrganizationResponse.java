package com.goozix.data.entity.response;

import com.google.gson.annotations.SerializedName;

public class OrganizationResponse {
    @SerializedName("login")
    private String login;

    public String getLogin() {
        return login;
    }
}
