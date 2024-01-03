package com.example.medizap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    Button signup;
    EditText Name, Email, Password, RePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=findViewById(R.id.create_account_btn);
        Name=findViewById(R.id.name_edtxt);
        Email=findViewById(R.id.email_signup_edtxt);
        Password=findViewById(R.id.pswd_signup_edtxt);
        RePassword=findViewById(R.id.repswd_edtxt);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Signup.this, "Signup clicked", Toast.LENGTH_SHORT).show();
                String name=Name.getText().toString();
                String email=Email.getText().toString();
                String pswd=Password.getText().toString();
                String repswd=RePassword.getText().toString();
            }
        });
    }
    public void onLoginClick(View view) {
        Toast.makeText(this, "Login Clicked", Toast.LENGTH_SHORT).show();
        Intent i_signup=new Intent(getApplicationContext(),Login_Main.class);
        startActivity(i_signup);
    }
}