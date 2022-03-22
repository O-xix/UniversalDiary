package com.example.calendarappexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    public SQLiteDBHelper(Context context) {
        super(context, "EntryList.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table EntryList(title TEXT, text TEXT, comments TEXT, numComments INTEGER, pub INTEGER, date TEXT, time TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists EntryList");
    }

    public Boolean insertentry(String title, String text, String comments, int numComments, String date, String time, int pub) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("text", text);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("comments", comments);
        contentValues.put("numComments", numComments);
        contentValues.put("pub", pub);
        long result = DB.insert("EntryList", null, contentValues);

        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean updateentry(String title, String text, String comments, int numComments, String date, String time) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("comments", comments);
        contentValues.put("numComments", numComments);
        Cursor cursor = DB.rawQuery("Select * from EntryList where time = ?", new String[]{time});
        if (cursor.getCount() > 0) {
            long result = DB.update("EntryList", contentValues, "time=?", new String[]{time});
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

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from EntryList", null);
        return cursor;
    }

//    public Cursor getSavedData() {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from EntryList where ", null);
//        return cursor;
//    }

    public Cursor getPublishedData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from EntryList where pub = 1 ", null);
        return cursor;
    }
}
