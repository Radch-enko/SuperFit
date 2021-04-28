package StartScreen.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.superfit.R;

import adapters.CustomViewPager;
import adapters.SplashViewAdapter;

public class Splash extends AppCompatActivity {

    CustomViewPager viewPager;

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
        SplashViewAdapter adapter = new SplashViewAdapter(getSupportFragmentManager());

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        viewPager.disableScroll(true);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setAdapter(adapter);
                            }
                        });
                    }
                },
                2000
        );


        ImageButton btn_return = findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0, false);
                btn_return.setVisibility(View.GONE);
            }
        });

    }
}