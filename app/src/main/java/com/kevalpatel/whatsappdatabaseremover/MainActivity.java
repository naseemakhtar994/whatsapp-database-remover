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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;

public class MainActivity extends AppCompatActivity {

    private static final String TASK_DB_REMOVER_TAG = "db_remover_task";
    private static final String TASK_DB_REMOVER_MANUAL_TAG = "db_remover_manual_task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clearManual = (Button) findViewById(R.id.btn_clear_manual);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            registerTask();

            clearManual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Clearing the database...", Toast.LENGTH_SHORT).show();
                    startService(new Intent(MainActivity.this, ManualRemoveService.class));
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                registerTask();
            } else {
                Toast.makeText(this, "Write storage permission not granted.", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void registerTask() {
        GcmNetworkManager gcmNetworkManager = GcmNetworkManager.getInstance(this);

        PeriodicTask task = new PeriodicTask.Builder()
                .setService(DatabaseRemoverService.class)
                .setTag(TASK_DB_REMOVER_TAG)
                .setRequiresCharging(true)
                .setPeriod(43200L)  //twice a day
                .setPersisted(true)
                .setFlex(1800L)     //half an hour
                .setUpdateCurrent(true)
                .build();

        gcmNetworkManager.schedule(task);
    }
}
