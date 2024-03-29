package com.example.medizap;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;


public class BookAppointment extends Fragment implements Button_Adapter_Date.ButtonClickListener  {
    Button jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec,lastPressedButton;

    public BookAppointment(){}
    View view;
    private RecyclerView docrecyclerView;
    private book_doc_adapter doctorsAdapter;
    private List<appointmentBookingHelper> doctorList;
    public static String selectedMonth,Date;
    FloatingActionButton back;
    Button_Adapter_Date buttonAdapter;
    private TextView pkddate;
    String name, hname, dept,fee;
    private List<String> buttonList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        view= inflater.inflate(R.layout.fragment_book_appointment, container, false);
        jan=view.findViewById(R.id.Jan);
        feb=view.findViewById(R.id.Feb);
        mar=view.findViewById(R.id.Mar);
        apr=view.findViewById(R.id.Apr);
        may=view.findViewById(R.id.May);
        jun=view.findViewById(R.id.Jun);
        jul=view.findViewById(R.id.Jul);
        aug=view.findViewById(R.id.Aug);
        sep=view.findViewById(R.id.Sep);
        oct=view.findViewById(R.id.Oct);
        nov=view.findViewById(R.id.Nov);
        dec=view.findViewById(R.id.Dec);
        pkddate=view.findViewById(R.id.DatePicker);
        back=view.findViewById(R.id.back);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.flFragment, new Appointment());
                fr.commit();
            }
        });






        jan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(jan,31,jan.getText().toString());
            }
        });
        feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(feb,28,feb.getText().toString());
            }
        });
        mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(mar,31,mar.getText().toString());
            }
        });
        apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(apr,30,apr.getText().toString());
            }
        });
        may.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(may,31,may.getText().toString());
            }
        });
        jun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(jun,30,jun.getText().toString());
            }
        });
        jul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(jul,31,jul.getText().toString());
            }
        });
        aug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(aug,31,aug.getText().toString());
            }
        });
        sep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(sep,30,sep.getText().toString());
            }
        });
        oct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(oct,31,oct.getText().toString());
            }
        });
        nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(nov,30,nov.getText().toString());
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(dec,31,dec.getText().toString());
            }
        });

        buttonAdapter = new Button_Adapter_Date(buttonList,this::onButtonClick);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(buttonAdapter);



        return view;
    }
    private void handleButtonClick(Button button, int days, String month) {
        // Clear the previous days
        buttonList.clear();

        // Restore the color of the previously pressed button
        if (lastPressedButton != null) {
            lastPressedButton.setBackgroundColor(Color.parseColor("#CBC3E3")); // Set your default color
        }

        // Change the color of the current pressed button
        button.setBackgroundColor(Color.parseColor("#967BB6")); // Set your desired color

        // Add days to the buttonList
        for (int i = 1; i <= days; i++) {
            buttonList.add(String.valueOf(i));
        }

        // Update the reference to the last pressed button
        lastPressedButton = button;

        // Notify the adapter that the data set has changed
        buttonAdapter.notifyDataSetChanged();

        // Store the selected month
        selectedMonth = month;
    }

    // Implement the callback method from ButtonAdapter.ButtonClickListener
    @Override
    public void onButtonClick(String date) {
        // Handle the selected date here
        if (pkddate != null) {
            Date=date;
            pkddate.setText("Selected Date: "+date + " " + selectedMonth);
            docrecyclerView = view.findViewById(R.id.docrecyclerView);
            docrecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            doctorList = new ArrayList<>();
            doctorsAdapter = new book_doc_adapter(doctorList);
            docrecyclerView.setAdapter(doctorsAdapter);
            DatabaseReference dbref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Doctor_Details");
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
                            appointmentBookingHelper appointmentBookingHelper= new appointmentBookingHelper(name, hname, dept, fee);
                            doctorList.add(appointmentBookingHelper);
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

        }

    }


}