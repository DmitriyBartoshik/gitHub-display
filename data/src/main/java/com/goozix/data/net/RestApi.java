package com.goozix.data.net;

import com.goozix.data.entity.response.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApi {
    @GET("users")
    Observable<List<UserResponse>> getUserList();
    @GET("users/{login}")
    Observable<UserResponse> getUser(@Path("login") String login);
}
