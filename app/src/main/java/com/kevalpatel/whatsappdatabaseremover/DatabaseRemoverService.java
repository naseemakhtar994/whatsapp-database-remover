package com.kevalpatel.whatsappdatabaseremover;

import android.util.Log;

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

        Log.d("task runner", "service running");

        return 0;
    }
}
