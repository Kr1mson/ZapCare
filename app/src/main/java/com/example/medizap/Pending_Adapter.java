package com.example.medizap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Pending_Adapter extends RecyclerView.Adapter<Pending_Adapter.ViewHolder>{
private List<Appointment_List> appointment_lists;
public Pending_Adapter(List<Appointment_List> appointment_lists) {
        this.appointment_lists = appointment_lists;
        }
static class ViewHolder extends RecyclerView.ViewHolder {
    TextView unameTextView,dnameTextView,dateTextView;

    ViewHolder(View itemView) {
        super(itemView);
        unameTextView=itemView.findViewById(R.id.unameTextView);
        dnameTextView = itemView.findViewById(R.id.dnameTextView);
        dateTextView=itemView.findViewById(R.id.dateTextView);

    }
}
    @NonNull
    @Override
    public Pending_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pending_Adapter.ViewHolder holder, int position) {
        Appointment_List appointment = appointment_lists.get(position);
        holder.unameTextView.setText(appointment.getUName());
        holder.dnameTextView.setText(appointment.getDName());
        holder.dateTextView.setText(appointment.getDate());
    }

    @Override
    public int getItemCount() {
        return appointment_lists.size();
    }
}


