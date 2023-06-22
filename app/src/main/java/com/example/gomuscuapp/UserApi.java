package com.example.gomuscuapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {
    @GET("users/1")
    Call<User> getUser();
}
