package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import MainScreen.Exercise;
import adapters.ExercisesListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= 19)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        Button btnSeeAll = findViewById(R.id.btnSeeAll);

        ArrayList<Exercise> listCollapsed = new ArrayList<>();
        listCollapsed.add(new Exercise("Push-Ups", "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.", getResources().getDrawable(R.drawable.push_ups)));
        listCollapsed.add(new Exercise("Plank", "The plank strengthens the abdominals, back and shoulders. ", getResources().getDrawable(R.drawable.plank)));

        ListView listView = findViewById(R.id.listView);

        ExercisesListAdapter adapter = new ExercisesListAdapter(this, listCollapsed);

        listView.setAdapter(adapter);

        btnSeeAll.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ExercisesListActivity.class);
            startActivity(intent);
        });

        Button btnRecipes = findViewById(R.id.btnRecipes);
        btnRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Recipes.class);
                startActivity(intent);
            }
        });

    }
}