package com.example.navigationson;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Login_Activity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        setContentView(R.layout.login_activity);

        LoginForm loginForm = new LoginForm();
        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.linearLogin, loginForm).commit();





    }
}
