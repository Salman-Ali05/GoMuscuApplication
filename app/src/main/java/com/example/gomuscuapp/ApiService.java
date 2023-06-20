package com.example.gomuscuapp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class ApiService {
    private static final String BASE_URL = "https://api.api-ninjas.com/";
    private ExerciceApi exerciseApi;
    private static ApiService instance;

    ApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        exerciseApi = retrofit.create(ExerciceApi.class);
    }

    public static synchronized ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public void getExercisesByMuscle(String muscle, String apiKey, Callback<List<Exercices>> callback) {
        Call<List<Exercices>> call = exerciseApi.getExercisesByMuscle(muscle, apiKey);
        call.enqueue(callback);
    }
}

