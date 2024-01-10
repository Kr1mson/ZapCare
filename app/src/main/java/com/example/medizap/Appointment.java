package com.example.medizap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Appointment extends Fragment {
    public Appointment(){}
    View view;
    private RecyclerView recyclerViewAppointment;
    private Appointment_Adapter appointmentAdapter;
    private List<Appointment_List> appointmentList;
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
        Appointment_List list1 = new Appointment_List("Uname","Dname", "hname", "dept", "fee","date","time");
        appointmentList.add(list1);
        Appointment_List list2 = new Appointment_List("Uname","Dname", "hname", "dept", "fee","date","time");
        appointmentList.add(list2);
        Appointment_List list3 = new Appointment_List("Uname","Dname", "hname", "dept", "fee","date","time");
        appointmentList.add(list3);
        Appointment_List list4 = new Appointment_List("Uname","Dname", "hname", "dept", "fee","date","time");
        appointmentList.add(list4);

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
