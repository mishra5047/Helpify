package com.example.helpify;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helpify.databinding.ItemEmergencyContactBinding;
import com.example.helpify.databinding.ItemNearbyPlacesBinding;

import java.util.ArrayList;

public class NearbyPlacesAdapter extends RecyclerView.Adapter<NearbyPlacesAdapter.ViewHolder> {

    private ArrayList<ItemNearbyPlace> list;
    private Context context;

    public NearbyPlacesAdapter(ArrayList<ItemNearbyPlace> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(ItemNearbyPlacesBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemNearbyPlace contact = list.get(position);

        holder.binding.nameHospital.setText(contact.name);
        holder.binding.addressHospital.setText(contact.address);

        holder.binding.callIcon.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + contact.phoneNumber));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemNearbyPlacesBinding binding;

        public ViewHolder(ItemNearbyPlacesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
