package com.glassbyte.neurobranch.Services.Sync;

import android.content.Context;
import android.preference.PreferenceManager;

import org.json.JSONObject;

/**
 * Created by ed on 14/08/16.
 */
public class WebServer {
    public static void synchronise() {
        
    }

    public static class PollAccount {
        public static String getCandidateId(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context).getString("id", "");
        }

        public static JSONObject getTrialsPartitive(String candidateId) {
            return null;
        }

        public static JSONObject getTrialsWithUpdates(JSONObject trialIds) {
            return null;
        }

        public static void getIsNewMember() {}
        public static void getIsVerifiedMember() {}
    }
}