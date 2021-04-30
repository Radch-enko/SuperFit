package RecipesScreen;

import androidx.appcompat.app.AppCompatActivity;
import RecipesScreen.*;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

//        Picasso.get().load(curRecipe.getImg()).centerCrop().resize(recipe_detail_wrapper.getMeasuredWidth(), recipe_detail_wrapper.getMeasuredHeight()).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
//                //setting background
//                recipe_detail_wrapper.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
//            }
//
//            @Override
//            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//            }
//            @Override
//            public void onPrepareLoad(Drawable drawable) {
//
//            }
//
//        });

        ListView ingredientLines = findViewById(R.id.ingredientLines);

        IngredientLinesAdapter adapter = new IngredientLinesAdapter(this, curRecipe.getIngredientLines());

        ingredientLines.setAdapter(adapter);
    }
}