package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AllContactsActivity extends AppCompatActivity {

    DataBaseHelper dbhelper;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);
        RefreshList();
    }

    private void RefreshList() {
        dbhelper = new DataBaseHelper(this);
        listView = findViewById(R.id.listview);

//      Display data when activity starts
        ArrayList<ContactsList> contactsList = dbhelper.getAllContacts();
        CustomAdapter adapter = new CustomAdapter(AllContactsActivity.this, contactsList,dbhelper);
        listView.setAdapter(adapter);
    }
}
