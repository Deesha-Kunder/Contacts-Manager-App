package com.deesha.contactsmanagerapp;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapters {
    @BindingAdapter("imageUri")
    public static void loadImage(ImageView view,String profilePath){
        if(profilePath!=null && !profilePath.isEmpty()){
            Log.d("bindingAdapter","loading :"+profilePath);
            Glide.with(view.getContext())
                    .load(Uri.parse(profilePath))
                    .placeholder(R.drawable.profile)
                    .error(R.drawable.profile)
                    .circleCrop()
                    .into(view);
        }else {
            view.setImageResource(R.drawable.profile);
        }
    }
}
