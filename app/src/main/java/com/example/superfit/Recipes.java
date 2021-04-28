package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import Recipes.*;

import adapters.RecipesRecyclerViewAdapter;

public class Recipes extends AppCompatActivity {

    ArrayList<Recipe> list = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        list.add(new Recipe("1", "7177 kcal", "290g protein • 325g fat • 746g carbs", getResources().getDrawable(R.drawable.recipe1), RecipesTypes.BALANCED.toString()));
        list.add(new Recipe("12", "7177 kcal", "290g protein • 325g fat • 746g carbs", getResources().getDrawable(R.drawable.recipe1), RecipesTypes.HIGH_FIBER.toString()));
        list.add(new Recipe("123", "7177 kcal", "290g protein • 325g fat • 746g carbs", getResources().getDrawable(R.drawable.recipe1), RecipesTypes.HIGH_PROTEIN.toString()));

        SearchView searchView = (SearchView) findViewById(R.id.search_view);

        RecyclerView rv = findViewById(R.id.rv);
        RecipesRecyclerViewAdapter adapter = new RecipesRecyclerViewAdapter(this, list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (searchView.getQuery().length() < query.length())
                    adapter.getFilter().filter(query);
                else adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(Recipes.this, newText, Toast.LENGTH_SHORT).show();
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        ImageButton btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> finish());

    }
}