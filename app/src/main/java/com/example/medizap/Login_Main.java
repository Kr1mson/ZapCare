package com.example.medizap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login_Main extends AppCompatActivity {

    EditText Email,Pswd,Name;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.login_main);
        super.onCreate(savedInstanceState);

        login=findViewById(R.id.login_btn);
        Name=findViewById(R.id.nam_edtxt);
        Pswd=findViewById(R.id.pswd_edtxt);
        SharedPreferences preferences = getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = preferences.getString("username", null);
        String token = preferences.getString("pswd", null);

        if (username != null && token != null) {
            Toast.makeText(Login_Main.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=Name.getText().toString();
                String pswd=Pswd.getText().toString();
                if(name.isEmpty()||pswd.isEmpty()){
                    Toast.makeText(Login_Main.this,"Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference user_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("User_Data");
                    DatabaseReference h_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Hospital_Users");

                    Query checkUser = user_ref.orderByChild("name").equalTo(name);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Your user login logic here
                                String passfromdb = snapshot.child(name).child("pswd").getValue(String.class);
                                if (passfromdb.equals(pswd)) {
                                    Toast.makeText(Login_Main.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                    SharedPreferences preferences = getSharedPreferences("user_credentials", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("username",name );
                                    editor.putString("pswd",pswd );
                                    editor.apply();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                } else if (pswd.equals("admin")) {
                                    Intent i3 = new Intent(getApplicationContext(), Agency_Signup.class);
                                    startActivity(i3);
                                    finish();
                                } else {
                                    Toast.makeText(Login_Main.this, "Wrong Password/Name", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Login_Main.this, "User Not Found Please Signup", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle the error if the retrieval is canceled
                        }
                    });
                }
            }
        });

        }
    public void onSignupClick(View view) {
        Intent i_signup=new Intent(getApplicationContext(),Signup.class);
        startActivity(i_signup);
    }
    public void onRegisterClick(View view) {
        Intent i_register=new Intent(getApplicationContext(),Hospital_Signup.class);
        startActivity(i_register);
    }
}