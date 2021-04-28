package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superfit.R;

import java.util.ArrayList;

import MainScreen.Exercise;

public class ExercisesListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Exercise> list;
    LayoutInflater lInflater;

    public ExercisesListAdapter(Context context, ArrayList<Exercise> exercises){
        this.context = context;
        this.list = exercises;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = lInflater.inflate(R.layout.exercises_item, parent, false);
        }

        Exercise cur_exer = (Exercise) getItem(position);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvItemTitle);
        TextView tvText = (TextView) convertView.findViewById(R.id.tvItemText);
        ImageView image = (ImageView) convertView.findViewById(R.id.itemImage);

        tvTitle.setText(cur_exer.title);
        tvText.setText(cur_exer.text);
        image.setImageDrawable(cur_exer.image);

        return convertView;
    }
}
