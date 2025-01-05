package com.deesha.contactsmanagerapp.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class},version =2)
public abstract class ContactDatabase extends RoomDatabase {
    //connection between Room database & ContactsDAO
    public abstract ContactsDAO getContactDAO();
    //single pattern
    private static ContactDatabase dbInstance;
    public static synchronized ContactDatabase getDbInstance(Context context){
        if(dbInstance==null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"contact_db").
                    fallbackToDestructiveMigration().build();
        }
        return dbInstance;
    }
}
