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
            "Abdominals",
            "Abductors",
            "Biceps",
            "Calves",
            "Chest",
            "Forearms",
            "Glutes",
            "Hamstrings",
            "Lats",
            "Lower back",
            "Middle back",
            "Neck",
            "Quadriceps",
            "Traps",
            "Triceps"
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
