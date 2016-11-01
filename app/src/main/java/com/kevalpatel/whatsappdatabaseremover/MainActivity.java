package com.kevalpatel.whatsappdatabaseremover;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;

public class MainActivity extends AppCompatActivity {

    private static final String TASK_DB_REMOVER_TAG = "db_remover_task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
