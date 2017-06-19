package com.game.android.mahfuzcse11.studentnamestorageandroidcrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by MahfuzCSE'11 on 19-Jun-17.
 */

public class DbController extends SQLiteOpenHelper {


    public DbController(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Test.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT UNIQUE,LASTNAME TEXT UNIQUE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE  IF EXISTS  STUDENTS ");
        onCreate(db);

    }


    public void insertStudent(String firstName, String lastName) {


        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME", firstName);
        contentValues.put("LASTNAME", lastName);
        this.getWritableDatabase().insertOrThrow("STUDENTS", "", contentValues);

    }


    public void deleteStudent(String firstName) {
        this.getWritableDatabase().delete("STUDENTS", "FIRSTNAME= '" + firstName + "' ", null);
    }

    public void updateStudent(String oldFirstName, String newFirstName) {
        this.getWritableDatabase().execSQL("UPDATE  STUDENTS SET FIRSTNAME='" + newFirstName + "' WHERE FIRSTNAME='" + oldFirstName + "'");
    }

    public void listAllStudent(TextView textView) {

        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM  STUDENTS", null);


        textView.setText("");
        while (cursor.moveToNext()) {
            textView.append(cursor.getString(1) + "   " + cursor.getString(2) + "\n");
        }


    }


}
