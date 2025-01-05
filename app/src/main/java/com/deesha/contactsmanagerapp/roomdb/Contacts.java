package com.deesha.contactsmanagerapp.roomdb;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Contacts_table")
public class Contacts  implements Serializable{
    // to rename column         @ColumnInfo(name = "Contact_id")
    @PrimaryKey(autoGenerate = true)
    private  int id;

    private String name;
    private String phoneNumber;
    private String email;
    private String profilePath;

    public Contacts(String name, String phoneNumber, String email, String profilePath) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profilePath = profilePath;
    }
    public Contacts(){

    }
    public void setId(int id) {
        this.id = id;
    }

    public String getProfilePath() {
        return profilePath;
    }


    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
