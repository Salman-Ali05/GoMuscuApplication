package com.example.gomuscuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GymActivity extends AppCompatActivity {

    private static final String[] muscleNameList = {
            "abdominals",
            "abductors",
            "biceps",
            "calves",
            "chest",
            "forearms",
            "glutes",
            "hamstrings",
            "lats",
            "lower_back",
            "middle_back",
            "neck",
            "quadriceps",
            "traps",
            "triceps"
    };

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

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        GymAdapter adapter = new GymAdapter();
        recyclerView.setAdapter(adapter);
    }

    private class GymAdapter extends RecyclerView.Adapter<GymAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_div, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String muscleName = muscleNameList[position];
            holder.bind(muscleName);

            // Set click listener on the textView
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the clicked position
                    int clickedPosition = holder.getAdapterPosition();
                    // Get the muscle name based on the position
                    String clickedMuscleName = muscleNameList[clickedPosition];
                    // Pass the muscle name to the next activity
                    Intent intent = new Intent(GymActivity.this, Activity_details.class);
                    intent.putExtra("muscleName", clickedMuscleName);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return muscleNameList.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.txtMuscleName);
            }

            public void bind(String muscleName) {
                textView.setText(muscleName);
            }
        }
    }

}
