package com.example.kyle.lab5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseHandler extends DefaultHandler {
    private TideItems tideItems;
    private TideItem item;

    public TideItems getItems() { return tideItems; }

    @Override
    public void startDocument() throws SAXException {
        tideItems = new TideItems();
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {

        if (qName.equals("item")) {
            item = new TideItem();
//            item.setDate(atts.getValue(0));
        }
        else if (qName.equals("date")) {
            item.setDate(atts);
        }
        else if (qName.equals("day")) {
            item.setDay(localName);
        }
        else if (qName.equals("time")) {
            item.setTime(atts.getValue(0));
        }
        else if (qName.equals("pred")) {
            item.setPredValue(atts.getValue(0));
        }
        else if (qName.equals("highlow")) {
            item.setHighLow(atts.getValue(0));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName,
                           String qName) throws SAXException
    {
        if (qName.equals("item")) {
            tideItems.add(item);
        }
    }
}
