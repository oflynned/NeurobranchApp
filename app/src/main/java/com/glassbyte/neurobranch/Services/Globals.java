package com.glassbyte.neurobranch.Services;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by ed on 10/06/16.
 */
public class Globals {
    //splash
    public static final int SPLASH_DURATION = 2000;

    //networking
    public static final String BACKEND_URL = "http://ec2-54-229-150-246.eu-west-1.compute.amazonaws.com";
    public static final String EMULATOR_LOOPBACK = "http://10.0.2.2:3000";

    public static final String HOST_ADDRESS = EMULATOR_LOOPBACK;

    public static final String POST_TRIAL_RESPONSE = HOST_ADDRESS + "insert";
    public static final String POST_QUESTION_RESPONSE = HOST_ADDRESS + "api/responsedata";

    //candidate actions
    public static String getCandidateInfo(String candidateId) {
        return HOST_ADDRESS + "/api/get-candidates/" + candidateId;
    }
    public static String retrieveTrialQuestions(String trialid) {
        return HOST_ADDRESS + "/api/get-questions/trialid/" + trialid;
    }

    public static String getRequestedCandidates(String candidateid) {
        return HOST_ADDRESS + "/api/get-requested-candidates/candidate/" + candidateid;
    }

    public static String getPartitiveMyTrials(String candidateid) {
        return HOST_ADDRESS + "/api/get-candidate-trials/" + candidateid;
    }

    public static String getExcludedTrials(String candidateid) {
        return HOST_ADDRESS + "/api/get-candidate-excluded-trials/" + candidateid;
    }

    public static final String CANDIDATE_SIGNUP_ADDRESS = HOST_ADDRESS + "/api/create-candidate";
    public static final String CANDIDATE_LOGIN_ADDRESS = HOST_ADDRESS + "/api/candidate-login";

    public static final String RETRIEVE_TRIALS_ADDRESS = HOST_ADDRESS + "/api/get-trials";
    public static final String POST_RESPONSE_ADDRESS = HOST_ADDRESS + "/api/create-response/";

    public static final String ADD_TO_REQUESTED_LIST = HOST_ADDRESS + "/api/create-requested-candidate";


    //deprecated static hosting
    public static final String GET_QUESTIONS_ADDRESS = "http://www.glassbyte.com/neurobranch_mock_data/questions.json";

    //programmatic layout helpers
    public static int getDp(Context context, float pixels) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                pixels, context.getResources().getDisplayMetrics());
    }
}
