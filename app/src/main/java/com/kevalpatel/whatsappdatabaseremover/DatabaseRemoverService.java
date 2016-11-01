package com.kevalpatel.whatsappdatabaseremover;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

/**
 * Created by Keval on 01-Nov-16.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class DatabaseRemoverService extends GcmTaskService {

    public DatabaseRemoverService() {
        super();
    }

    @Override
    public int onRunTask(TaskParams taskParams) {

        //check for the permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){


        }else {
            Toast.makeText(this, "Write external storage permission not provided.",Toast.LENGTH_LONG).show();
        }
        return 0;
    }
}
