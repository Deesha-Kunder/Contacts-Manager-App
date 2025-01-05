package com.deesha.contactsmanagerapp.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deesha.contactsmanagerapp.R;
import com.deesha.contactsmanagerapp.databinding.ContactListItemBinding;
import com.deesha.contactsmanagerapp.roomdb.Contacts;

import java.util.ArrayList;

public class MyContactAdapter extends RecyclerView.Adapter<MyContactAdapter.MyViewHolder> {
    ArrayList<Contacts>contacts;
    OnContactLongClickListener clickListener;

    public MyContactAdapter(ArrayList<Contacts>contacts,OnContactLongClickListener clickListener){
        this.contacts=contacts;
        this.clickListener= clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contact_list_item,
                parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contacts currentContact = contacts.get(position);
        holder.binding.setContacts(currentContact);

        Glide.with(holder.binding.image.getContext())
                .load(Uri.parse(currentContact.getProfilePath()))
                .into(holder.binding.image);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickListener.onContactLongClick(currentContact);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(contacts!=null){
            return contacts.size();
        }else{
            return 0;
        }
    }
    public void setContacts(ArrayList<Contacts>contacts){
        this.contacts=contacts;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ContactListItemBinding binding;

        public MyViewHolder( ContactListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface OnContactLongClickListener{
        void onContactLongClick(Contacts contacts);
    }
}
