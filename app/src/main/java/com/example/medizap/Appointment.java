package com.example.medizap;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Appointment extends Fragment {
    public Appointment(){}
    View view;
    private RecyclerView recyclerViewAppointment;
    private Appointment_Adapter appointmentAdapter;
    private List<Appointment_List> appointmentList;
    String uname, dname, hname, dept, fee,date,month,approval;
    FloatingActionButton booknew;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_appointment, container, false);
        booknew=view.findViewById(R.id.booknew);
        recyclerViewAppointment = view.findViewById(R.id.appointrecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAppointment.setLayoutManager(layoutManager);
        appointmentList = new ArrayList<>();
        appointmentAdapter = new Appointment_Adapter(appointmentList,getContext());
        recyclerViewAppointment.setAdapter(appointmentAdapter);
        DatabaseReference ap_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Final appointments");

        ap_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appointmentList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Book_Doc_Helper_DB bd = postSnapshot.getValue(Book_Doc_Helper_DB.class);
                    if (bd!= null) {
                        uname = bd.getUname();
                        dname = bd.getDname();
                        hname = bd.getHname();
                        dept = bd.getDept();
                        fee = bd.getFee();
                        date = bd.getDate();
                        month = bd.getMonth();
                        approval = bd.getApproval();

                        Appointment_List appointments = new Appointment_List(uname,dname,hname,dept,fee,date,month,approval);
                        appointmentList.add(appointments);
                    }
                    else{
                        Toast.makeText(getContext(),"Please add a doctor first",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(),Hospital_Add_Doctor.class);
                        startActivity(intent);
                    }
                }

                appointmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        booknew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.flFragment, new BookAppointment());
                fr.commit();
            }
        });
        return view;
    }
}
