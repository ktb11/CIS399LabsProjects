package com.example.kyle.lab6v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TideItems tideItems;
    static final String DATE = "date";
    static final String DAY = "day";
    static final String HL = "hl";
    static final String TIME = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FileIO io = new FileIO(getApplicationContext());
        tideItems = io.readFile();

        // ** Start setting things up for the data adapter that will be used by the ListView **
        // This ArrayList of HashMaps will be the data source for the adapter
        ArrayList<HashMap<String, String>> data = new
                ArrayList<HashMap<String, String>>();

        // Put fields from each TideItem into a HashMap, put the HashMaps into the ArrayList
        // Keys match the column names in the SimpleAdapter
        for (TideItem item : tideItems){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(DATE, item.getDate());
            map.put(DAY, item.getDay());
            map.put(HL, item.getHighLow());
            map.put(TIME, item.getTime());
            data.add(map);
        }

        // Instantiate a data adapter using the ArrayList of HashMaps, data, as a data source
        // The String array contains the column names
        // The int array contains the ids of the widgets in each row of the ListView
        // The order of the elements associates column names with widget ids
        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.listview_item,
                new String[]{DATE,DAY,HL,TIME},
                new int[]{ R.id.dateTextView,
                        R.id.dayTextView,
                        R.id.hlTextView,
                        R.id.timeTextView,}
        );

        // Pass the data adapter to the List View
        ListView itemsListView = (ListView)findViewById(R.id.tidesListView);
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemClickListener(this);


    }

    // ** Event Handler **

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TideItem item = tideItems.get(position);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        double predValueCm = Double.parseDouble(item.getPredValueFt());
        predValueCm *= 30.48;
//        formatter.format(predValueCm);
        Toast.makeText(this,
                "Predicted heights"+ "\r\n" + item.getPredValueFt() + " feet." + "\r\n" + formatter.format(predValueCm) + " cm",
                Toast.LENGTH_LONG).show();
    }
}
