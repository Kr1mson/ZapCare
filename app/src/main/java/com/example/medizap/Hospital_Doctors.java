package com.example.medizap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Hospital_Doctors extends Fragment {
    public Hospital_Doctors(){}
    View view;
    private RecyclerView recyclerViewDoctors;
    private Doctors_Adapter doctorsAdapter;
    private List<Doctor> doctorList;
    FloatingActionButton add;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hospital__doctors, container, false);
        recyclerViewDoctors = view.findViewById(R.id.recyclerViewDoctors);
        recyclerViewDoctors.setLayoutManager(new LinearLayoutManager(getContext()));
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
        doctorList.add(new Doctor("Dr. Smith", "Cardiologist", "$150"));
        doctorList.add(new Doctor("Dr. Johnson", "Orthopedic Surgeon", "$200"));
        doctorList.add(new Doctor("Dr. Williams", "Pediatrician", "$120"));

        doctorsAdapter = new Doctors_Adapter(doctorList);
        recyclerViewDoctors.setAdapter(doctorsAdapter);
        return view;
    }
}