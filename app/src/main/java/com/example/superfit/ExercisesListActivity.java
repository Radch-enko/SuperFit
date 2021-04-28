package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import MainScreen.Exercise;
import adapters.ExercisesListAdapter;

public class ExercisesListActivity extends AppCompatActivity {

    ImageButton btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);

        btnReturn = findViewById(R.id.btnReturn);

        ArrayList<Exercise> listFull = new ArrayList<>();
        listFull.add(new Exercise("Push-Ups", "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.", getResources().getDrawable(R.drawable.push_ups)));
        listFull.add(new Exercise("Plank", "The plank strengthens the abdominals, back and shoulders. ", getResources().getDrawable(R.drawable.plank)));
        listFull.add(new Exercise("Push-Ups", "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.", getResources().getDrawable(R.drawable.push_ups)));
        listFull.add(new Exercise("Push-Ups", "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.", getResources().getDrawable(R.drawable.push_ups)));
        listFull.add(new Exercise("Push-Ups", "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.", getResources().getDrawable(R.drawable.push_ups)));
        ListView listView = findViewById(R.id.listView);

        ExercisesListAdapter adapter = new ExercisesListAdapter(this, listFull);

        listView.setAdapter(adapter);


        btnReturn.setOnClickListener(v -> finish());


    }
}