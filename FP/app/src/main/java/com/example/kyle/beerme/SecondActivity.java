package com.example.kyle.beerme;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<Beer> beerList;
    ListView listView;
    Beer beer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        myDB = new DatabaseHelper(this);

        beerList =  new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();

        if(numRows == 0){
            Toast.makeText(SecondActivity.this, "DB empty",Toast.LENGTH_LONG).show();
        }else{

            while(data.moveToNext()){
                beer = new Beer(data.getString(1), data.getString(2), data.getString(3));
                beerList.add(beer);

            }
            ListViewAdapter adapter = new ListViewAdapter(this, R.layout.listview_adapter,beerList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }
}
