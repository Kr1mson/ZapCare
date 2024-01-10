package com.example.medizap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Hospital_Doctors extends Fragment {
    public Hospital_Doctors(){}
    View view;
    private RecyclerView recyclerViewDoctors;
    DatabaseReference dbref;
    private Doctors_Adapter doctorsAdapter;
    private List<Doctor> doctorList;
    FloatingActionButton add;
    String name, hname, dept,fee,btime,etime;
    Button delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hospital__doctors, container, false);
        recyclerViewDoctors = view.findViewById(R.id.recyclerViewDoctors);
        recyclerViewDoctors.setLayoutManager(new GridLayoutManager(getContext(), 2));
        dbref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Doctor_Details");
        add=view.findViewById(R.id.addnew);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.flFragment, new Hospital_Add_Doctor());
                fr.commit();
            }
        });

        // Dummy data
        doctorList = new ArrayList<>();
        doctorsAdapter = new Doctors_Adapter(doctorList);
        recyclerViewDoctors.setAdapter(doctorsAdapter);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doctorList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Doctor_Helper doc = postSnapshot.getValue(Doctor_Helper.class);
                    if (doc!= null) {
                        name = doc.getName();
                        hname = doc.getHname();
                        dept = doc.getDept();
                        fee = doc.getFee();
                        btime = doc.getBtime();
                        etime = doc.getEtime();
                        Doctor doctor = new Doctor(name, hname, dept, fee,btime,etime);
                        doctorList.add(doctor);
                    }
                    else{
                        Toast.makeText(getContext(),"Please add a doctor first",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(),Hospital_Add_Doctor.class);
                        startActivity(intent);
                    }
                }

                doctorsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}