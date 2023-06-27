package com.example.gomuscuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    Button retour;

    TextView firstNameText;
    TextView lastNameText;
    ImageView userImageView;

    Random rn = new Random();
    int randomUserId = rn.nextInt(10) + 1;

    ApiClient apiClient = new ApiClient("https://dummyjson.com/");
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

        firstNameText = findViewById(R.id.FirstName);
        lastNameText = findViewById(R.id.LastName);
        userImageView = findViewById(R.id.userImageView);
        // Users API
        apiClient.getUserApi().getUser(randomUserId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                firstNameText.setText(user.getFirstName());
                lastNameText.setText(user.getLastName());
                Glide.with(getApplicationContext())
                        .load(user.getImage())
                        .into(userImageView);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}