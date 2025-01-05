package com.deesha.contactsmanagerapp;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;


public class ProfileClickHandler {
    Activity activity;
    public ProfileClickHandler(Activity activity){
        this.activity = activity;
    }
    public void OnClickPhoto(View v){
       Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       intent.setType("image/*");
       activity.startActivityForResult(intent,1);
    }
}
