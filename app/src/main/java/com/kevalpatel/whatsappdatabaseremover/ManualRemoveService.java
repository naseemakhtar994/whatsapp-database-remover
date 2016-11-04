package com.kevalpatel.whatsappdatabaseremover;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Keval on 04-Nov-16.
 * This intent service will execute to to remove database manually.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class ManualRemoveService extends IntentService {
    public ManualRemoveService() {
        super("ManualRemoveService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //check for the permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

            //Check for the external storage dir.
            File dir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()
                    + getString(R.string.whats_app_database_path));

            if (dir.exists()) {
                //List all the db files in this folder
                File[] files = dir.listFiles();

                for (File file: files)
                    if (!file.getName().contains("msgstore.db")) file.delete();
            } else {
                //Whatsapp folder not available.
            }
        } else {
            Toast.makeText(this, "Write external storage permission not provided.", Toast.LENGTH_LONG).show();
        }
    }
}
