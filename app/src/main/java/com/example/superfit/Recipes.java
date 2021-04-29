package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import Recipes.*;

import adapters.RecipesRecyclerViewAdapter;

public class Recipes extends AppCompatActivity {

    ArrayList<Recipe> list = new ArrayList<Recipe>();
    RecipesRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        SearchView searchView = (SearchView) findViewById(R.id.search_view);

        RecyclerView rv = findViewById(R.id.rv);
        adapter = new RecipesRecyclerViewAdapter(this, list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);





        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ParsingAPI parser = new ParsingAPI();

                AsyncTask.execute(() -> {
                    try {
                        list = parser.parse("https://api.edamam.com/search?q=" + query + "&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=high-protein");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(() -> {
                        adapter = new RecipesRecyclerViewAdapter(getApplicationContext(), list);
                        adapter.notifyDataSetChanged();
                        rv.setAdapter(adapter);
                    });
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                ParsingAPI parser = new ParsingAPI();
//
//                AsyncTask.execute(() -> {
//                    try {
//                        list = parser.parse("https://api.edamam.com/search?q=" + newText + "&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=high-protein");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    runOnUiThread(() -> {
//                        adapter = new RecipesRecyclerViewAdapter(getApplicationContext(), list);
//                        adapter.notifyDataSetChanged();
//                        rv.setAdapter(adapter);
//                    });
//                });
                return false;
            }
        });

        ImageButton btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> finish());
    }



    public void startParsing(String newText){
//        MyTask.execute(() -> {
//
//            try {
//
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            }
//        });
    }
}