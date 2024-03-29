package com.glassbyte.neurobranch.Portal.MainSections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glassbyte.neurobranch.R;
import com.glassbyte.neurobranch.Services.DataObjects.JSON;
import com.glassbyte.neurobranch.Services.DataObjects.Trial;
import com.glassbyte.neurobranch.Services.Enums.Preferences;
import com.glassbyte.neurobranch.Services.Globals;
import com.glassbyte.neurobranch.Services.HTTP.HTTPRequest;
import com.glassbyte.neurobranch.Services.Helpers.Connectivity;
import com.glassbyte.neurobranch.Services.Helpers.Manager;
import com.glassbyte.neurobranch.Services.Interfaces.JSONCallback;

import org.json.JSONArray;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by ed on 10/06/16.
 */
public class TrialsAvailableFragment extends android.support.v4.app.Fragment implements JSONCallback {
    View view;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Trial> trials = new ArrayList<>();
        trials.add(new Trial("Retrieving Trials",
                "Please wait while trials are being retrieved from Neurobranch services.",
                "You", true));
        adapter = new CardAdapter(trials, TrialsAvailableFragment.this,
                getActivity().getSupportFragmentManager());
        recyclerView.setAdapter(adapter);
        loadTrials();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_trials_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.card_view_recycler_view);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)
                view.findViewById(R.id.swipe_portal_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTrials();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTrials();
    }

    private void loadTrials() {
        if(Connectivity.isNetworkConnected(getActivity())) {
            try {
                ArrayList<Trial> trials = new ArrayList<>();
                trials.add(new Trial("Trial Loading...", "Please wait while trials are loaded",
                        "You", false));

                String id = Manager.getInstance().getPreference(Preferences.id, getContext());
                URL endpoint = new URL(Globals.getExcludedTrials(id));

                new HTTPRequest.ReceiveJSON(getActivity(), endpoint, TrialsAvailableFragment.this).execute();
                adapter = new CardAdapter(trials, TrialsAvailableFragment.this, getActivity().getSupportFragmentManager());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            ArrayList<Trial> trials = new ArrayList<>();
            trials.add(new Trial("Internet unavailable",
                    "Please enable an internet connection in order to use Neurobranch services.",
                    "You", false));
            adapter = new CardAdapter(trials, TrialsAvailableFragment.this,
                    getActivity().getSupportFragmentManager());
        }

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadCompleted(JSONArray object) {
        ArrayList<Trial> trials = JSON.parseTrialJSON(object);
        if (trials.size() == 0 || object == null) {
            trials.add(new Trial("No Trials Currently Available",
                    "There are no trials available to you at this time", "You", false));
        }

        adapter = new CardAdapter(trials, TrialsAvailableFragment.this, getActivity().getSupportFragmentManager());
        recyclerView.setAdapter(adapter);
    }
}