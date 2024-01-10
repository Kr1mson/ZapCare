package com.example.medizap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Hospital_Signup extends AppCompatActivity {
    EditText Name, Email, Pswd, Repswd;
    String name, email, pswd, repswd;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_signup);
        Name = findViewById(R.id.name_edtxt);
        Email = findViewById(R.id.email_signup_edtxt);
        Pswd = findViewById(R.id.pswd_signup_edtxt);
        Repswd = findViewById(R.id.repswd_edtxt);
        create = findViewById(R.id.create_account_btn);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = Name.getText().toString();
                email = Email.getText().toString();
                pswd = Pswd.getText().toString();
                repswd = Repswd.getText().toString();
                String uniqueUsername = extractUsername(email);
                if(name.equals("")||email.equals("")||pswd.equals("")||repswd.equals("")){
                    Toast.makeText(Hospital_Signup.this, "Please Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pswd.length()==8) {
                        if (pswd.equals(repswd)) {
                            DatabaseReference h_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Hospital_Users");
                            h_signupHelper helper = new h_signupHelper(name, email, pswd);
                            h_ref.child(uniqueUsername).setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Name.setText("");
                                    Email.setText("");
                                    Pswd.setText("");
                                    Repswd.setText("");
                                    Toast.makeText(Hospital_Signup.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Hospital_Home.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        } else {
                            Toast.makeText(Hospital_Signup.this, "Passwords does't match", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Hospital_Signup.this,"Password must be atleast 8 characters long", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private String extractUsername(String email) {
        // Use substring to get the part before @gmail.com
        int atIndex = email.indexOf("@");

        if (atIndex != -1) {
            return email.substring(0, atIndex);
        } else {
            // Handle the case where the email doesn't contain @gmail.com
            return email;
        }
    }
}