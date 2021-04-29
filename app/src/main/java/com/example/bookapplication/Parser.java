package com.example.bookapplication;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Parser {
    ArrayList<Node> d;
    public ArrayList<Node> getDefenition(Context c) throws IOException, SAXException, ParserConfigurationException {

        DefaultHandler handler = new DefaultHandler();
        InputStream is = c.getResources().openRawResource(R.raw.astronomy);

        //File inputFile = new File("/sdcard/folioreader/astronomy/astronomy.epub");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(is,"UTF-8");

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("title");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            d.add(nList.item(temp));
            Log.d("mytag", nList.item(temp).getNodeName());
        }

        return d;
    }
}
