package StartScreen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.superfit.R;

public class SignIn extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(v -> {
            viewPager.setCurrentItem(1, false);
            getActivity().findViewById(R.id.btn_return).setVisibility(View.VISIBLE);
        });


        Button btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.btn_return).setVisibility(View.GONE);
                viewPager.setCurrentItem(2, false);
            }
        });
        return view;
    }
}