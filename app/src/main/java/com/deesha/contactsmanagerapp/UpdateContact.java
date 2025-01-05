package com.deesha.contactsmanagerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.deesha.contactsmanagerapp.databinding.ActivityUpdateContactBinding;
import com.deesha.contactsmanagerapp.roomdb.Contacts;
import com.deesha.contactsmanagerapp.viewmodel.MyViewModel;

public class UpdateContact extends AppCompatActivity {
 ActivityUpdateContactBinding binding;
 MyViewModel viewModel;
 int contactID;
 String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_update_contact);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        contactID = getIntent().getIntExtra("contactId",-1);
        if(contactID==-1){
            Toast.makeText(this, "Invalid Contact", Toast.LENGTH_SHORT).show();
            finish();
        }
        viewModel.getContactByID(contactID).observe(this, new Observer<Contacts>() {
            @Override
            public void onChanged(Contacts contacts) {
                if(contacts!=null){
                    binding.updateName.setText(contacts.getName());
                    binding.updateEmail.setText(contacts.getEmail());
                    binding.updatePhone.setText(contacts.getPhoneNumber());
                    imageUri =contacts.getProfilePath();
                    if(imageUri!=null){
                        Glide.with(UpdateContact.this)
                                .load(contacts.getProfilePath())
                                .error(R.drawable.profile)
                                .circleCrop()
                                .into(binding.updateImage);
                    }
                }
            }
        });
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedName=binding.updateName.getText().toString();
                String updatedPhoneNumber = binding.updatePhone.getText().toString();
                String updatedEmail = binding.updateEmail.getText().toString();
                String updatedImage = imageUri;

                Contacts updatedContacts = new Contacts(updatedName,updatedPhoneNumber,updatedEmail,updatedImage);
                updatedContacts.setId(contactID);
                viewModel.updateContacts(updatedContacts);
                finish();
            }
        });

    }
}


