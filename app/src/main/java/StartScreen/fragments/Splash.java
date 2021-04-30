package StartScreen.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.superfit.R;

import adapters.CustomViewPager;
import adapters.SplashViewAdapter;


public class Splash extends AppCompatActivity {

    CustomViewPager viewPager;
    private String AUTH_STATUS = "authorization";
    public SplashViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(Build.VERSION.SDK_INT >= 19)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        viewPager = findViewById(R.id.viewPager);
        adapter = new SplashViewAdapter(getSupportFragmentManager());

        viewPager.disableScroll(true);


        ImageButton btn_return = findViewById(R.id.btn_return);


        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        viewPager.disableScroll(true);
                        runOnUiThread(() -> runOnUiThread(() -> {
                            btn_return.setVisibility(View.VISIBLE);
                            viewPager.setAdapter(adapter);
                        }));
                    }
                },
                2000
        );

        btn_return.setOnClickListener(v -> {
            setSignInFragment();
            btn_return.setVisibility(View.GONE);
        });

    }

    private void setSignUpFragment(){
        viewPager.setCurrentItem(0, false);
    }
    private void setSignInFragment(){
        viewPager.setCurrentItem(1, false);
    }
}