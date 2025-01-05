package com.deesha.contactsmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.deesha.contactsmanagerapp.roomdb.Contacts;
import com.deesha.contactsmanagerapp.viewmodel.MyViewModel;

public class AddNewContactClickHandler {
    Context context;
    Contacts contacts;
    MyViewModel viewModel;
    public AddNewContactClickHandler(Context context,Contacts contacts,MyViewModel viewModel){
        this.context = context;
        this.contacts = contacts;
        this.viewModel = viewModel;
    }
    public void saveOnClick(View v){
        if(contacts.getName()==null||contacts.getPhoneNumber()==null || contacts.getEmail()==null) {
            Log.d("savebuttonclicked",""+contacts.getName()+ ":"+contacts.getEmail()+":"+contacts.getPhoneNumber());
            Toast.makeText(context, "Fields can't be empty", Toast.LENGTH_SHORT).show();
        }else {
            Intent i = new Intent(context, MainActivity.class);

            Contacts c = new Contacts(contacts.getName(),contacts.getPhoneNumber(),contacts.getEmail(),contacts.getProfilePath());
            viewModel.addNewContacts(c);

            context.startActivity(i);
        }
    }

}
