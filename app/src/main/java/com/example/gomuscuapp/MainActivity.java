package com.example.gomuscuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button goToGym; //gym page button
    Button goToCardio; //cardio page button
    Button goToProfil; //profil page button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToGym = findViewById(R.id.btnGym);

        goToGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Gym page");
                Intent intent = new Intent(MainActivity.this, GymActivity.class);
                startActivity(intent);
            }
        });

        goToCardio = findViewById(R.id.btnCardio);

        goToCardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Cardio page");
                Intent intent = new Intent(MainActivity.this, CardioActivity.class);
                startActivity(intent);
            }
        });

        goToProfil = findViewById(R.id.btnProfil);

        goToProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Profil page");
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });

        Button goToTest;

        goToTest = findViewById(R.id.buttonTestApi);
        goToTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("api page");
                Intent intent = new Intent(MainActivity.this, TestApi.class);
                startActivity(intent);
            }
        });
    }
}