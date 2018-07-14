package com.example.kyle.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TideItems tideItems;
    static final String DATE = "date";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileIO io = new FileIO(getApplicationContext());
        tideItems = io.readFile();

        // ** Start setting things up for the data adapter that will be used by the ListView **
        // This ArrayList of HashMaps will be the data source for the adapter
//        ArrayList<HashMap<String, String>> data = new
//                ArrayList<HashMap<String, String>>();
//
//        for (TideItem item : tideItems){
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put(DATE, item.getTideDateFormatted());
//            data.add(map);
//        }
//
//        SimpleAdapter adapter = new SimpleAdapter(this,
//                data,
//                R.layout.listview_item,
//                new String[]{DATE},
//                new int[]{
//                        R.id.dateTextView,
//                }
//        );
//
//        ListView itemsListView = (ListView)findViewById(R.id.tidesListView);
//        itemsListView.setAdapter(adapter);
//        itemsListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TideItem item = tideItems.get(position);
//        Toast.makeText(this,
//                "Low: " + item.getLowTemp() + "\r\n" +
//                        "High:" + item.getHighTemp(),
//                Toast.LENGTH_LONG).show();
    }
}
