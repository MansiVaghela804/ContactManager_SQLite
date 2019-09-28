package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContactActivity extends AppCompatActivity {

    DataBaseHelper dbHelper;
    EditText searchContact;
    Button search;
    TextView name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        dbHelper = new DataBaseHelper(this);
        searchContact = (EditText)findViewById(R.id.SerachMobileNumber);
        search = (Button)(Button)findViewById(R.id.btnSerach);
        name = (TextView)findViewById(R.id.txtviewName);
        email = (TextView)findViewById(R.id.txtviewEmail);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getData(searchContact.getText().toString());

                try {
                    if (cursor.moveToNext()){
                        Toast.makeText(SearchContactActivity.this, "Show Data Sucessfully", Toast.LENGTH_SHORT).show();
                        name.setText(cursor.getString(1));
                        email.setText(cursor.getString(3));
                    }else {
                        Toast.makeText(SearchContactActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }finally {
                    cursor.close();
                }
            }
        });
    }
}
