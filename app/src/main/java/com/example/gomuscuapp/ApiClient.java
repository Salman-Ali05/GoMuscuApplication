package com.example.gomuscuapp;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {
    private static final String BASE_URL = "https://api.api-ninjas.com/";
    private static final String API_KEY = "0fSoE6wmC8tp9P82namp2A==tIwwgtrd8ZuCZ3Wg";

    private OkHttpClient client;

    public ApiClient() {
        client = new OkHttpClient();
    }

    public void getExercisesByMuscle(String muscle, Callback callback) {
        String apiEndpoint = BASE_URL + "v1/exercises?type=strength&muscle=" + muscle;
        Request request = new Request.Builder()
                .url(apiEndpoint)
                .addHeader("X-Api-Key", API_KEY)
                .build();

        client.newCall(request).enqueue(callback);
    }

    // Other API request methods here

    // Add any necessary helper methods or data models for processing the API response
}
