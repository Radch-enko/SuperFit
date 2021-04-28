package StartScreen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.superfit.MainActivity;
import com.example.superfit.R;

import adapters.CustomViewPager;


public class SignUp extends Fragment {

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

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomViewPager viewPager = getActivity().findViewById(R.id.viewPager);
                viewPager.setCurrentItem(0, false);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation(edUserName, edEmail, edCode, edRepeatCode)){
                    getActivity().finish();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
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
}