package com.example.kyle.lab6;

import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;

public class SecondActivity extends Activity {
    private Dal dal = new Dal(this);
    Cursor cursor = null;
    SimpleCursorAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        Intent intent = getIntent();
        String date = intent.getExtras().getString("date");
        String location = intent.getExtras().getString("location");

        //get date depend on date and location
        cursor = dal.getItemFromDb(location,date);

        //display data in listview
        adapter = new SimpleCursorAdapter(this,
                R.layout.listview_item,
                cursor,
                new String[]{"Date","Day","Time","InFt","HighLow"},
                new int[]{
                        R.id.dateTextView,
                        R.id.dayTextView,
                        R.id.timeTextView,
                        R.id.heightTextView,
                        R.id.hlTextView},

                0 );

        ListView itemsListView =(ListView)findViewById(R.id.listviewId);
        itemsListView.setAdapter(adapter);
        //itemsListView.setOnItemClickListener(this);



    }


}
