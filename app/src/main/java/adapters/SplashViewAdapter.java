package adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.service.controls.templates.ControlButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import StartScreen.fragments.SignIn;
import StartScreen.fragments.SignInStep2;
import StartScreen.fragments.SignUp;

import static android.content.Context.MODE_PRIVATE;

public class SplashViewAdapter extends FragmentPagerAdapter {

    public SplashViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SignUp();
            case 2:
                return new SignInStep2();
            case 1:
                return new SignIn();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
