package MainScreen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import ExerciseListScreen.ExercisesListActivity;
import com.example.superfit.R;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

import RecipesScreen.Recipes;
import StartScreen.fragments.Splash;
import adapters.ExercisesListAdapter;
import me.aflak.bluetooth.Bluetooth;


public class MainActivity extends AppCompatActivity {

        private String AUTH_STATUS = "authorization";

    //Сокет, с помощью которого мы будем отправлять данные на Arduino
    BluetoothSocket clientSocket;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if(Build.VERSION.SDK_INT >= 19)
            {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }

            Button btnSeeAll = findViewById(R.id.btnSeeAll);

            ArrayList<Exercise> listCollapsed = new ArrayList<>();
            listCollapsed.add(new Exercise("Push-Ups", "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.", getResources().getDrawable(R.drawable.push_ups)));
            listCollapsed.add(new Exercise("Plank", "The plank strengthens the abdominals, back and shoulders. ", getResources().getDrawable(R.drawable.plank)));

            ListView listView = findViewById(R.id.listView);

            ExercisesListAdapter adapter = new ExercisesListAdapter(this, listCollapsed);

            listView.setAdapter(adapter);

            btnSeeAll.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), ExercisesListActivity.class);
                startActivity(intent);
            });

            Button btnRecipes = findViewById(R.id.btnRecipes);
            btnRecipes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Recipes.class);
                    startActivity(intent);
                }
            });

            Button btnSignOut = findViewById(R.id.btnSignOut);
            btnSignOut.setOnClickListener(v -> {
                SharedPreferences.Editor editor = getSharedPreferences(AUTH_STATUS, MODE_PRIVATE).edit();
                editor.clear().commit();
                finish();
                Intent goToSplash = new Intent(getApplicationContext(), Splash.class);
                startActivity(goToSplash);
            });


            BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();

            ArrayList<BluetoothDevice> list = null;
                list = getBondedBtDevices(bluetooth);

            list.forEach(v -> {
                System.out.println(" CurItem : " + v.getName());
            });
            System.out.println("Lenght = " + list.size());

                    //Инициируем соединение с устройством
            Method m = null;
            try {
                m = list.get(0).getClass().getMethod(
                "createRfcommSocket", new Class[] {int.class});
                System.out.println("Method : " + m.getName());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                clientSocket = (BluetoothSocket) m.invoke(list.get(0), 1);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("clientSocket.getRemoteDevice() " +  clientSocket.getRemoteDevice());
                clientSocket.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private ArrayList<BluetoothDevice> getBondedBtDevices(BluetoothAdapter bluetooth) {
        Set<BluetoothDevice> deviceSet = bluetooth.getBondedDevices();
        ArrayList<BluetoothDevice> tmpArrayList = new ArrayList<>();
        if (deviceSet.size() > 0) {
            for (BluetoothDevice device: deviceSet) {
                tmpArrayList.add(device);
            }
        }
        return tmpArrayList;
    }
    }
