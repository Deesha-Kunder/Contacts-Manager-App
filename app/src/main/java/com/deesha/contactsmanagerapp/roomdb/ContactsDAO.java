package com.deesha.contactsmanagerapp.roomdb;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
//it defines the set of method for performing db operation
public interface ContactsDAO {
    @Insert
    void insert(Contacts contacts);
    @Delete
    void delete(Contacts contacts);
    @Update
    void update(Contacts contacts);

    @Query("SELECT * FROM Contacts_table")
    LiveData<List<Contacts>> getAllContacts();

    @Query("SELECT * FROM Contacts_table WHERE id =:contactId")
    LiveData<Contacts>getContactByID(int contactId);

}
