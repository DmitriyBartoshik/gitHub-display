package com.goozix.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goozix.data.entity.response.UserInfoResponse;
import com.goozix.data.entity.response.OrganizationResponse;
import com.goozix.data.entity.response.UserResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    private RestApi restApi;
    private Gson gson;
    private static final String GITHUB_REQUEST_URL = "https://api.github.com/";
    private ErrorParserTransformer errorParserTransformer;


    @Inject
    public RestService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        gson = new GsonBuilder()
                .create();

        this.restApi = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(GITHUB_REQUEST_URL)
                .client(okHttpClient)
                .build()
                .create(RestApi.class);

        errorParserTransformer = new ErrorParserTransformer(gson);
    }

    public Observable<List<UserResponse>> getUserList(int userId) {
        return restApi
                .getUserList(userId)
                .compose(errorParserTransformer.<List<UserResponse>, Throwable>parseHttpError());
    }

    public Observable<UserInfoResponse> getUser(String login) {
        return restApi
                .getUser(login)
                .compose(errorParserTransformer.<UserInfoResponse, Throwable>parseHttpError());
    }

    public Observable<List<OrganizationResponse>> getUserOrganization(String login) {
        return restApi
                .getUserOrganization(login)
                .compose(errorParserTransformer.<List<OrganizationResponse>, Throwable>parseHttpError());
    }
}