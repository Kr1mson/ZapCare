package com.example.medizap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Pending_Adapter extends RecyclerView.Adapter<Pending_Adapter.ViewHolder> {
    private List<Pending_list> pending_lists;


    public Pending_Adapter(List<Pending_list> pending_lists) {
        this.pending_lists = pending_lists;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView unameTextView, dnameTextView, dateTextView,monthTextView;
        ImageButton Approve, Reject;

        ViewHolder(View itemView) {
            super(itemView);
            unameTextView = itemView.findViewById(R.id.unameTextView);
            dnameTextView = itemView.findViewById(R.id.dnameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            monthTextView = itemView.findViewById(R.id.monthTextView);
            Approve = itemView.findViewById(R.id.approve);
            Reject = itemView.findViewById(R.id.reject);



            // Initialize your buttons here

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
        Pending_list pending = pending_lists.get(position);
        holder.unameTextView.setText(pending.getUname());
        holder.dnameTextView.setText(pending.getDname());
        holder.dateTextView.setText("Date: "+pending.getDate());
        holder.monthTextView.setText("Month: "+pending.getMonth());
        String dname = pending.getDname().toString();
        String date_ = pending.getDate().toString();
        String doctor = removeDrAndExtraSpace(dname);
        DatabaseReference ap_ref = FirebaseDatabase.getInstance("https://medizap-a8ae7-default-rtdb.firebaseio.com/").getReference("Appointments");

        holder.Approve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Approved", Toast.LENGTH_SHORT).show();
                ap_ref.child(Login_Main.sharedname+doctor+date_).child("approval").setValue("Approved");
            }
        });
        holder.Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Rejected", Toast.LENGTH_SHORT).show();
                ap_ref.child(Login_Main.sharedname+doctor+date_).child("approval").setValue("Rejected");

            }
        });
    }
    private static String removeDrAndExtraSpace(String input) {
        // Replace "Dr." with an empty string and trim leading/trailing spaces
        return input.replace("Dr.", "").trim();
    }

    @Override
    public int getItemCount() {
        return pending_lists.size();
    }
}
