package mybody_screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superfit.R;

public class MyBodyActivity extends AppCompatActivity {

    private static final String BODY_DATA = "BODY_DATA";
    private static final String HEIGHT = "height", WEIGHT = "weight";
    Button btnChangeWeight, btnChangeHeight;
    SharedPreferences prefs;


    TextView tvWeight, tvHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_body);

        if(Build.VERSION.SDK_INT >= 19)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);

        prefs = this.getSharedPreferences(BODY_DATA, MODE_PRIVATE);

        tvHeight.setText(prefs.getString(HEIGHT, "Undefined") + " cm");
        tvWeight.setText(prefs.getString(WEIGHT, "Undefined") + " kg");


        btnChangeWeight = findViewById(R.id.btnChangeWeight);
        btnChangeHeight = findViewById(R.id.btnChangeHeight);

        btnChangeWeight.setOnClickListener(v -> {
            showChangeBodyDataDialog(MyBodyActivity.this, WEIGHT);
        });

        btnChangeHeight.setOnClickListener(v -> {
            showChangeBodyDataDialog(MyBodyActivity.this, HEIGHT);
        });



    }

    private void showChangeBodyDataDialog(MyBodyActivity myBodyActivity, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(myBodyActivity, R.style.ChangeBodyDataDialog));
        builder.setTitle("Change your " + type);
        LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.change_body_data_dialog, null);
        builder.setView(view);

        EditText editText = view.findViewById(R.id.editText);

        DialogInterface.OnClickListener listener = null;

        SharedPreferences.Editor editor = myBodyActivity.getSharedPreferences(BODY_DATA, MODE_PRIVATE).edit();
        switch (type){
            case HEIGHT:
                listener = (dialog, which) -> {
                    editor.putString(HEIGHT, editText.getText().toString());
                    editor.apply();
                    tvHeight.setText(prefs.getString(HEIGHT, "Undefined") + " cm");
                    tvWeight.setText(prefs.getString(WEIGHT, "Undefined") + " kg");
                };
                break;
            case WEIGHT:
                listener = (dialog, which) -> {
                    editor.putString(WEIGHT, editText.getText().toString());
                    editor.apply();
                    tvHeight.setText(prefs.getString(HEIGHT, "Undefined") + " cm");
                    tvWeight.setText(prefs.getString(WEIGHT, "Undefined") + " kg");
                };
                break;
            default:
                break;
        }

        builder.setNegativeButton("CANCEL", (dialog, which) -> {
           dialog.dismiss();
        });

        if (listener == null) Toast.makeText(myBodyActivity, "listener is null !!!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(myBodyActivity, "listener is not null !!!", Toast.LENGTH_SHORT).show();
        builder.setPositiveButton("CHANGE", listener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}