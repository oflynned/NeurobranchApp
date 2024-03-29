package com.glassbyte.neurobranch.Services.Sync;

import android.app.IntentService;
import android.content.Intent;

import com.glassbyte.neurobranch.Services.DataObjects.Trial;
import com.glassbyte.neurobranch.Services.Enums.Preferences;
import com.glassbyte.neurobranch.Services.Globals;
import com.glassbyte.neurobranch.Services.HTTP.HTTPRequest;
import com.glassbyte.neurobranch.Services.Helpers.Manager;
import com.glassbyte.neurobranch.Services.Interfaces.JSONCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by ed on 14/08/16
 */
public class SyncService extends IntentService {

    public SyncService() {
        super("SyncService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("Invoking web service");
        if(!Manager.getInstance().getPreference(Preferences.id, getApplicationContext()).equals(""))
            WebServer.pollTrialStates(getApplicationContext());
    }
}

