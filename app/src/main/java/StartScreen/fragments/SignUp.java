package StartScreen.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.superfit.R;

import MainScreen.MainActivity;
import adapters.CustomViewPager;
import adapters.SplashViewAdapter;

import static android.content.Context.MODE_PRIVATE;


public class SignUp extends Fragment {

    private String AUTH_STATUS = "authorization";
    CustomViewPager viewPager;
    SplashViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        Button btnSignUp = view.findViewById(R.id.btnSignUp);


        EditText edUserName = view.findViewById(R.id.edUserName);
        EditText edEmail = view.findViewById(R.id.edEmail);
        EditText edCode = view.findViewById(R.id.edCode);
        EditText edRepeatCode = view.findViewById(R.id.edRepeatCode);

        viewPager = getActivity().findViewById(R.id.viewPager);
        adapter = new SplashViewAdapter(getChildFragmentManager());

        if (checkAuthorization()){
            setSignInFragment();
            adapter.notifyDataSetChanged();
        }else{
            setSignUpFragment();
            adapter.notifyDataSetChanged();
        }

        btnSignIn.setOnClickListener(v -> viewPager.setCurrentItem(1, false));

        btnSignUp.setOnClickListener(v -> {
            if (checkValidation(edUserName, edEmail, edCode, edRepeatCode)){
                registration(edUserName, edEmail, edCode);
                getActivity().finish();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void registration(EditText edUserName, EditText edEmail, EditText edCode) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences(AUTH_STATUS, MODE_PRIVATE).edit();
        editor.putString("userName", edUserName.getText().toString());
        editor.putString("email", edEmail.getText().toString());
        editor.putInt("code", Integer.parseInt(edCode.getText().toString()));
        editor.apply();
        Toast.makeText(getContext(), "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkValidation(EditText edUserName, EditText edEmail, EditText edCode, EditText edRepeatCode) {
        if (edUserName.getText().toString().equals("") || edEmail.getText().toString().equals("") || edCode.getText().toString().equals("") || edRepeatCode.getText().equals("")) {
            Toast.makeText(getContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!edEmail.getText().toString().contains("@")){
            Toast.makeText(getContext(), "Проверьте правильность почты", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edCode.getText().toString().contains("0") || edRepeatCode.getText().toString().contains("0")){
            Toast.makeText(getContext(), "Код должен быть без 0", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!edCode.getText().toString().equals(edRepeatCode.getText().toString())){
            Toast.makeText(getContext(), "Коды не совпадают", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean checkAuthorization(){
        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences(AUTH_STATUS, MODE_PRIVATE);
        String userName = prefs.getString("userName", "none");
        String email = prefs.getString("email", "none");
        int code = prefs.getInt("code", 0);

        if (userName == "none" || email == "none" || code == 0) return false;

        return true;
    }

    private void setSignUpFragment(){
        viewPager.setCurrentItem(0, false);
    }
    private void setSignInFragment(){
        viewPager.setCurrentItem(1, false);
    }
}