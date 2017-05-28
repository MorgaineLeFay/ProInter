package com.example.ramon.projectprointerv.activities.drive;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ramon.projectprointerv.activities.app.ApiClientAsyncTask;
import com.example.ramon.projectprointerv.activities.app.TransitionActivity;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Ramon on 15/05/2017.
 */

public class DriveUpdateFiles extends DriveUserActivity {


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

                    final ResultCallback<DriveApi.DriveIdResult> idCallback = new ResultCallback<DriveApi.DriveIdResult>() {
                        @Override
                        public void onResult(DriveApi.DriveIdResult result) {
                            if (!result.getStatus().isSuccess()) {
                                showMessage("Cannot find DriveId. Are you authorized to view this file?");
                                return;
                            }
                            DriveId driveId = result.getDriveId();
                            DriveFile file = driveId.asDriveFile();
                            new EditContentsAsyncTask(DriveUpdateFiles.this).execute(file);
                        }
                    };

                    String mFileIdString = mFileId.getResourceId();
                    Drive.DriveApi.fetchDriveId(getGoogleApiClient(), mFileIdString)
                            .setResultCallback(idCallback);

                    Intent intent = new Intent(this, TransitionActivity.class);
                    startActivity(intent);
                }

                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }



    public class EditContentsAsyncTask extends ApiClientAsyncTask<DriveFile, Void, Boolean> {

        public EditContentsAsyncTask(Context context) {
            super(context);
        }


        @Override
        protected Boolean doInBackgroundConnected(DriveFile... args) {
            DriveFile file = args[0];
            try {
                DriveApi.DriveContentsResult driveContentsResult = file.open(
                        getGoogleApiClient(), DriveFile.MODE_WRITE_ONLY, null).await();
                if (!driveContentsResult.getStatus().isSuccess()) {
                    return false;
                }
                DriveContents driveContents = driveContentsResult.getDriveContents();
                OutputStream outputStream = driveContents.getOutputStream();
                outputStream.write(TransitionActivity.buffer0);
                com.google.android.gms.common.api.Status status =
                        driveContents.commit(getGoogleApiClient(), null).await();
                return status.getStatus().isSuccess();
            } catch (IOException e) {
                Log.e(TAG, "IOException while appending to the output stream", e);
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                showMessage("Error while editing contents");
                return;
            }
            showMessage("Successfully edited contents");



        }
    }

}
