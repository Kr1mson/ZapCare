package com.example.medizap;

import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Main extends AppCompatActivity {

    EditText Email,Pswd;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.login_main);
        super.onCreate(savedInstanceState);
        login=findViewById(R.id.login_btn);
        Email=findViewById(R.id.email_edtxt);
        Pswd=findViewById(R.id.pswd_edtxt);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login_Main.this, "Login Clicked", Toast.LENGTH_SHORT).show();
                String email=Email.getText().toString();
                String pswd=Pswd.getText().toString();
            }
        });

        }
    public void onSignupClick(View view) {
        Toast.makeText(this, "Signup Clicked", Toast.LENGTH_SHORT).show();
        Intent i_signup=new Intent(getApplicationContext(),Signup.class);
        startActivity(i_signup);






        }

}