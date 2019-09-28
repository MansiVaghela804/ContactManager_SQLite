package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME  = "Contact.db";
    public static final String TABLE_NAME = "Contacts";
    private static final int VERSION  = 1;
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MOBILE_NUMBER = "mobileNumber";
    public static final String COLUMN_EMAIL = "email";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_MOBILE_NUMBER + " INTEGER, " +
                    COLUMN_EMAIL + " TEXT " +
                    ")";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Create Database query
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public Boolean insertData(String name, String mobileNumber, String email){

//        Important line SqliteDatabse create
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_MOBILE_NUMBER,mobileNumber);
        contentValues.put(COLUMN_EMAIL,email);
        Log.e("Result",email);
        long result = db.insert(TABLE_NAME,null,contentValues);
//        db.close();
        if (result == -1){
            return true;
        }else {
            return false;
        }
    }
    public Cursor getData(String mobileNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
//      search query -->> select * from table_name "where mobile_number = '123456'
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE mobileNumber  = '" + mobileNumber + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public void deleteContact(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, COLUMN_MOBILE_NUMBER + " = " + mobileNumber,null);
//        db.close();
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID + " = '" + id +"'",null);
    }
    public void updateContact(String mobileNumber,String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_EMAIL,email);
        db.update(TABLE_NAME,contentValues,"mobileNumber = '" + mobileNumber +"'",null);
        db.close();
    }
    public ArrayList<ContactsList> getAllContacts(){
        ArrayList<ContactsList> notes = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ContactsList note = new ContactsList();
                note.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                note.setMobileNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE_NUMBER)));
                note.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();

        // return notes list
        return notes;

    }
}
