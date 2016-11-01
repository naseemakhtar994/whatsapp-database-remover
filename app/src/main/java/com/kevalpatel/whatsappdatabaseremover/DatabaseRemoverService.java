package com.kevalpatel.whatsappdatabaseremover;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

import java.io.File;

/**
 * Created by Keval on 01-Nov-16.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class DatabaseRemoverService extends GcmTaskService {

    public DatabaseRemoverService() {
        super();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public int onRunTask(TaskParams taskParams) {

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
        return 0;
    }
}
