package com.example.navigationson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginForm extends Fragment {

    private static final String PRES_NAME = "sharedPreference" ;
    private static String passwordd;
    EventRoomDatabase eventRoomDatabase;
    public static String user;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Boolean check;




    public  LoginForm(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){


        View v = inflater.inflate(R.layout.fragment_login_form, container, false);

        final Button login = v.findViewById(R.id.buttonlogin);
        final Button register = v.findViewById(R.id.buttonregister);

        final CheckBox checkBox = (CheckBox)v.findViewById(R.id.checkBox);


        final EditText username = (EditText)v.findViewById(R.id.enterusername);
        final EditText password = (EditText)v.findViewById(R.id.enterpassword);

        eventRoomDatabase = EventRoomDatabase.getInstance(getContext());


       // checkSharedPreferences();
        sharedPreferences = getActivity().getSharedPreferences(PRES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        check = sharedPreferences.getBoolean("check", false);


        if (check == true){

            username.setText(sharedPreferences.getString("username",null));
            password.setText(sharedPreferences.getString("password",null));
            checkBox.setChecked(true);

        }





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    user = username.getText().toString();
                    passwordd = password.getText().toString();


                    if (checkBox.isChecked()) {
                        editor.putBoolean("check", true);
                        editor.putString("username", user);
                        editor.putString("password", passwordd);
                        editor.commit();
                    } else {
                        editor.clear();
                        editor.commit();
                    }



                LoginEvent loginEvent = EventRoomDatabase.getInstance(getContext()).eventDao().isTrue(username.getText().toString(), password.getText().toString());



                if (loginEvent != null){

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);


                }
                else {
                    Toast.makeText(getActivity(), "invalid username or password", Toast.LENGTH_LONG).show();

                }

            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager= getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLogin , new SignupForm()).addToBackStack(null).commit();
            }
        });



        return v;

    }


    }



