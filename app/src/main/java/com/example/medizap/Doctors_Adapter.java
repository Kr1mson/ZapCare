package com.example.medizap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Doctors_Adapter extends RecyclerView.Adapter<Doctors_Adapter.ViewHolder> {

    private List<Doctor> doctors;

    public Doctors_Adapter(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, specializationTextView, feeTextView;

        ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            specializationTextView = itemView.findViewById(R.id.specializationTextView);
            feeTextView = itemView.findViewById(R.id.feeTextView);
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
        holder.nameTextView.setText(doctor.getName());
        holder.specializationTextView.setText(doctor.getSpecialization());
        holder.feeTextView.setText(doctor.getFee());
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }
}
