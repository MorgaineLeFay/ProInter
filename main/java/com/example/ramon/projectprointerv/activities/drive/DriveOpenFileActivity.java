package com.example.ramon.projectprointerv.activities.drive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.ramon.projectprointerv.activities.app.TransitionActivity;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;

/**
 * Created by Ramon on 15/05/2017.
 */

public class DriveOpenFileActivity extends DriveUserActivity {


    private DriveId mFileId;


    /**
     *  Handle Response of selected file
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(final int requestCode,
                                    final int resultCode, final Intent data) {
        switch (requestCode) {

            case REQUEST_CODE_OPENER:

                if (resultCode == RESULT_OK) {

                    mFileId = (DriveId) data.getParcelableExtra(
                            OpenFileActivityBuilder.EXTRA_RESPONSE_DRIVE_ID);
//
                    Log.e("file id", mFileId.getResourceId() + "");

                    String url = "https://drive.google.com/open?id="+ mFileId.getResourceId();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);


                }

                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

}
