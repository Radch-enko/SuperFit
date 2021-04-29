package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superfit.R;
import com.example.superfit.Recipe_detail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Recipes.Recipe;

public class RecipesRecyclerViewAdapter extends RecyclerView.Adapter<RecipesRecyclerViewAdapter.ViewHolder> implements Filterable {
    private ArrayList<Recipe> list, listFull;
    private Context context;

    public RecipesRecyclerViewAdapter(Context context, ArrayList<Recipe> userData) {
        this.context = context;
        this.list = userData;
        listFull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.kcal.setText(list.get(position).getKcal());
        holder.info.setText(list.get(position).getInfo());
        Picasso.get().load(list.get(position).getImg()).placeholder(R.drawable.no_image).into(holder.img);

        holder.recipe_wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDetail = new Intent(context, Recipe_detail.class);
                goToDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                goToDetail.putExtra("curRecipe", list.get(position));
                context.getApplicationContext().startActivity(goToDetail);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, kcal, info;
        ImageView img;
        LinearLayout recipe_wrapper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipe_wrapper = itemView.findViewById(R.id.recipe_wrapper);
            title = itemView.findViewById(R.id.tvRecipeName);
            kcal = itemView.findViewById(R.id.tvKcal);
            info = itemView.findViewById(R.id.tvRecipeInfo);
            img = itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Recipe> filteredList = new ArrayList<>();

            if (context == null || constraint.length() == 0){
                filteredList.addAll(listFull);
            }else {
                String filteredPattern = constraint.toString();
                System.out.println("filteredPattern: " + filteredPattern);

                for (Recipe item: listFull){
                    System.out.println("item.getType(): " + item.getType());
                    if (item.getType().contains(filteredPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<Recipe>)results.values);
            notifyDataSetChanged();
        }
    };
}
