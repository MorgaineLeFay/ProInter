package com.example.ramon.projectprointerv.activities.drive;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.ramon.projectprointerv.activities.app.TransitionActivity;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.OpenFileActivityBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by Ramon on 14/05/2017.
 */

public class DriveCreateFileActivity extends DriveUserActivity {

    private static final String TAG = "CreateFileWithCreatorActivity";

    protected static final int REQUEST_CODE_CREATOR = 1;
    private Intent intent;

    @Override
    public void onConnected(Bundle connectionHint) {
        super.onConnected(connectionHint);
        Drive.DriveApi.newDriveContents(getGoogleApiClient())
                .setResultCallback(driveContentsCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CREATOR:
                if (resultCode == RESULT_OK) {
                    showMessage("Arquivo enviado com sucesso");
                    chamarTransisitionActivity();
                }
                finish();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }


    final ResultCallback<DriveApi.DriveContentsResult> driveContentsCallback =
            new ResultCallback<DriveApi.DriveContentsResult>() {
                @Override
                public void onResult(DriveApi.DriveContentsResult result) {
                   DriveContents driveContents = result.getDriveContents();

                    OutputStream outputStream = driveContents.getOutputStream();
                    try {
                        outputStream.write(TransitionActivity.buffer0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    MetadataChangeSet metadataChangeSet = new MetadataChangeSet.Builder()
                            .setMimeType("application/vnd.openxmlformats-officedocument.wordprocessingml.document").build();
                    IntentSender intentSender = Drive.DriveApi
                            .newCreateFileActivityBuilder()
                            .setInitialMetadata(metadataChangeSet)
                            .setInitialDriveContents(driveContents)
                            .build(getGoogleApiClient());
                    try {
                        startIntentSenderForResult(
                                intentSender, REQUEST_CODE_CREATOR, null, 0, 0, 0);

                    } catch (IntentSender.SendIntentException e) {
                        Log.w(TAG, "Unable to send intent", e);
                    }

                }

    };


    public void chamarTransisitionActivity(){
        Intent intent = new Intent(this, TransitionActivity.class);
        startActivity(intent);
    }
}