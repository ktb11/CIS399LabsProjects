package com.example.kyle.lab6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TideSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tide.sqlite";
    private static final int DB_VERSION = 1;
    private Context context = null;

    public TideSQLiteHelper(Context c){
        super(c,DB_NAME,null,DB_VERSION);
        this.context = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE Item"
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Date TEXT,"
                + "City TEXT,"
                + "Day TEXT,"
                + "Time TEXT,"
                + "InFt TEXT,"
                + "HighLow TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db ,int oldVersion, int newVersion){
    //not implementing upgrade
    }
}
