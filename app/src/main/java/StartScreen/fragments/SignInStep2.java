package StartScreen.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import HelpersObjects.VibrateService;
import MainScreen.Exercise;
import MainScreen.MainActivity;

import com.example.superfit.R;

import static android.content.Context.MODE_PRIVATE;

public class SignInStep2 extends Fragment implements View.OnClickListener {
    private String AUTH_STATUS = "authorization";
    StringBuilder builder;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in_step2, container, false);
        btn1 = view.findViewById(R.id.button);
        btn2 = view.findViewById(R.id.button2);
        btn3 = view.findViewById(R.id.button3);
        btn4 = view.findViewById(R.id.button4);
        btn5 = view.findViewById(R.id.button5);
        btn6 = view.findViewById(R.id.button6);
        btn7 = view.findViewById(R.id.button7);
        btn8 = view.findViewById(R.id.button8);
        btn9 = view.findViewById(R.id.button9);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn8.setOnClickListener(this);

        ImageButton btn_return = getActivity().findViewById(R.id.btn_return);
        btn_return.setVisibility(View.VISIBLE);

        TextView tvUserName = view.findViewById(R.id.tvUserName);
        tvUserName.setText(getUserName());

        builder = new StringBuilder();

        return view;
    }

    private String getUserName(){
        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences(AUTH_STATUS, MODE_PRIVATE);
        String userName = prefs.getString("userName", "none");
        return userName;
    }

    @Override
    public void onClick(View v) {
        //Создаем новое намерение для запуска сервиса использования вибрации
        Intent intentVibrate =new Intent(getContext(), VibrateService.class);
        getActivity().startService(intentVibrate);
        if (builder.toString().length() < 4){
            switch (v.getId()){
                case R.id.button:
                    builder.append( btn1.getText().toString() );
                    break;
                case R.id.button2:
                    builder.append( btn2.getText().toString() );
                    break;
                case R.id.button3:
                    builder.append( btn3.getText().toString() );
                    break;
                case R.id.button4:
                    builder.append( btn4.getText().toString() );
                    break;
                case R.id.button5:
                    builder.append( btn5.getText().toString() );
                    break;
                case R.id.button6:
                    builder.append( btn6.getText().toString() );
                    break;
                case R.id.button7:
                    builder.append( btn7.getText().toString() );
                    break;
                case R.id.button8:
                    builder.append( btn8.getText().toString() );
                    break;
                case R.id.button9:
                    builder.append( btn9.getText().toString() );
                    break;

            }
            if (builder.toString().length() == 4) {
                if (isRightPassword()){
                    Intent goToMainScreen = new Intent(getContext(), MainActivity.class);
                    startActivity(goToMainScreen);
                }else{
                    incorrectPasswornAlert();
                    getActivity().startService(intentVibrate);
                    getActivity().startService(intentVibrate);
                    builder.setLength(0);
                    return;
                }
            }
        }
        System.out.println("Текущий код: " + builder.toString());
    }

    private void incorrectPasswornAlert() {
        Toast.makeText(getContext(), "Неправильный пароль", Toast.LENGTH_SHORT).show();
    }

    private boolean isRightPassword(){
        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences(AUTH_STATUS, MODE_PRIVATE);
        int password = prefs.getInt("code", 0);

        if (builder.toString().equals(String.valueOf(password)))
            return true;

        return false;
    }
}