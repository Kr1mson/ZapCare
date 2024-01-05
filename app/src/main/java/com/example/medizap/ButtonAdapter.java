package com.example.medizap;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

    private List<String> buttonList;
    private ButtonClickListener buttonClickListener;
    private int selectedItem = -1; // Track the selected item

    // Interface for the callback
    public interface ButtonClickListener {
        void onButtonClick(String date);
    }

    // Constructor to set the callback
    public ButtonAdapter(List<String> buttonList, ButtonClickListener listener) {
        this.buttonList = buttonList;
        this.buttonClickListener = listener;
    }

    public class ButtonViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.buttonItem);
        }
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        final String buttonText = buttonList.get(position);
        holder.button.setText(buttonText);

        // Set click listener for the button
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedItem = selectedItem; // Save the previous selected item
                selectedItem = holder.getAdapterPosition(); // Update the selected item

                // Notify the adapter that the item has changed to refresh the UI
                notifyItemChanged(previousSelectedItem);
                notifyItemChanged(selectedItem);

                if (buttonClickListener != null) {
                    buttonClickListener.onButtonClick(buttonText);
                }
            }
        });

        // Change the background color based on the selected state
        if (selectedItem == position) {
            holder.button.setBackgroundColor(Color.parseColor("#967BB6")); // Set your desired color
        } else {
            holder.button.setBackgroundColor(Color.parseColor("#AA98A9")); // Set your default color
        }
    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }
}
