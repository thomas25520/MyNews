package com.mynews.controller.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.controller.activities.DisplaySearchActivity;
import com.mynews.controller.activities.SearchActivity;
import com.mynews.data.entities.search.SearchResponse;
import com.mynews.utils.SearchCall;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchFragment extends Fragment implements RootSearchCallBack { // while implement interface, should implement method
    private final String searchActivity = "SearchActivity";
    private final String notificationActivity = "NotificationActivity";

    private DatePickerDialog.OnDateSetListener mDateSetListenerEnd;
    private DatePickerDialog.OnDateSetListener mDateSetListenerBegin;

    private String mBeginDateApiFormat;
    private String mEndDateApiFormat;

    public EditText mQuery;
    public TextView mBeginDateTextView;
    public TextView mEndDateTextView;
    public TextView mSearchBtn;
    public TextView mNotificationBtn;
    private CheckBox mArts;
    private CheckBox mPolitics;
    private CheckBox mBusiness;
    private CheckBox mSports;
    private CheckBox mEntrepreneurs;
    private CheckBox mTravels;
    private String activity;

    View mView;

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

        mView = inflater.inflate(R.layout.fragment_search, container, false);

        chooseInfoToDisplay(activity);

        return mView;
    }

    private void initViewForSearchActivity() {
        mBeginDateTextView = mView.findViewById(R.id.fragment_search_start_date_edit);
        mEndDateTextView = mView.findViewById(R.id.fragment_search_end_date_edit);
        mQuery = mView.findViewById(R.id.fragment_search_query_term);
        mSearchBtn = mView.findViewById(R.id.fragment_search_search_btn);

        mArts = mView.findViewById(R.id.fragment_search_checkBox_arts);
        mPolitics = mView.findViewById(R.id.fragment_search_checkBox_politics);
        mBusiness = mView.findViewById(R.id.fragment_search_checkBox_business);
        mSports = mView.findViewById(R.id.fragment_search_checkBox_sports);
        mEntrepreneurs = mView.findViewById(R.id.fragment_search_checkBox_entrepreneurs);
        mTravels = mView.findViewById(R.id.fragment_search_checkBox_travels);
    }

    private void initViewForNotificationActivity() {
        mQuery = mView.findViewById(R.id.fragment_notification_query_term);
        mNotificationBtn = mView.findViewById(R.id.fragment_notification_oncePerDayBtn);

        mArts = mView.findViewById(R.id.fragment_notification_checkBox_arts);
        mPolitics = mView.findViewById(R.id.fragment_notification_checkBox_politics);
        mBusiness = mView.findViewById(R.id.fragment_notification_checkBox_business);
        mSports = mView.findViewById(R.id.fragment_notification_checkBox_sports);
        mEntrepreneurs = mView.findViewById(R.id.fragment_notification_checkBox_entrepreneurs);
        mTravels = mView.findViewById(R.id.fragment_notification_checkBox_travels);
    }

    private void chooseInfoToDisplay(String activity) {
        switch (activity) {
            case searchActivity:
                // build for case & init for each view
                initViewForSearchActivity();
                initBeginDate();
                initEndDate();

                final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                mSearchBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        // User Error handling
                        if (mQuery.getText().toString().isEmpty()) // Check query
                            Toast.makeText(getContext(), "Merci d'entrer un mot-clé", Toast.LENGTH_LONG).show();
                        else if (Integer.valueOf(mBeginDateApiFormat) > Integer.valueOf(mEndDateApiFormat)) { // Verify begiDate < endDate
                            Toast.makeText(getContext(), "Merci d'entrer une date de début inférieur à la date de fin.", Toast.LENGTH_LONG).show();
                            if (Integer.valueOf(mBeginDateApiFormat) > Integer.valueOf(sdf.format(Calendar.getInstance().getTime())) || Integer.valueOf(mEndDateApiFormat) > Integer.valueOf(sdf.format(Calendar.getInstance().getTime()))) // Verify (beginDate & endDate) < current date
                                Toast.makeText(getContext(), "Merci d'entrer une date inférieure à la date actuelle", Toast.LENGTH_LONG).show();
                        } else if (!mArts.isChecked() && !mPolitics.isChecked() && !mBusiness.isChecked() && !mSports.isChecked() && !mEntrepreneurs.isChecked() && !mTravels.isChecked()) // Checks that at least one category is checked
                            Toast.makeText(getContext(), "Merci de cocher au moins une catégorie.", Toast.LENGTH_LONG).show();
                        else
                            new SearchCall().search(SearchFragment.this, mQuery.getText().toString(), getSection(), mBeginDateApiFormat, mEndDateApiFormat);
                    }
                });



                break;
            case notificationActivity:
                initViewForNotificationActivity();
                break;
        }
    }

    public String getSection() {
        // Checkbox is checked or not return string for api
        String section = "";

        if (mArts.isChecked())
            section += "Arts+";
        if (mPolitics.isChecked())
            section += "Politics+";
        if (mBusiness.isChecked())
            section += "Business+";
        if (mSports.isChecked())
            section += "Sports+";
        if (mEntrepreneurs.isChecked())
            section += "Entrepreneurs+";
        if (mTravels.isChecked())
            section += "Travels+";
        return section;
    }

    // User press SEARCH button
    public void runSearch() {
    }

    private void initBeginDate() {
        // Default date is current date
        SimpleDateFormat sdfToApi = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = Calendar.getInstance().getTime();
        mBeginDateApiFormat = sdfToApi.format(currentDate);

        SimpleDateFormat sdfToDisplay = new SimpleDateFormat("dd/MM/yyyy");
        mBeginDateTextView.setText(sdfToDisplay.format(currentDate));
    }

    public void setBeginDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        // Display user choice on TextView
        mDateSetListenerBegin = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String beginDate = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year; // Display
                mBeginDateApiFormat = year + String.format("%02d", month) + String.format("%02d", day); // API
                mBeginDateTextView.setText(beginDate);
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListenerBegin,
                year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void initEndDate() {
        // Default date is current date
        SimpleDateFormat sdfToApi = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = Calendar.getInstance().getTime();
        mEndDateApiFormat = sdfToApi.format(currentDate);

        SimpleDateFormat sdfToDisplay = new SimpleDateFormat("dd/MM/yyyy");
        mEndDateTextView.setText(sdfToDisplay.format(currentDate));
    }

    public void setEndDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        // Display user choice on TextView
        mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String endDate = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year; // Display
                mEndDateApiFormat = year + String.format("%02d", month) + String.format("%02d", day); // API
                mEndDateTextView.setText(endDate);
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListenerEnd,
                year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onResponse(SearchResponse searchResponse) {
        Intent intent = new Intent(getContext(), DisplaySearchActivity.class);
        intent.putExtra("searchResponse", searchResponse.toJson()); // put string object converted with json
        startActivity(intent);
    }

    @Override
    public void onFailure() {
        // todo : throw the error
    }
}