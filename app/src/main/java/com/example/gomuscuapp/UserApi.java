package com.example.gomuscuapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);
}
