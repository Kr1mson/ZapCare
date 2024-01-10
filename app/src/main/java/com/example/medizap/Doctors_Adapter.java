package com.example.medizap;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class Doctors_Adapter extends RecyclerView.Adapter<Doctors_Adapter.ViewHolder> {

    private List<Doctor> doctors;

    public Doctors_Adapter(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, hospitalTextView, specializationTextView, feeTextView, btimeTextView, etimeTextview;
        String name;
        FloatingActionButton delete;

        ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            hospitalTextView = itemView.findViewById(R.id.hospitalTextView);
            specializationTextView = itemView.findViewById(R.id.specializationTextView);
            feeTextView = itemView.findViewById(R.id.feeTextView);
            btimeTextView = itemView.findViewById(R.id.btimeTextView);
            etimeTextview = itemView.findViewById(R.id.etimeTextView);
            delete = itemView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteData();
                }
            });
        }
        private void deleteData(){
            String fullName = nameTextView.getText().toString();
            String doctorName = fullName.replace("Dr. ", "");
            DatabaseReference ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Doctor_Details").child(doctorName);

            ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(itemView.getContext(), "Record Removed Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(itemView.getContext(), "Remove operation failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);

        // Populate data into views
        holder.nameTextView.setText("Dr. " + doctor.getName());
        holder.hospitalTextView.setText(doctor.getHospital_name());
        holder.specializationTextView.setText(doctor.getSpecialization());
        holder.feeTextView.setText("$"+doctor.getFee()+" per session");
        holder.btimeTextView.setText(doctor.getBtime()+":00");
        holder.etimeTextview.setText(doctor.getEtime()+":00");
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }
}
