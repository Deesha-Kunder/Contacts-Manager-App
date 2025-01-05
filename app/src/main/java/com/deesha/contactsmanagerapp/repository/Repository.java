package com.deesha.contactsmanagerapp.repository;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.deesha.contactsmanagerapp.roomdb.ContactDatabase;
import com.deesha.contactsmanagerapp.roomdb.Contacts;
import com.deesha.contactsmanagerapp.roomdb.ContactsDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    //Available data sources Room database!
    private final ContactsDAO contactsDAO;

    //executing database operation background
    ExecutorService executor;
    //updating UI
    Handler handler;

    public Repository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getDbInstance(application);
        this.contactsDAO = contactDatabase.getContactDAO();

        //run database operation in the background
        executor = Executors.newSingleThreadExecutor();
        //update UI
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContacts(Contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDAO.insert(contacts);
            }
        });
    }
    public void deleteContact(Contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDAO.delete(contacts);
            }
        });
    }
    public void updateContacts(Contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDAO.update(contacts);
            }
        });
    }
    public LiveData<List<Contacts>> getAllContacts(){

        return contactsDAO.getAllContacts();
    }
    public LiveData<Contacts>getContactByID(int ContactId){
        return contactsDAO.getContactByID(ContactId);
    }
}
