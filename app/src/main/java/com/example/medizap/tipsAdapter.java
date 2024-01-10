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

import java.util.List;

public class tipsAdapter extends RecyclerView.Adapter<tipsAdapter.ViewHolder> {
    private List<String> facts;

    public tipsAdapter(List<String> facts) {
        this.facts = facts;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView factView;


        ViewHolder(View itemView) {
            super(itemView);
            factView = itemView.findViewById(R.id.tipstxt);

        }
    }

    @NonNull
    @Override
    public tipsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tipsAdapter.ViewHolder holder, int position) {
        String tip = facts.get(position);
        holder.factView.setText(tip);

    }

    @Override
    public int getItemCount() {
        return facts.size();
    }
}
