package com.deesha.contactsmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandler {
    Context context;
    public MainActivityClickHandler(Context context){
        this.context=context;
    }
    public void onFABClicked(View v){

        Intent i = new Intent(context.getApplicationContext(),AddNewContact.class);
        context.startActivity(i);
    }
}
