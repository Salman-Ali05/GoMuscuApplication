package com.example.gomuscuapp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class ApiClient {

    private static final String BASE_URL = "https://api.api-ninjas.com/v1/";
    private static final String API_KEY = "0fSoE6wmC8tp9P82namp2A==tIwwgtrd8ZuCZ3Wg";
    private static final String API_ENDPOINT = "exercises";

    private static ApiClient instance;
    private ExerciseApi exerciseApi;

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        exerciseApi = retrofit.create(ExerciseApi.class);
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public void getExercisesByMuscle(String muscleName, Callback<List<Exercise>> callback) {
        Call<List<Exercise>> call = exerciseApi.getExercisesByMuscle(muscleName, API_KEY);
        call.enqueue(callback);
    }
}
