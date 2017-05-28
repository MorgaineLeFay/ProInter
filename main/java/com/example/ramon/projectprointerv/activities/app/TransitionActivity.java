package com.example.ramon.projectprointerv.activities.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.ramon.projectprointerv.R;
import com.example.ramon.projectprointerv.activities.drive.DriveCreateFileActivity;
import com.example.ramon.projectprointerv.activities.drive.DriveOpenFileActivity;
import com.example.ramon.projectprointerv.activities.drive.DriveUpdateFiles;
import com.example.ramon.projectprointerv.activities.drive.DriveUserActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ramon on 14/05/2017.
 */

public class TransitionActivity  extends Activity {


    public static byte[] buffer0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.transiotion_scree_layout);
    }



    public void updateFileOnClick(View view){

        int CODE = 100;
        Intent intent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
        intent.putExtra("CONTENT_TYPE", "*/*");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(intent, CODE);

    }


    public void insertFileOnClick(View view){

        int CODE = 200;
        Intent intent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
        intent.putExtra("CONTENT_TYPE", "*/*");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(intent, CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            Uri uri =  data.getData();

            try {
                InputStream isStream = getContentResolver().openInputStream(uri);
                buffer0 = getBytes(isStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Intent intent = new Intent(this, DriveUpdateFiles.class);

            startActivity(intent);
        }

        if(requestCode == 200 && resultCode == RESULT_OK){
            Uri uri =  data.getData();

            try {
                InputStream isStream = getContentResolver().openInputStream(uri);
                buffer0 = getBytes(isStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Intent intent = new Intent(this, DriveCreateFileActivity.class);

            startActivity(intent);
        }
    }





    public void openFileOnClick(View view){
        Intent intent = new Intent(this, DriveOpenFileActivity.class);
        startActivity(intent);


    }




    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }




}












