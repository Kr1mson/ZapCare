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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Hospital_Appointment extends Fragment implements Pending_Adapter.ApprovalListener{
    public Hospital_Appointment(){}
    View view;
    private RecyclerView recyclerViewAppointment;
    private RecyclerView recyclerViewPending;
    private Appointment_Adapter appointmentAdapter;
    private Pending_Adapter pendingAdapter;
    String uname, dname, hname, dept, fee,date,month, approval;
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
        pendingAdapter = new Pending_Adapter(PendingList,this);

        recyclerViewAppointment.setAdapter(appointmentAdapter);
        recyclerViewPending.setAdapter(pendingAdapter);
        DatabaseReference final_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Final appointments");
        DatabaseReference ap_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Appointments");
        final_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appointmentList.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Book_Doc_Helper_DB bd = postSnapshot.getValue(Book_Doc_Helper_DB.class);
                    if (bd!= null) {
                        uname = bd.getUname();
                        dname = bd.getDname();
                        hname = bd.getHname();
                        dept = bd.getDept();
                        fee= bd.getFee();
                        date = bd.getDate();
                        month = bd.getMonth();
                        approval = "approved";
                        Appointment_List ap = new Appointment_List(uname,dname,hname,dept,fee, date, month,approval);
                        appointmentList.add(ap);

                    }
                    else{
                        //Toast.makeText(getContext(),"Appointment added",Toast.LENGTH_SHORT).show();
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
                        approval = bd.getApproval();
                        Pending_list pendingList = new Pending_list(uname, date, dname, month);
                        PendingList.add(pendingList);
                    }

                }
                pendingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public void onApproveClicked(Pending_list pendingItem){
        PendingList.remove(pendingItem);
        PendingList.clear();
        DatabaseReference ap_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Appointments");
        ap_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PendingList.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Book_Doc_Helper_DB bd = postSnapshot.getValue(Book_Doc_Helper_DB.class);
                    if(bd!= null) {
                        uname = bd.getUname();
                        dname = bd.getDname();
                        hname = bd.getHname();
                        dept = bd.getDept();
                        fee = bd.getFee();
                        date = bd.getDate();
                        month = bd.getMonth();
                        approval = bd.getApproval();
                        Appointment_List appointmentList1 = new Appointment_List(uname, dname, hname, dept, fee, date, month, approval);
                        appointmentList.add(appointmentList1);
                        DatabaseReference s_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Final appointments");
                        if(!dname.equals("")) {
                            String fin_doc = removeDrAndExtraSpace(dname);

                            s_ref.child(uname + fin_doc + date).setValue(appointmentList1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        s_ref.child(uname + fin_doc + date).child("approval").setValue("approved");
                                        //Toast.makeText(getContext(), "Added to your appointments", Toast.LENGTH_SHORT).show();
                                        deleteData(uname, dname, fin_doc, date);

                                    } else {
                                        Toast.makeText(getContext(), "there was a problem", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            continue;
                        }

                    }

                }
                appointmentAdapter.notifyDataSetChanged();
                PendingList.remove(pendingItem);
                pendingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void onRejectClicked(Pending_list pendingItem){
        DatabaseReference ap_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Appointments");
        ap_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PendingList.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Book_Doc_Helper_DB bd = postSnapshot.getValue(Book_Doc_Helper_DB.class);
                    if(bd!= null) {
                        uname = bd.getUname();
                        dname = bd.getDname();
                        hname = bd.getHname();
                        dept = bd.getDept();
                        fee = bd.getFee();
                        date = bd.getDate();
                        month = bd.getMonth();
                        approval = "rejected";
                        Appointment_List appointmentList2 = new Appointment_List(uname, dname, hname, dept, fee, date, month, approval);
                        //appointmentList.add(appointmentList2);
                        DatabaseReference s_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Final appointments");
                        String fin_doc = removeDrAndExtraSpace(dname);
                        s_ref.child(uname+fin_doc+date).setValue(appointmentList2).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    s_ref.child(uname+fin_doc+date).child("approval").setValue("rejected");
                                    Toast.makeText(getContext(), "Rejected", Toast.LENGTH_SHORT).show();
                                    deleteData(uname,dname,fin_doc,date);
                                }
                                else{
                                    Toast.makeText(getContext(), "there was a problem", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                }
                appointmentAdapter.notifyDataSetChanged();
                PendingList.remove(pendingItem);
                pendingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        appointmentAdapter.notifyDataSetChanged();

        PendingList.remove(pendingItem);
        pendingAdapter.notifyDataSetChanged();
    }
    private static String removeDrAndExtraSpace(String input) {
        // Replace "Dr." with an empty string and trim leading/trailing spaces
        return input.replace("Dr.", "").trim();
    }
    private void deleteData(String uname,String dname, String fin_doc, String date){
        fin_doc = removeDrAndExtraSpace(dname);
        DatabaseReference ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Appointments").child(uname+fin_doc+date);

        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(getContext(), "Record Removed Successfully from pending appointments", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(getContext(), "Remove operation failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
