package com.example.taketest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
//DB helper class
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Result.db";
    public static final String TABLE_NAME = "results";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    //Create table for storing data
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+TABLE_NAME +
                        " (id integer primary key, score integer)"
        );
    }
    //drops table if exists already
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    //insert the test result to db
    public boolean insertResult (int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("score", score);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }
    //query db for score values
    public ArrayList<Integer> getAllResults() {
        ArrayList<Integer> array_list = new ArrayList<Integer>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getInt(res.getColumnIndex("score")));
            res.moveToNext();
        }
        return array_list;
    }

}
