package com.example.medizap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Hospital_Appointment extends Fragment {
    public Hospital_Appointment(){}
    View view;
    private RecyclerView recyclerViewAppointment;
    private RecyclerView recyclerViewPending;
    private Appointment_Adapter appointmentAdapter;
    private Pending_Adapter pendingAdapter;
    private List<Appointment_List> appointmentList, pendingList;

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
        pendingList = new ArrayList<>();

        appointmentAdapter = new Appointment_Adapter(appointmentList);
        pendingAdapter = new Pending_Adapter(pendingList);

        recyclerViewAppointment.setAdapter(appointmentAdapter);
        recyclerViewPending.setAdapter(pendingAdapter);

        Appointment_List list1 = new Appointment_List("Uname", "Dname", "hname", "dept", "fee", "date", "time");
        appointmentList.add(list1);
        Appointment_List list2 = new Appointment_List("Uname", "Dname", "hname", "dept", "fee", "date", "time");
        appointmentList.add(list2);
        Appointment_List list3 = new Appointment_List("Uname", "Dname", "hname", "dept", "fee", "date", "time");
        appointmentList.add(list3);
        Appointment_List list4 = new Appointment_List("Uname", "Dname", "hname", "dept", "fee", "date", "time");
        appointmentList.add(list4);

        Appointment_List list5 = new Appointment_List("Uname", "Dname", "hname", "dept", "fee", "date", "time");
        pendingList.add(list5);

        return view;
    }
}
