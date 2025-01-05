package com.deesha.contactsmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deesha.contactsmanagerapp.adapter.MyContactAdapter;
import com.deesha.contactsmanagerapp.databinding.ActivityMainBinding;
import com.deesha.contactsmanagerapp.roomdb.ContactDatabase;
import com.deesha.contactsmanagerapp.roomdb.Contacts;
import com.deesha.contactsmanagerapp.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //data source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList=new ArrayList<>();
    //adapter
    private MyContactAdapter adapter;

    //binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler mainActivityClickHandler;


    MyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityClickHandler = new MainActivityClickHandler(this);

        mainBinding.setClickHandler(mainActivityClickHandler);

        //recyclerView
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //database
        contactDatabase = ContactDatabase.getDbInstance(this);
        //ViewModel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                contactsArrayList.clear();
                contactsArrayList.addAll(contacts);
                adapter.notifyDataSetChanged();
            }
        });

        //adapter
        adapter = new MyContactAdapter(contactsArrayList, new MyContactAdapter.OnContactLongClickListener() {
            @Override
            public void onContactLongClick(Contacts contacts) {
                ShowDialogBox(contacts);
            }
        });

        //set adapter
        recyclerView.setAdapter(adapter);
        //swipe to delete
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                        //action to be performed
//                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());
//                viewModel.deleteContact(c);
//            }
//        }).attachToRecyclerView(recyclerView);
    }

    private void ShowDialogBox(Contacts contacts) {
        new AlertDialog.Builder(this)
                .setTitle("Chose action")
                .setMessage("Do you want to Delete or Update this Contact?")
                .setPositiveButton("Update",(dialog,which)->{

                    Intent intent =new Intent(this,UpdateContact.class);
                    intent.putExtra("contactId",contacts.getId());
                    startActivity(intent);
                })
                .setNegativeButton("delete",(dialog,which)->{
                    viewModel.deleteContact(contacts);
                })
                .setNeutralButton("Cancel",(dialog,which)->{
                    dialog.dismiss();
                })
                .create()
                .show();
    }
}