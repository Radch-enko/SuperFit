package ExerciseListScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.superfit.R;

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
        listFull.add(new Exercise("Squats", "Сonsidered a vital exercise for increasing the strength and size of the lower body.", getResources().getDrawable(R.drawable.squats)));
        listFull.add(new Exercise("Crunch", "It involves the entire abs, but primarily it works the rectus abdominis muscle.", getResources().getDrawable(R.drawable.crunch)));
        listFull.add(new Exercise("Running", "It develops endurance, strengthens the legs and the cardiovascular system.", getResources().getDrawable(R.drawable.running)));
        ListView listView = findViewById(R.id.listView);

        ExercisesListAdapter adapter = new ExercisesListAdapter(this, listFull);

        listView.setAdapter(adapter);


        btnReturn.setOnClickListener(v -> finish());
    }
}