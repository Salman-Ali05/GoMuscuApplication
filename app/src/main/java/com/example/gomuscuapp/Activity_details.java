package com.example.gomuscuapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private LinearLayout exerciseContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Retrieve the muscle name from the intent extras
        String muscleName = getIntent().getStringExtra("muscleName");

        // Find the exercise container in your layout
        exerciseContainer = findViewById(R.id.exerciseContainer);

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
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonArray = new JSONArray(responseData);
                                exerciseContainer.removeAllViews(); // Clear existing views

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject exercise = jsonArray.getJSONObject(i);
                                    String exerciseName = exercise.getString("name");
                                    String exerciseEquip = exercise.getString("equipment");
                                    String exerciseMuscle = exercise.getString("muscle");
                                    String exerciseDiff = exercise.getString("difficulty");
                                    String exerciseInstruc = exercise.getString("instructions");

                                    // Create a new TextView for each exercise detail
                                    TextView exerciseNameTextView = new TextView(Activity_details.this);
                                    exerciseNameTextView.setText("Exercise Name: " + exerciseName);
                                    exerciseContainer.addView(exerciseNameTextView);

                                    TextView exerciseEquipTextView = new TextView(Activity_details.this);
                                    exerciseEquipTextView.setText("Equipment: " + exerciseEquip);
                                    exerciseContainer.addView(exerciseEquipTextView);

                                    TextView exerciseMuscleTextView = new TextView(Activity_details.this);
                                    exerciseMuscleTextView.setText("Muscle: " + exerciseMuscle);
                                    exerciseContainer.addView(exerciseMuscleTextView);

                                    TextView exerciseDiffTextView = new TextView(Activity_details.this);
                                    exerciseDiffTextView.setText("Difficulty: " + exerciseDiff);
                                    exerciseContainer.addView(exerciseDiffTextView);

                                    TextView exerciseInstrucTextView = new TextView(Activity_details.this);
                                    exerciseInstrucTextView.setText("Instructions: " + exerciseInstruc);
                                    exerciseContainer.addView(exerciseInstrucTextView);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                TextView errorTextView = new TextView(Activity_details.this);
                                errorTextView.setText("Error: Failed to parse exercise data");
                                exerciseContainer.addView(errorTextView);
                            }
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView errorTextView = new TextView(Activity_details.this);
                            errorTextView.setText("Error: " + response.code() + " " + responseData);
                            exerciseContainer.addView(errorTextView);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView errorTextView = new TextView(Activity_details.this);
                        errorTextView.setText("Error: " + e.getMessage());
                        exerciseContainer.addView(errorTextView);
                    }
                });
            }
        });
    }
}
