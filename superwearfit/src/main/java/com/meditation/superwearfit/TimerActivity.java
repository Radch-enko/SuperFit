package com.meditation.superwearfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.meditation.superwearfit.TimerScreens.ClockFragments;
import com.meditation.superwearfit.TimerScreens.FinishFragments;
import com.meditation.superwearfit.TimerScreens.StartFragment;

public class TimerActivity extends AppCompatActivity {

    public ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        viewPager = findViewById(R.id.viewPager);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new StartFragment();
                    case 1:
                        return new ClockFragments();
                    case 2:
                        return new FinishFragments();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
        viewPager.setAdapter(adapter);
    }
}