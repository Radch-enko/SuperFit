package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superfit.R;

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
        holder.img.setImageDrawable(list.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, kcal, info;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
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
                String filteredPattern = constraint.toString().toLowerCase().trim();

                for (Recipe item: listFull){
                    if (item.getTitle().toLowerCase().contains(filteredPattern)){
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
