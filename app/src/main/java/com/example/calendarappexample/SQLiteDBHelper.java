package com.example.calendarappexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDate;
import java.time.LocalTime;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    public SQLiteDBHelper(Context context) {
        super(context, "EntryList.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table EntryList(title TEXT primary key, text TEXT, date TEXT, time TEXT, comments TEXT, numComments INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists EntryList");
    }

    public Boolean insertentry(String title, String text, String date, String time, String comments, int numComments) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("text", text);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("comments", comments);
        contentValues.put("numComments", numComments);
        long result = DB.insert("EntryList", null, contentValues);

        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean updateentry(String title, String text, String date, String time, String comments, int numComments) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("comments", comments);
        contentValues.put("numComments", numComments);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where title = ?", new String[]{title});
        if (cursor.getCount() > 0) {
            long result = DB.update("EntryList", contentValues, "title=?", new String[]{title});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getdata () {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
