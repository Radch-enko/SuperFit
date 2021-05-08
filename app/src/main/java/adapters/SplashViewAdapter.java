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
            case 1:
                return new SignIn();
            case 2:
                return new SignInStep2();
            default:
                return new SignUp();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
