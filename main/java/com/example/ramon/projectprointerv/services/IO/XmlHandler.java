package com.example.ramon.projectprointerv.services.IO;

import android.util.Log;

import com.example.ramon.projectprointerv.R;
import com.example.ramon.projectprointerv.UserEntity;
import com.example.ramon.projectprointerv.factories.FactoryLogin;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Ramon on 07/05/2017.
 */

public class XmlHandler extends DefaultHandler {

    private UserEntity userEntity = FactoryLogin.getSingleInstanceUser();

    private StringBuilder email, password, idDriverRootFolder;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        Log.i("s", "Iniciou o documento");

    }





    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if(qName.equals("email")){
            email = new StringBuilder();
        }else
            if(qName.equals("password")){
                password = new StringBuilder();
            }else
                if(qName.equals("idFolder")){
                    idDriverRootFolder = new StringBuilder();
                }else{
                    email = new StringBuilder();
                    password = new StringBuilder();
                    idDriverRootFolder = new StringBuilder();
                }

    }





    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("email")){
            userEntity.setEmail(email.toString());
        }else
        if(qName.equals("password")){
            userEntity.setPassword(password.toString());
        }else
        if(qName.equals("idFolder")){
            userEntity.setIdDriveRootFolder(idDriverRootFolder.toString());
        }
    }






    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        email.append(ch, start, length);
        password.append(ch, start, length);
        idDriverRootFolder.append(ch, start, length);

    }






    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

        Log.i("s", "finalizou o documento" + userEntity.toString());

    }
}
