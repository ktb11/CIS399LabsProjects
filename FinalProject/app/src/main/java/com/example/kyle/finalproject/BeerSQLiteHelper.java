package com.example.kyle.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BeerSQLiteHelper extends SQLiteOpenHelper{

    private static final String TAG = "BeerSQLiteHelper";

    public static final String DATABASE_NAME = "beers.db";
    private static final String TABLE_NAME ="beerTable";
    private static final String COL1 = "ID";
    private static final String COL2 = "BEER";
    private static final String COL3 = "BREWERY";
    private static final String COL4 = "TYPE";
    private static final String COL5 = "COST";
    private static final String COL6 = "RATING";
    private static final String COL7 = "DATE";

    private static final int DATABASE_VERSION =1;
    //private Context context = null;

    public BeerSQLiteHelper(Context c){
        super(c,TABLE_NAME,null,DATABASE_VERSION);
        //this.context = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //create a new database
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT,"
                + COL3 + " TEXT,"
                + COL4 + " TEXT,"
                + COL5 + " REAL,"
                + COL6 + " INTEGER,"
                + COL7 + " TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addBeerName(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


}
