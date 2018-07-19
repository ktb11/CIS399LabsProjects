package com.example.kyle.beerme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Beer>{

    private LayoutInflater mInflater;
    private ArrayList<Beer> beers;
    private int mView;

    public ListViewAdapter(Context c, int textViewResourceID, ArrayList<Beer> beers){
        super(c,textViewResourceID,beers);
        this.beers = beers;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = textViewResourceID;
    }

    public View getView(int position, View convertVeiw, ViewGroup parents){
        convertVeiw = mInflater.inflate(mView,null);

        Beer beer = beers.get(position);

        if(beer != null){
            TextView beerName = (TextView) convertVeiw.findViewById(R.id.textBeerName);
            TextView breweryName = (TextView) convertVeiw.findViewById(R.id.textBreweryName);
            TextView date = (TextView) convertVeiw.findViewById(R.id.textDate);


            if(beerName != null){
                beerName.setText(beer.getBeerName());
            }
            if(breweryName != null){
                breweryName.setText(beer.getBreweryName());
            }
            if(date != null){
                date.setText(beer.getDate());
            }
        }
        return convertVeiw;
    }
}
