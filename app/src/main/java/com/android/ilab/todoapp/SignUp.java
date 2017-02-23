package com.android.ilab.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    EditText username ;
    EditText email ;
    EditText password ;
    EditText password1 ;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email_id);
        password = (EditText) findViewById(R.id.password1);
        password1 = (EditText) findViewById(R.id.password2);
        Button sign = (Button) findViewById(R.id.sign_up_button);

//        boolean pass = true;
//
//        if(username.length()< 5 || username.length()>10)
//            username.setError("Username must not be less than 5 characters nor greater than 10");
//            pass = false ;
//        if(password.getText() != password1.getEditableText())
//            password1.setError("This does not match with the above password");


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

}
