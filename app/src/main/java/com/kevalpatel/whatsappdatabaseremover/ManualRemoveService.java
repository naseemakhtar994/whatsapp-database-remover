/*
 * Copyright 2016 Keval Patel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
