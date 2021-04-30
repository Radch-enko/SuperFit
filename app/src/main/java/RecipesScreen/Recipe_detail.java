package RecipesScreen;

import androidx.appcompat.app.AppCompatActivity;
import RecipesScreen.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.superfit.R;

import java.util.ArrayList;

public class Recipe_detail extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ImageButton btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent fromList = getIntent();

        Recipe curRecipe = (Recipe) fromList.getSerializableExtra("curRecipe");
        System.out.println("RecipeDetail, текущий рецепт: " + curRecipe.toString());

        TextView tvRecipeName = findViewById(R.id.tvRecipeName);
        tvRecipeName.setText(curRecipe.getTitle());

        TextView tvRecipeKcal = findViewById(R.id.tvRecipeKcal);
        tvRecipeKcal.setText(curRecipe.getKcal());

        TextView tvRecipeBJU = findViewById(R.id.tvRecipeBJU);
        tvRecipeBJU.setText(curRecipe.getInfo());

        ListView ingredientLines = findViewById(R.id.ingredientLines);

        IngredientLinesAdapter adapter = new IngredientLinesAdapter(this, curRecipe.getIngredientLines());

        ingredientLines.setAdapter(adapter);
    }
}