package com.example.gomuscuapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_details extends AppCompatActivity {

    private static final String API_KEY = "0fSoE6wmC8tp9P82namp2A==tIwwgtrd8ZuCZ3Wg";
    private static final String BASE_URL = "https://api.api-ninjas.com/v1/";
    private static final String API_ENDPOINT = "exercises";
    private TextView muscleNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Retrieve the muscle name from the intent extras
        String muscleName = getIntent().getStringExtra("muscleName");

        // Find the TextView in your layout
        muscleNameTextView = findViewById(R.id.txtMuscleName);

        // Set the muscle name to the TextView
        muscleNameTextView.setText(muscleName);

        makeApiRequest(muscleName);
    }

    private void makeApiRequest(String muscleName) {
        OkHttpClient client = new OkHttpClient();

        String apiEndpoint = BASE_URL + API_ENDPOINT + "?muscle=" + muscleName;
        Request request = new Request.Builder()
                .url(apiEndpoint)
                .addHeader("X-Api-Key", API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        muscleNameTextView.setText("Error: " + e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            muscleNameTextView.setText(responseData);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            muscleNameTextView.setText("Error: " + response.code() + " " + responseData);
                        }
                    });
                }
            }
        });
    }
}
