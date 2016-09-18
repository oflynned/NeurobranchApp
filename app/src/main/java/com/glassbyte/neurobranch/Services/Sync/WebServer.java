package com.glassbyte.neurobranch.Services.Sync;

import android.content.Context;
import android.preference.PreferenceManager;

import com.glassbyte.neurobranch.Services.Helpers.Manager;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ed on 14/08/16.
 */
public class WebServer {
    public static void synchronise(Context context, String trialid) {
        Manager.getInstance().notifyUserWeb(context, trialid);
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
