package com.example.ramon.projectprointerv.services.IO;

import android.content.res.AssetManager;

import com.example.ramon.projectprointerv.R;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Ramon on 07/05/2017.
 */

public class XmlIO {

    SAXParser parser = null;
    private InputStream inputStream;

    public XmlIO (InputStream inputStream){
        this.inputStream = inputStream;
    }


    public void testandoSaxAPI() throws IOException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();


        try {
            this.parser = saxParserFactory.newSAXParser();
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }


        //InputStream inputStream = assetManager.open("users.xml");
        XmlHandler handler = new XmlHandler();

        try {
            parser.parse(inputStream, handler );
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }





}













