package StartScreen.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.superfit.R;

import static android.content.Context.MODE_PRIVATE;

public class SignIn extends Fragment {
    private String AUTH_STATUS = "authorization";
    EditText edUserName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        edUserName = view.findViewById(R.id.edUserName);
        ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(v -> {
            viewPager.setCurrentItem(2, false);
            getActivity().findViewById(R.id.btn_return).setVisibility(View.VISIBLE);
        });

        if (getUserName()){
            viewPager.setCurrentItem(2);
        }


        Button btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.btn_return).setVisibility(View.GONE);
                viewPager.setCurrentItem(0, false);
            }
        });
        return view;
    }

    private boolean getUserName(){
        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences(AUTH_STATUS, MODE_PRIVATE);
        String userName = prefs.getString("userName", "none");
        if (userName == "none") return false;

        edUserName.setText(userName);

        return true;
    }
}