package com.example.medizap;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Appointment_Adapter extends RecyclerView.Adapter<Appointment_Adapter.ViewHolder> {
    private List<Appointment_List> appointment_lists;
    private Context context;

    public Appointment_Adapter(List<Appointment_List> appointment_lists, Context context) {
        this.appointment_lists = appointment_lists;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView unameTextView, dnameTextView, hospitalTextView, specializationTextView, feeTextView, dateTextView, timeTextView;
        ImageView cancel;
        GestureDetector gestureDetector;

        ViewHolder(View itemView) {
            super(itemView);
            unameTextView = itemView.findViewById(R.id.unameTextView);
            dnameTextView = itemView.findViewById(R.id.dnameTextView);
            hospitalTextView = itemView.findViewById(R.id.hospitalTextView);
            specializationTextView = itemView.findViewById(R.id.specializationTextView);
            feeTextView = itemView.findViewById(R.id.feeTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            cancel = itemView.findViewById(R.id.cancel);

            gestureDetector = new GestureDetector(itemView.getContext(), new GestureListener());
            cancel.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    gestureDetector.onTouchEvent(event);
                    return true;
                }
            });
        }
    }

    @NonNull
    @Override
    public Appointment_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Appointment_Adapter.ViewHolder holder, int position) {
        Appointment_List appointment = appointment_lists.get(position);

        // Populate data into views
        holder.unameTextView.setText(appointment.getUName());
        holder.dnameTextView.setText(appointment.getDName());
        holder.hospitalTextView.setText(appointment.getHname());
        holder.specializationTextView.setText(appointment.getDept());
        holder.feeTextView.setText("$" + appointment.getFee() + " per session");
        holder.dateTextView.setText(appointment.getDate());
        holder.timeTextView.setText(appointment.getTime());
    }

    @Override
    public int getItemCount() {
        return appointment_lists.size();
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // Handle single tap
            Toast.makeText(context, "Hold to Delete", Toast.LENGTH_SHORT).show();
            // Change the text or perform any action for a single tap
            // imageView.setText("Single Tap Text");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // Handle long press
            Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
            // Change the text or perform any action for a long press
            // imageView.setText("Long Press Text");
        }
    }
}
