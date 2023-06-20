package com.example.gomuscuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.gomuscuapp.Exercices;

import java.util.List;

public class GymActivity extends AppCompatActivity {


    Button retour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_gym2);

        retour = findViewById(R.id.btnRetour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Gym page");
                Intent intent = new Intent(GymActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}