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

        // имя пользователя указанное при регистрации
        String userName = getUserName();

        btnSignIn.setOnClickListener(v -> {
            if (edUserName.getText().toString().equals(userName)){
                // Если имя пользователя в поле ввода совпадет с тем, что при регистрации
                viewPager.setCurrentItem(2, false);
                getActivity().findViewById(R.id.btn_return).setVisibility(View.VISIBLE);
            }else{
                Toast.makeText(getContext(), "Пользователь с таким именем не найден! Зарегистрируйтесь!", Toast.LENGTH_SHORT).show();
            }
            
        });

        // Автозаполнение поля ввода сохраннеными данными
        if (userName != "none"){
            edUserName.setText(getUserName());
            viewPager.setCurrentItem(2);
        }


        Button btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(v -> {
            // скрываю "стрелочку" по которой происходит возврат
            getActivity().findViewById(R.id.btn_return).setVisibility(View.GONE);
            viewPager.setCurrentItem(0, false);
        });
        return view;
    }

    private String getUserName(){
        // получение имени пользователя из бд
        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences(AUTH_STATUS, MODE_PRIVATE);
        String userName = prefs.getString("userName", "none");
        return userName;
    }
}