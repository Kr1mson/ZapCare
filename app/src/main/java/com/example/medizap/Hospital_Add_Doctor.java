package com.example.medizap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class Hospital_Add_Doctor extends Fragment {
    public Hospital_Add_Doctor(){}
    View view;
    EditText Fname,Dept,Fee,Lname;
    Button save;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hospital__add__doctor, container, false);
        save=view.findViewById(R.id.save);
        Fname=view.findViewById(R.id.fname_edtxt);
        Lname=view.findViewById(R.id.lname_edtxt);
        Dept=view.findViewById(R.id.dept_edtxt);
        Fee=view.findViewById(R.id.fee_edtxt);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=Fname.getText().toString();
                String lname=Lname.getText().toString();
                String name=fname+lname;
                String dept=Dept.getText().toString();
                String fee=Fee.getText().toString();
                Toast.makeText(getContext(), "Registered!!!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}