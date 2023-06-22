package com.example.gomuscuapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersApiService {
    private static final String BASE_URL = "https://dummyjson.com/";

    private UserApi userApi;

    UsersApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);


    }

    public Call<User> getUser() {
        return userApi.getUser();
    }
}