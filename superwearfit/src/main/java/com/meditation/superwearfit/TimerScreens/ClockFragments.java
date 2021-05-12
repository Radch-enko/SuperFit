package com.meditation.superwearfit.TimerScreens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.meditation.superwearfit.R;

public class ClockFragments extends Fragment {

    Chronometer chronometer;
    long startTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            System.out.println("isVisibleToUser = true");
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }
        else{
            System.out.println("isVisibleToUser = false");
            if (chronometer != null){
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_clock_fragments, container, false);
        System.out.println("onCreateView");
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        final ViewPager viewPager = getActivity().findViewById(R.id.viewPager);

        LinearLayout clock_wrapper = view.findViewById(R.id.clock_wrapper);
        clock_wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewPager.setCurrentItem(0);
                                    }
                                });
                            }
                        },
                        2000
                );
            }
        });



        return view;
    }
}