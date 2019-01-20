package com.goozix.data.net;

import com.goozix.data.entity.response.OrganizationResponse;
import com.goozix.data.entity.response.UserResponse;
import com.goozix.data.entity.response.UserInfoResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {
    @GET("users")
    Observable<List<UserResponse>> getUserList(
            @Query("per_page") int USER_PER_PAGE,
            @Query("since") int userID);

    @GET("users/{login}")
    Observable<UserInfoResponse> getUser(@Path("login") String login);

    @GET("users/{login}/orgs")
    Observable<List<OrganizationResponse>> getUserOrganization(@Path("login") String login);
}
