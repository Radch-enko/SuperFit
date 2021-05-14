package RecipesScreen;

import androidx.appcompat.app.AppCompatActivity;
import RecipesScreen.*;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.superfit.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class Recipe_detail extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // скрываю панель навигации
        if(Build.VERSION.SDK_INT >= 19)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        ImageButton btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView recipe_bg = findViewById(R.id.recipe_bg);

        Intent fromList = getIntent();

        Recipe curRecipe = (Recipe) fromList.getSerializableExtra("curRecipe");
        System.out.println("RecipeDetail, текущий рецепт: " + curRecipe.toString());

        TextView tvRecipeName = findViewById(R.id.tvRecipeName);
        tvRecipeName.setText(curRecipe.getTitle());

        TextView tvRecipeKcal = findViewById(R.id.tvRecipeKcal);
        tvRecipeKcal.setText(curRecipe.getKcal());

        TextView tvRecipeBJU = findViewById(R.id.tvRecipeBJU);
        tvRecipeBJU.setText(curRecipe.getInfo());

        Picasso.get().load(curRecipe.getImg()).placeholder(R.drawable.no_image).into(recipe_bg);

        ListView ingredientLines = findViewById(R.id.ingredientLines);

        IngredientLinesAdapter adapter = new IngredientLinesAdapter(this, curRecipe.getIngredientLines());

        ingredientLines.setAdapter(adapter);
    }
}