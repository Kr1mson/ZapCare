package com.example.medizap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class Hospital_Add_Doctor extends Fragment {


    View view;
    FirebaseDatabase hdb;
    DatabaseReference dref;
    DatabaseReference href;
    EditText Fname,Dept,Fee,Hname,Btime,Etime;
    Button save;
    FloatingActionButton back;
    public static boolean containsSpecialCharacters(String input) {
        // Define a string containing allowed characters (alphanumeric and space)
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

        // Check if the input contains any characters outside the allowed set
        for (char c : input.toCharArray()) {
            if (allowedCharacters.indexOf(c) == -1) {
                return true; // Special character found
            }
        }

        return false; // No special characters found
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hospital__add__doctor, container, false);
        save=view.findViewById(R.id.save);
        Fname=view.findViewById(R.id.fname_edtxt);
        Hname=view.findViewById(R.id.hname_edtxt);
        Dept=view.findViewById(R.id.dept_edtxt);
        Fee=view.findViewById(R.id.fee_edtxt);
        Btime=view.findViewById(R.id.startTime_edtxt);
        Etime=view.findViewById(R.id.endTime_edtxt);
        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.flFragment, new Hospital_Doctors());
                fr.commit();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=Fname.getText().toString();
                String hname=Hname.getText().toString();
                String dept=Dept.getText().toString();
                String fee=Fee.getText().toString();
                String btime=Btime.getText().toString();
                String etime=Etime.getText().toString();
                if(fname.equals("")||hname.equals("")||dept.equals("")||fee.equals("")||btime.equals("")||etime.equals("")){
                    Toast.makeText(getContext(),"Please fill all the fields",Toast.LENGTH_LONG).show();
                }
                else{
                    Doctor_Helper doctorHelper = new Doctor_Helper(fname, hname,dept,fee,btime,etime);
                    hdb= FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/");
                    href = hdb.getReference("Hospital_Details");
                    dref = hdb.getReference("Doctor_Details");
                    if(containsSpecialCharacters(fname)){
                        Toast.makeText(getContext(),"Special Characters like ( . @ # $ % ^ & * ) are not allowed in name",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dref.child(fname).setValue(doctorHelper).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Fname.setText("");
                                Hname.setText("");
                                Dept.setText("");
                                Fee.setText("");
                                Btime.setText("");
                                Etime.setText("");
                                Toast.makeText(getContext(), "Doctor registered", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
        return view;
    }
}