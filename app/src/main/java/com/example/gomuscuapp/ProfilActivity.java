package com.example.gomuscuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfilActivity extends AppCompatActivity {
    Button retour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        retour = findViewById(R.id.btnRetour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Gym page");
                Intent intent = new Intent(ProfilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}