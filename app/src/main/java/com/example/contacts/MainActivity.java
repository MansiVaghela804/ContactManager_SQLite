package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dbHelper;
    Button serachContact,insertContact,updateContact,allContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serachContact = (Button)findViewById(R.id.serachContact);
        insertContact = (Button)findViewById(R.id.insertContact);
        updateContact = (Button)findViewById(R.id.updateContact);
        allContacts = (Button)findViewById(R.id.ContactBook);
        dbHelper = new DataBaseHelper(this);

        serachContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchContactActivity.class);
                startActivity(intent);
            }
        });
        insertContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNewContactsActivity.class);
                startActivity(intent);
            }
        });

        updateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UpdateContactActivity.class);
                startActivity(intent);
            }
        });
        allContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}
