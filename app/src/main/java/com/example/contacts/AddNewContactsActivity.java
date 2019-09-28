package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewContactsActivity extends AppCompatActivity {

    EditText name,mobileNumber,email;
    Button save;
    private DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contacts);

        dbHelper = new DataBaseHelper(this);
        name =(EditText)findViewById(R.id.edittxtName);
        mobileNumber =(EditText)findViewById(R.id.edittxtMobileNumber);
        email =(EditText)findViewById(R.id.edittxtEmail);
        save = (Button)findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbHelper.insertData(name.getText().toString(),
                        mobileNumber.getText().toString(),
                        email.getText().toString());
                if (result == false){
                    Toast.makeText(getApplicationContext(), "Data Insertd Sucessfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Data Not Insertd", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
