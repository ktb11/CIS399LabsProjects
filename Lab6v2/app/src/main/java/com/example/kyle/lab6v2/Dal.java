package com.example.kyle.lab6v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Dal {
    private Context context = null;
    public Dal(Context context){this.context = context;}

//    public void loadTestData()
//    {
//
//        // Initialize database
//        TideSQLiteHelper helper = new TideSQLiteHelper(context);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        // load the database with test data if it isn't already loaded
//
//        loadDb("floranceTides");	// Florance
//
//        db.close();
//    }

    public void loadDb(String city) {

        String fileName = city +".xml";
        TideItems items;
        TideSQLiteHelper helper = new TideSQLiteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // florance data
        items = parseXml(fileName);
        for (TideItem item : items){
            cv.put("Date", item.getDate());
            cv.put("Day",item.getDay());
            cv.put("City","Florance");
            cv.put("Time",item.getTime());
            cv.put("InFt",item.getPredValueFt());
            cv.put("HighLow",item.getHighLow());
            db.insert("Item",null,cv);
        }
        db.close();
    }

    public Cursor getItemFromDb(String location, String date)
    {

        // Initialize the database
        TideSQLiteHelper helper = new TideSQLiteHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        // Get a weather forecast for one location
        String query = "SELECT * FROM Item WHERE City = ? AND Date = ? ORDER BY Date ASC";
        String[] variables = new String[]{location, date};
        return db.rawQuery(query,variables);
    }

    public TideItems parseXml(String xmlData){

        try{
            // get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            //set content handler
            ParseHandler handler = new ParseHandler();
            xmlreader.setContentHandler(handler);

            // read the file from internal storage
            InputStream in = context.getAssets().open(xmlData);

            // parse the data
            InputSource is = new InputSource(in);
            xmlreader.parse(is);

            // set the feed in the activity
            TideItems items = handler.getItems();
            return items;

        }
        catch (Exception e){
            return  null;
        }
    }
}
