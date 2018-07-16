package com.example.kyle.lab6;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseHandler extends DefaultHandler {
    private TideItems tideItems;
    private TideItem item;

    // booleans to check if data has been read.
    private boolean isDate = false;
    private boolean isDay = false;
    private boolean isTime = false;
    private boolean isPredValueFt = false;
    private boolean isHighLow = false;

    public TideItems getItems() { return tideItems; }

    @Override
    public void startDocument() throws SAXException {
        tideItems = new TideItems();
        item = new TideItem();
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {

        if (qName.equals("item")) {
            item = new TideItem();
            return;
        }
        else if (qName.equals("date")) {
            isDate = true;
            return;
        }
        else if (qName.equals("day")) {
            isDay = true;
            return;
        }
        else if (qName.equals("time")) {
            isTime = true;
            return;
        }
        else if (qName.equals("pred")) {
            isPredValueFt = true;
            return;
        }
        else if (qName.equals("highlow")) {
            isHighLow = true;
            return;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName,
                           String qName) throws SAXException
    {
        if (qName.equals("item")) {
            tideItems.add(item);
        }
        return;
    }

    @Override
    public void characters(char ch[], int start , int length){
        String s = new String(ch, start,length);
        if (isDate){
            item.setDate(s);
            isDate = false;
        }
        else if (isDay){
            item.setDay(s);
            isDay = false;
        }
        else if (isTime){
            item.setTime(s);
            isTime = false;
        }
        else if (isPredValueFt){
            item.setPredValueFt(s);
            isPredValueFt = false;
        }
        else if (isHighLow){
            item.setHighLow(s);
            isHighLow = false;
        }
    }
}
