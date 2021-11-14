package com.example.helpify.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.helpify.ClickListener;
import com.example.helpify.DataClass;
import com.example.helpify.EmergencyContactAdapter;
import com.example.helpify.ItemEmergencyContact;
import com.example.helpify.R;
import com.example.helpify.databinding.AddContactNumberDialogBinding;
import com.example.helpify.databinding.FragmentHomeBinding;
import com.example.helpify.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements ClickListener {

    private FragmentHomeBinding binding;
    SharedPreferences preferences;
    int contactCount = Integer.MAX_VALUE;
    DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferences = getContext().getSharedPreferences("Helpify", MODE_PRIVATE);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getSosContact();
            }
        }, 200);

        binding.addSOSContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contactCount == 3){
                    Toast.makeText(requireContext(), "Limit of 3 Contacts Reached", Toast.LENGTH_SHORT).show();
                    return;
                }

                // dialog to add an emergency contact
                AddContactNumberDialogBinding binding = AddContactNumberDialogBinding.inflate(LayoutInflater.from(requireContext()));
                //initialize alert builder.
                AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());

                //set our custom alert dialog to tha alertdialog builder
                alert.setView(binding.getRoot());

                final AlertDialog dialog = alert.create();

                binding.addContactButton.setOnClickListener(view1 -> {
                    String name = binding.contactNameEnter.getText().toString();
                    String relation = binding.relationEnter.getText().toString();
                    String contact = binding.mobileNoEnter.getText().toString();

                    ItemEmergencyContact contactItem = new ItemEmergencyContact(contact, name, relation);
                    reference.child("Emergency_Contact").child(contact).setValue(contactItem);
                    dialog.dismiss();
                    getSosContact();
                });

                dialog.show();
            }
        });

        binding.ambulanceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "102"));
                startActivity(intent);
            }
        });

        binding.fireImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "101"));
                startActivity(intent);
            }
        });

        binding.policeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "100"));
                startActivity(intent);
            }
        });

        binding.sosImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Sending Message to your Contacts", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    void getSosContact(){

        String number = getFromSharedPreference("number");

        if(number.equals("")){
            Toast.makeText(requireContext(), "Number Invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        reference = FirebaseDatabase.getInstance("https://helpify-f91f8-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference(number);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataClass dataClass = dataSnapshot.getValue(DataClass.class);

                contactCount = (int) dataSnapshot.child("Emergency_Contact").getChildrenCount();

                ArrayList<ItemEmergencyContact> contacts = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.child("Emergency_Contact").getChildren()){
                    ItemEmergencyContact contact = snapshot.getValue(ItemEmergencyContact.class);
                    contacts.add(contact);
                }

                setUI(contacts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    String getFromSharedPreference(String key){
        return preferences.getString(key, "");
    }

    void setUI(ArrayList<ItemEmergencyContact> list){

        if(list.isEmpty()){
            Toast.makeText(getContext(), "No Emergency Contact Added", Toast.LENGTH_SHORT).show();
            binding.recyclerEmergency.setVisibility(View.GONE);
            return;
        }
        binding.recyclerEmergency.setVisibility(View.VISIBLE);
        EmergencyContactAdapter adapter = new EmergencyContactAdapter(list, getContext(), this);
        binding.recyclerEmergency.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        binding.recyclerEmergency.setAdapter(adapter);
    }

    @Override
    public void onClick(String contact) {
        reference.child("Emergency_Contact").child(contact).removeValue();
        getSosContact();
    }
}