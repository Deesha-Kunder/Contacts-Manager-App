package com.deesha.contactsmanagerapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.Manifest;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.deesha.contactsmanagerapp.databinding.ActivityAddNewContactBinding;
import com.deesha.contactsmanagerapp.roomdb.Contacts;
import com.deesha.contactsmanagerapp.viewmodel.MyViewModel;

public class AddNewContact extends AppCompatActivity {
private ActivityAddNewContactBinding binding;
private AddNewContactClickHandler handler;
private ProfileClickHandler profileClickHandler;
private static final int ImagePicker_ReqCode=1;
private Contacts contacts;
private static final int REQUEST_PERMISSION =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // If permission is not granted, request permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        }

        contacts= new Contacts();
        contacts.setProfilePath(Uri.parse("android.resource://"+getPackageName()+"/"+R.drawable.profile).toString());
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);
        handler = new AddNewContactClickHandler(this,contacts,viewModel);
        profileClickHandler = new ProfileClickHandler(this);

        binding.setClickhandler(handler);
        binding.setProfileClickHandler(profileClickHandler);
        binding.setContacts(contacts);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ImagePicker_ReqCode && resultCode==RESULT_OK && data!=null&& data.getData()!=null){
            Uri selectedImage = data.getData();
            Log.d("selected image",""+selectedImage);
            if(selectedImage!=null){

                Log.d("profilepath :","image "+selectedImage.toString());
                contacts.setProfilePath(selectedImage.toString());
                binding.setContacts(contacts);
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check if the request code matches the one you used to request permission
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with the image picker
                Log.d("AddNewContact", "Permission granted for external storage.");
            } else {
                // Permission denied, handle accordingly
                Log.e("AddNewContact", "Permission denied for external storage.");
                Toast.makeText(this, "Permission denied. Unable to pick an image.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}