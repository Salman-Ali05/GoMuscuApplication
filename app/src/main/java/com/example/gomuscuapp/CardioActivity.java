package com.example.gomuscuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardioActivity extends AppCompatActivity {

    Button retour; //retour button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio2);

        retour = findViewById(R.id.btnRetour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Gym page");
                Intent intent = new Intent(CardioActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}