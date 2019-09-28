package com.example.contacts;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {

    private ArrayList<ContactsList> users;
    private LayoutInflater layoutInflater;
    Activity ctx;
    Context context;
    DataBaseHelper dbHelper;

    public CustomAdapter(Activity ctx, ArrayList<ContactsList> users,DataBaseHelper dbHelper) {
        this.ctx = ctx;
        this.users = users;
        this.dbHelper = dbHelper;

    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = ctx.getLayoutInflater().inflate(R.layout.row_single,null);
        TextView name = view.findViewById(R.id.textViewName);
        TextView phone = view.findViewById(R.id.textViewMobileNumber);
        TextView email = view.findViewById(R.id.textViewEmail);
        final Button delete = view.findViewById(R.id.buttonDelete);
        final Button update = view.findViewById(R.id.buttonUpdate);
        final ContactsList list = users.get(position);

        Log.e("list.getId()",""+list.getId());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteContact(Integer.parseInt(list.getId()));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = ctx.getLayoutInflater().inflate(R.layout.activity_update_contact,null);
                EditText updateName = view.findViewById(R.id.edittxtUpdateName);
                EditText updateMobileNumber = view.findViewById(R.id.edittxtUpdateMobileNumber);
                EditText updateEmail = view.findViewById(R.id.edittxtUpdateEmail);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);
                dbHelper.updateContact(updateMobileNumber.getText().toString(),updateName.getText().toString(),updateEmail.getText().toString());
            }
        });
        String username = list.getName();
        String userphone = list.getMobileNumber();
        String useremail = list.getEmail();

        name.setText(username);
        phone.setText(userphone);
        email.setText(useremail);

        return view;
    }
}
