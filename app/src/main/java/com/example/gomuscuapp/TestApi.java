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

public class TestApi extends AppCompatActivity {

    private static final String API_KEY = "0fSoE6wmC8tp9P82namp2A==tIwwgtrd8ZuCZ3Wg";
    private static final String MUSCLE = "biceps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);

        makeApiRequest();
    }

    private void makeApiRequest() {
        OkHttpClient client = new OkHttpClient();

        String apiEndpoint = "https://api.api-ninjas.com/v1/exercises?muscle=" + MUSCLE;
        Request request = new Request.Builder()
                .url(apiEndpoint)
                .addHeader("X-Api-Key", API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = findViewById(R.id.txtTestApi);
                            textView.setText(responseData);
                        }
                    });
                } else {
                    final String errorData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Handle API error here
                            TextView textView = findViewById(R.id.txtTestApi);
                            textView.setText("Error: " + response.code() + " " + errorData);
                        }
                    });
                }
            }
        });
    }
}
