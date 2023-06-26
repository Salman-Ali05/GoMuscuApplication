package com.example.gomuscuapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CardioActivity extends AppCompatActivity {

    private static final String API_KEY = "0fSoE6wmC8tp9P82namp2A==tIwwgtrd8ZuCZ3Wg";
    private static final String BASE_URL = "https://api.api-ninjas.com/v1/";
    private static final String API_ENDPOINT = "exercises";
    private LinearLayout exerciseContainer;

    Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio2);

        retour = findViewById(R.id.btnRetour);
        TextView txtMuscle = findViewById(R.id.txtMuscle);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Gym page");
                Intent intent = new Intent(CardioActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        txtMuscle.setText("Cardio".toUpperCase());

        exerciseContainer = findViewById(R.id.exerciseContainer);

        makeApiRequest("cardio");
    }

    private void makeApiRequest(String muscleName) {
        OkHttpClient client = new OkHttpClient();

        String apiEndpoint = BASE_URL + API_ENDPOINT + "?type=cardio";
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
                                exerciseContainer.removeAllViews();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    System.out.println(jsonArray);
                                    /*
                                    JSONObject exercise = jsonArray.getJSONObject(i);
                                    String cardioName = exercise.getString("name");
                                    String cardioEquip = exercise.getString("equipment");
                                    String cardioDiff = exercise.getString("difficulty");
                                    String cardioInstruc = exercise.getString("instructions");

                                    View exerciseItemView = getLayoutInflater().inflate(R.layout.item_muscle_div, exerciseContainer, false);

                                    TextView cardioNameTextView = exerciseItemView.findViewById(R.id.txtExerciseName);
                                    TextView cardioEquipTextView = exerciseItemView.findViewById(R.id.txtExerciseEquipment);
                                    TextView cardioDiffTextView = exerciseItemView.findViewById(R.id.txtExerciseDifficulty);
                                    TextView cardioInstrucTextView = exerciseItemView.findViewById(R.id.txtExerciseInstructions);

                                    cardioNameTextView.setText("Exercise Name: " + cardioName);
                                    cardioEquipTextView.setText("Equipment: " + cardioEquip);
                                    cardioDiffTextView.setText("Difficulty: " + cardioDiff);
                                    cardioInstrucTextView.setText("Instructions: " + cardioInstruc);

                                    exerciseContainer.addView(exerciseItemView);

                                     */
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                TextView errorTextView = new TextView(CardioActivity.this);
                                errorTextView.setText("Error: Failed to parse exercise data");
                                exerciseContainer.addView(errorTextView);
                            }
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView errorTextView = new TextView(CardioActivity.this);
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
                        TextView errorTextView = new TextView(CardioActivity.this);
                        errorTextView.setText("Error: " + e.getMessage());
                        exerciseContainer.addView(errorTextView);
                    }
                });
            }
        });
    }
}
