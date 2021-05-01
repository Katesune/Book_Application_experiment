package com.example.bookapplication;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Parser {
    ArrayList<String> d;
    ArrayList<InputStream> docs;
    Context c;

    Parser(Context contex) {
        d = new ArrayList<String>();
        docs = new ArrayList<InputStream>();
        c = contex;
        addDocs();
    }


    private void addDocs() {
        docs.add(c.getResources().openRawResource(R.raw.main10));
        docs.add(c.getResources().openRawResource(R.raw.main13));
    }

    public ArrayList<String> getDefenition() throws IOException, SAXException, ParserConfigurationException {

        for (InputStream is: docs) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            doc = dBuilder.parse(is, "UTF-8");
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("span");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                try {
                    String style = nList.item(temp).getAttributes().getNamedItem("style").getNodeValue();
                    if (style.equals("font-weight:bold;font-style:italic;")) {
                        String value = nList.item(temp).getTextContent();
                        d.add(value);
                    }
                } catch (NullPointerException e) {
                }
            }
        }

        return d;
    }
}
