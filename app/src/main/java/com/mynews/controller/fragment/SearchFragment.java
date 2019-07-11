package com.mynews.controller.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mynews.R;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchFragment extends Fragment { // while implement interface, should implement method
    private final String searchActivity = "SearchActivity";
    private final String notificationActivity = "NotificationActivity";
    public EditText mQuery;
    public TextView mBeginDateTextView;
    public TextView mEndDateTextView;
    public TextView mSearchBtn;
    public TextView mNotificationBtn;
    View mView;
    private CheckBox mArts;
    private CheckBox mPolitics;
    private CheckBox mBusiness;
    private CheckBox mSports;
    private CheckBox mEntrepreneurs;
    private CheckBox mTravels;
    private String activity;

    public static SearchFragment newInstance(String activity) {
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("activity", activity);
        searchFragment.activity = activity;
        return searchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        switch (activity) {
            case searchActivity:
                // build for case & init for each view
                mView = inflater.inflate(R.layout.fragment_notification, container, false);
                //initViewForSearchActivity();
                break;
            case notificationActivity:
                mView = inflater.inflate(R.layout.fragment_notification, container, false);
                initViewForNotificationActivity();
                break;
        }
        //chooseInfoToDisplay(activity);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void runSearch(View view) {

    }

    private void initViewForSearchActivity() {
        mBeginDateTextView = mView.findViewById(R.id.activity_search_start_date_edit);
        mEndDateTextView = mView.findViewById(R.id.activity_search_end_date_edit);
        mQuery = mView.findViewById(R.id.notification_search_query_term);
        mSearchBtn = mView.findViewById(R.id.activity_search_search_btn);

        mArts = mView.findViewById(R.id.notification_search_checkBox_arts);
        mPolitics = mView.findViewById(R.id.notification_search_checkBox_politics);
        mBusiness = mView.findViewById(R.id.notification_search_checkBox_business);
        mSports = mView.findViewById(R.id.notification_search_checkBox_sports);
        mEntrepreneurs = mView.findViewById(R.id.notification_search_checkBox_entrepreneurs);
        mTravels = mView.findViewById(R.id.notification_search_checkBox_travels);
    }

    private void initViewForNotificationActivity() {
        mQuery = mView.findViewById(R.id.notification_search_query_term);
        mNotificationBtn = mView.findViewById(R.id.fragment_notification_oncePerDayBtn);

        mArts = mView.findViewById(R.id.notification_search_checkBox_arts);
        mPolitics = mView.findViewById(R.id.notification_search_checkBox_politics);
        mBusiness = mView.findViewById(R.id.notification_search_checkBox_business);
        mSports = mView.findViewById(R.id.notification_search_checkBox_sports);
        mEntrepreneurs = mView.findViewById(R.id.notification_search_checkBox_entrepreneurs);
        mTravels = mView.findViewById(R.id.notification_search_checkBox_travels);
    }

    private void chooseInfoToDisplay(String activity) {
        switch (activity) {
            case searchActivity:
                // build for case & init for each view
                initViewForSearchActivity();
                break;
            case notificationActivity:
                initViewForNotificationActivity();
                break;
        }
    }
}