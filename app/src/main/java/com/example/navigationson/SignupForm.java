package com.example.navigationson;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupForm extends Fragment {


    Context context;


    public SignupForm(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        final View v = inflater.inflate(R.layout.fragment_signup_form, container, false);
        Button register = v.findViewById(R.id.register);
       // Button cancel = v.findViewById(R.id.cancel);

        final EditText user = (EditText)v.findViewById(R.id.user);
        final EditText email = (EditText)v.findViewById(R.id.email);
        final EditText password = (EditText)v.findViewById(R.id.password);
        final EditText confirm = (EditText)v.findViewById(R.id.confirm);






        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                if (user.length() == 0) {
                    user.setError("cant be empty");
                } else if (email.length() == 0) {
                    email.setError("cant be empty");
                } else if (password.length() == 0) {
                    password.setError("cant be empty");
                } else if (confirm.length() == 0) {
                    confirm.setError("cant be empty");

                } else if (!password.getText().toString().equals(confirm.getText().toString())){
                    confirm.setError("password not matching");

                } else if (!validateEmail(email.getText().toString())){
                    email.setError("lutfen gecerli bir adres girin");



                }



//
//                if(user.getText().toString().isEmpty()){
//                    Toast.makeText(context, "username bos olamaz", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//                else if(email.getText().toString().isEmpty()){
//                    Toast.makeText(context, "email bos olamaz", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//                else if(password.getText().toString().isEmpty()){
//                    Toast.makeText(context, "password secmeye ihtiyacın var", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//                else if(confirm.getText().toString().isEmpty()){
//                    Toast.makeText(context, "confirm secmeye ihtiyacın var", Toast.LENGTH_SHORT).show();
//                    return;
//
//
//                }
//
////                else if (!password.equals(confirm)) {
////
////                        Toast.makeText(context, "password not matching",Toast.LENGTH_SHORT).show();
////
////
////                }
//


                else {

                    LoginEvent loginEvent = new LoginEvent(user.getText().toString(), email.getText().toString(), password.getText().toString(), confirm.getText().toString());

                    EventRoomDatabase.getInstance(context).eventDao().insertRegister(loginEvent);
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.linearLogin, new LoginForm()).addToBackStack(null).commit();


                }

            }
//


            protected boolean validateEmail(String email){

                String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                        "\\@" +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                        "(" +

                        "\\." +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                        ")+";

                Pattern pattern = Pattern.compile(emailPattern);
                Matcher matcher = pattern.matcher(email);

                return matcher.matches();
            }


        });


        return v;
    }


}


