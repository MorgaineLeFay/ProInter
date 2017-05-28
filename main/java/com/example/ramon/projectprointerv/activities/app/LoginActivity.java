package com.example.ramon.projectprointerv.activities.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramon.projectprointerv.activities.drive.DriveUserActivity;
import com.example.ramon.projectprointerv.R;
import com.example.ramon.projectprointerv.services.IO.XmlHandler;
import com.example.ramon.projectprointerv.services.ValidateUsers;
import com.google.android.gms.drive.DriveContents;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Ramon on 06/05/2017.
 */

public class LoginActivity extends Activity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private TextView txtview;

    private SAXParser parser = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.index_info_layout);
    }




    public void onClickEntrarButton (View view ) throws ParserConfigurationException, SAXException {

        editTextLogin = (EditText) findViewById(R.id.login_edttxt_login);
        editTextPassword = (EditText) findViewById(R.id.login_edttxt_password);

        Editable loginEditable = editTextLogin.getText();
        Editable passwordEditable = editTextPassword.getText();
        String loginString = loginEditable.toString();
        String passwordString = passwordEditable.toString();

        SAXParserFactory spf = SAXParserFactory.newInstance();
        parser = spf.newSAXParser();
        XmlHandler xmlHandler = new XmlHandler();
        ValidateUsers validateUsers = new ValidateUsers(loginString, passwordString);

        try(InputStream inputStream = getAssets().open("users.xml")){
            parser.parse(inputStream , xmlHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }


       // if(validateUsers.validateUsers()){
            Toast.makeText(this, "Sucesso", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TransitionActivity.class);
            startActivity(intent);

       // }

    }





    public void onClickCadastroButton(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);


    }



    public void onClickOkButton(View view){
        setContentView(R.layout.login_layout);
    }
}
