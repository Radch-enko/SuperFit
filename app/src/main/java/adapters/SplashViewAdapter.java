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
                System.out.println("case 1 called");
                return new SignIn();
            case 2:
                System.out.println("case 2 called");
                return new SignInStep2();
            default:
                System.out.println("case SingUp called");
                return new SignUp();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
