package RecipesScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.superfit.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import adapters.RecipesRecyclerViewAdapter;

public class Recipes extends AppCompatActivity {

    ArrayList<Recipe> list = new ArrayList<Recipe>();
    RecipesRecyclerViewAdapter adapter;
    CheckBox rbLowFat, rbLowCarb, rbHighProtein;

    // строка для фильтрации по БЖУ
    StringBuilder filterQueryBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // filter buttons
        rbLowFat = findViewById(R.id.rbLowFat);
        rbLowCarb = findViewById(R.id.rbLowCarb);
        rbHighProtein = findViewById(R.id.rbHighProtein);

        filterQueryBuilder = new StringBuilder();


        RecyclerView rv = findViewById(R.id.rv);
        adapter = new RecipesRecyclerViewAdapter(this, list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        rbLowFat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                filterQueryBuilder.append(RecipesTypes.LOW_FAT.toString());
                adapter.getFilter().filter(filterQueryBuilder.toString());
            }
        });

        rbLowCarb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                filterQueryBuilder.append(RecipesTypes.LOW_CARB.toString());
                adapter.getFilter().filter(filterQueryBuilder.toString());
            }
        });

        rbHighProtein.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                filterQueryBuilder.append(RecipesTypes.HIGH_PROTEIN.toString());
                adapter.getFilter().filter(filterQueryBuilder.toString());
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
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
                        progressBar.setVisibility(View.GONE);
                        adapter = new RecipesRecyclerViewAdapter(getApplicationContext(), list);
                        adapter.notifyDataSetChanged();
                        rv.setAdapter(adapter);
                    });
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ImageButton btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> finish());
    }
}