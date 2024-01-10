package com.example.medizap;

import static com.example.medizap.BookAppointment.Date;
import static com.example.medizap.BookAppointment.selectedMonth;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.List;


public class book_doc_adapter extends RecyclerView.Adapter<book_doc_adapter.ViewHolder> {


    private List<appointmentBookingHelper> doctors;
    private Context context;
    public book_doc_adapter(List<appointmentBookingHelper> doctors) {
        this.doctors = doctors;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, hospitalTextView, specializationTextView, feeTextView,waitTextView;
        String name, hospital,fee, dept, wait;
        Button schedule;

        ViewHolder(View itemView) {
            super(itemView);


            nameTextView = itemView.findViewById(R.id.nameTextView);
            hospitalTextView = itemView.findViewById(R.id.hospitalTextView);
            specializationTextView = itemView.findViewById(R.id.specializationTextView);
            feeTextView = itemView.findViewById(R.id.feeTextView);
            waitTextView = itemView.findViewById(R.id.waitView);
            schedule=itemView.findViewById(R.id.schedule);
            schedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference user_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("User_Data");


                    user_ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                //String patient_name = snapshot.child("name").getValue(String.class);
                                String patient_name=Login_Main.sharedname;
                                name = nameTextView.getText().toString();
                                hospital = hospitalTextView.getText().toString();
                                dept = specializationTextView.getText().toString();
                                fee = feeTextView.getText().toString();
                                String date = Date.toString();
                                String month = selectedMonth.toString();
                                if(name.equals("")||hospital.equals("")||dept.equals("")||fee.equals("")||date.equals("")||month.equals("")){
                                    Toast.makeText(itemView.getContext(), "There was a problem scheduling an appointment", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Book_Doc_Helper_DB bookDocHelperDb = new Book_Doc_Helper_DB(patient_name,name,hospital,dept,fee,date,month);
                                    DatabaseReference appointment_ref= FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Appointments");
                                    appointment_ref.child(patient_name).setValue(bookDocHelperDb).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(itemView.getContext(), "New appointment Requested", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                            } else{
                                Toast.makeText(itemView.getContext(), "Cannot fetch values", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });
        }
    }
    @NonNull
    @Override
    public book_doc_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_doc_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull book_doc_adapter.ViewHolder holder, int position) {
        appointmentBookingHelper doctor = doctors.get(position);

        // Populate data into views
        holder.nameTextView.setText("Dr. " + doctor.getName());
        holder.hospitalTextView.setText(doctor.getHospital_name());
        holder.specializationTextView.setText(doctor.getSpecialization());
        holder.feeTextView.setText("$"+doctor.getFee()+" per session");

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

}
