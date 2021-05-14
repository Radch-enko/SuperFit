package MainScreen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ExerciseListScreen.ExercisesListActivity;
import com.example.superfit.R;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

import RecipesScreen.Recipes;
import StartScreen.fragments.Splash;
import adapters.ExercisesListAdapter;
import mybody_screen.MyBodyActivity;


public class MainActivity extends AppCompatActivity {

    private String AUTH_STATUS = "authorization";

    Button btnDetails;

    private static final String BODY_DATA = "BODY_DATA";
    private static final String HEIGHT = "height", WEIGHT = "weight";

    TextView tvWeight, tvHeight;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // скрываю панель навигации
        if(Build.VERSION.SDK_INT >= 19)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);
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
        btnRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Recipes.class);
            startActivity(intent);
        });

        Button btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences(AUTH_STATUS, MODE_PRIVATE).edit();
            editor.clear().commit();
            finish();
            Intent goToSplash = new Intent(getApplicationContext(), Splash.class);
            startActivity(goToSplash);
        });

        btnDetails = findViewById(R.id.btnDetails);
        btnDetails.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyBodyActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = this.getSharedPreferences(BODY_DATA, MODE_PRIVATE);
        tvHeight.setText(prefs.getString(HEIGHT, "Undefined") + " cm");
        tvWeight.setText(prefs.getString(WEIGHT, "Undefined") + " kg");
    }
}
