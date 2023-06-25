package com.example.gomuscuapp;

import com.example.gomuscuapp.Exercise;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

public interface ExerciseApi {
    @GET("exercises")
    Call<List<Exercise>> getExercisesByMuscle(
            @Query("muscle") String muscle,
            @Query("apiKey") String apiKey
    );

    @GET("exercises&type=cardio")
    Call<List<CardioEx>> getCardioEx(
            @Query("apiKey") String apiKey
    );
}
