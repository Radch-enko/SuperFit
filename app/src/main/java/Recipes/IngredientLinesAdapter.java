package Recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.superfit.R;

import java.util.ArrayList;

public class IngredientLinesAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> ingredients;
    LayoutInflater lInflater;

    public IngredientLinesAdapter(Context context, ArrayList<String> ingredients){
        this.context = context;
        this.ingredients = ingredients;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = lInflater.inflate(R.layout.ingredient_line, parent, false);
        }

        TextView tvIngredientText = (TextView) convertView.findViewById(R.id.tvIngredientText);
        tvIngredientText.setText( (String) getItem(position) );

        return convertView;
    }
}
