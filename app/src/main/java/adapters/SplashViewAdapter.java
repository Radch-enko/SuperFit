package adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import StartScreen.fragments.SignIn;
import StartScreen.fragments.SignInStep2;
import StartScreen.fragments.SignUp;

public class SplashViewAdapter extends FragmentPagerAdapter {
    public SplashViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SignIn();
            case 1:
                return new SignInStep2();
            case 2:
                return new SignUp();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
