package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContactActivity extends AppCompatActivity {

    DataBaseHelper dbHelper;
    EditText updateMobileNumber,updateName,updateEmail;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        dbHelper = new DataBaseHelper(this);
        updateMobileNumber = (EditText)findViewById(R.id.edittxtUpdateMobileNumber);
        updateName = (EditText)findViewById(R.id.edittxtUpdateName);
        updateEmail = (EditText)findViewById(R.id.edittxtUpdateEmail);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateContact(updateMobileNumber.getText().toString(),updateName.getText().toString(),updateEmail.getText().toString());
                Toast.makeText(UpdateContactActivity.this, "Data Updated Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
