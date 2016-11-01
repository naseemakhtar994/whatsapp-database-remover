package com.kevalpatel.whatsappdatabaseremover;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;

public class MainActivity extends AppCompatActivity {

    private static final String TASK_DB_REMOVER_TAG = "db_remover_task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            registerTask();
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
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void registerTask() {
        GcmNetworkManager gcmNetworkManager = GcmNetworkManager.getInstance(this);

        PeriodicTask task = new PeriodicTask.Builder()
                .setService(DatabaseRemoverService.class)
                .setTag(TASK_DB_REMOVER_TAG)
                .setRequiresCharging(true)
                .setPeriod(30L)  //twice a day
                .setPersisted(true)
                .setFlex(10L)     //half an hour
                .setUpdateCurrent(true)
                .build();

        gcmNetworkManager.schedule(task);
    }
}
