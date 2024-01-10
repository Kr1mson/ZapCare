package com.example.medizap;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Hospital_Appointment extends Fragment {
    public Hospital_Appointment(){}
    View view;
    private RecyclerView recyclerViewAppointment;
    private RecyclerView recyclerViewPending;
    private Appointment_Adapter appointmentAdapter;
    private Pending_Adapter pendingAdapter;
    String uname, dname,date,month;
    private List<Appointment_List> appointmentList;
    private List<Pending_list> PendingList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hospital__appointment, container, false);
        recyclerViewPending = view.findViewById(R.id.pendingrecyclerView);
        recyclerViewAppointment = view.findViewById(R.id.appointrecyclerView);

        // Use different LayoutManagers for each RecyclerView
        LinearLayoutManager layoutManagerAppointment = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManagerPending = new LinearLayoutManager(getContext());

        recyclerViewAppointment.setLayoutManager(layoutManagerAppointment);
        recyclerViewPending.setLayoutManager(layoutManagerPending);

        appointmentList = new ArrayList<>();
        PendingList = new ArrayList<>();

        appointmentAdapter = new Appointment_Adapter(appointmentList,getContext());
        pendingAdapter = new Pending_Adapter(PendingList);

        recyclerViewAppointment.setAdapter(appointmentAdapter);
        recyclerViewPending.setAdapter(pendingAdapter);

        DatabaseReference ap_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Appointments");
        ap_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PendingList.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Book_Doc_Helper_DB bd = postSnapshot.getValue(Book_Doc_Helper_DB.class);
                    if (bd!= null) {
                        uname = bd.getUname();
                        dname = bd.getDname();
                        date = bd.getDate();
                        month = bd.getMonth();
                        Pending_list pendingList = new Pending_list(uname, date, dname,month);
                        PendingList.add(pendingList);
                    }
                    else{
                        Toast.makeText(getContext(),"Waiting for a request",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(),Hospital_Add_Doctor.class);
                        startActivity(intent);
                    }

                }
                pendingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}
