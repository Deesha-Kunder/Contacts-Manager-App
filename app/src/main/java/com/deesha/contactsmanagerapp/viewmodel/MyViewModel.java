package com.deesha.contactsmanagerapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.deesha.contactsmanagerapp.repository.Repository;
import com.deesha.contactsmanagerapp.roomdb.Contacts;

import java.util.List;

public class MyViewModel extends AndroidViewModel
{
    private Repository repository;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }
    public void addNewContacts(Contacts contacts){
        repository.addContacts(contacts);
    }
    public void deleteContact(Contacts contacts){
        repository.deleteContact(contacts);
    }
    public void updateContacts(Contacts contacts){
        repository.updateContacts(contacts);
    }

    public LiveData<List<Contacts>> getAllContacts()
    {
        return repository.getAllContacts();
    }
    public LiveData<Contacts>getContactByID(int contactID){
        return repository.getContactByID(contactID);
    }
}
