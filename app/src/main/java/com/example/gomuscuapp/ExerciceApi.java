package com.example.gomuscuapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ExerciceApi {
    @GET("v1/exercises")
    Call<List<Exercices>> getExercisesByMuscle(
            @Query("muscle") String muscle,
            @Query("api_key") String apiKey
    );
}

