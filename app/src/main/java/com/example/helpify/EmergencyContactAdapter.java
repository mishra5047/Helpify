package com.example.helpify;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helpify.databinding.ItemEmergencyContactBinding;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class EmergencyContactAdapter extends RecyclerView.Adapter<EmergencyContactAdapter.ViewHolder> {

    private ArrayList<ItemEmergencyContact> list;
    private Context context;
    private ClickListener clickListener;

    public EmergencyContactAdapter(ArrayList<ItemEmergencyContact> list, Context context, ClickListener clickListener) {
        this.list = list;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(ItemEmergencyContactBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemEmergencyContact contact = list.get(position);

        holder.binding.namePerson.setText(contact.ContactName);
        holder.binding.relationPerson.setText(contact.Relation);

        holder.binding.callIcon.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + contact.Contact));
            context.startActivity(intent);
        });

        holder.binding.deleteIcon.setOnClickListener(view -> {
            clickListener.onClick(contact.Contact);
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemEmergencyContactBinding binding;

        public ViewHolder(ItemEmergencyContactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
