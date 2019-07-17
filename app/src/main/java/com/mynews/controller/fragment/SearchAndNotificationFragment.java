package com.mynews.controller.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.mynews.data.entities.search.SearchResponse;
import com.mynews.utils.SearchCall;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchAndNotificationFragment extends Fragment implements RootSearchCallBack { // while implement interface, should implement method
    private static final String SEARCH_ACTIVITY = "SearchActivity";
    private static final String NOTIFICATION_ACTIVITY = "NotificationActivity";

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
    private String mActivityName;

    View mView;

    public static SearchAndNotificationFragment newInstance(String activityName) {
        SearchAndNotificationFragment searchAndNotificationFragment = new SearchAndNotificationFragment();
        //Bundle bundle = new Bundle();
        //bundle.putString("mActivityName", mActivityName);
        searchAndNotificationFragment.mActivityName = activityName;
        return searchAndNotificationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (mActivityName) {
            case SEARCH_ACTIVITY:
                mView = inflater.inflate(R.layout.fragment_search, container, false);
                initSearchActivity();
                break;
            case NOTIFICATION_ACTIVITY:
                mView = inflater.inflate(R.layout.fragment_notification, container, false);
                initViewForNotificationActivity();
                break;
            default:
                break;
        }
        return mView;
    }

    private void initSearchActivity() {
        initViewForSearchActivity();
        initBeginDate();
        initEndDate();
        initButtonsListener();
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

    private void initButtonsListener() {
        mBeginDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeginDate();
            }
        });

        mEndDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEndDate();
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionSearch();
            }
        });
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
    public void actionSearch() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // User Error handling
        if (mQuery.getText().toString().isEmpty()) // Check query
            Toast.makeText(getContext(), "Merci d'entrer un mot-clé", Toast.LENGTH_LONG).show();
        else if (Integer.valueOf(mBeginDateApiFormat) > Integer.valueOf(mEndDateApiFormat)) { // Verify beginDate < endDate
            Toast.makeText(getContext(), "Merci d'entrer une date de début inférieur à la date de fin.", Toast.LENGTH_LONG).show();
            if (Integer.valueOf(mBeginDateApiFormat) > Integer.valueOf(sdf.format(Calendar.getInstance().getTime())) || Integer.valueOf(mEndDateApiFormat) > Integer.valueOf(sdf.format(Calendar.getInstance().getTime()))) // Verify (beginDate & endDate) < current date
                Toast.makeText(getContext(), "Merci d'entrer une date inférieure à la date actuelle", Toast.LENGTH_LONG).show();
        } else if (!mArts.isChecked() && !mPolitics.isChecked() && !mBusiness.isChecked() && !mSports.isChecked() && !mEntrepreneurs.isChecked() && !mTravels.isChecked()) // Checks that at least one category is checked
            Toast.makeText(getContext(), "Merci de cocher au moins une catégorie.", Toast.LENGTH_LONG).show();
        else
            new SearchCall().search(this, mQuery.getText().toString(), getSection(), mBeginDateApiFormat, mEndDateApiFormat);
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
                String beginDate = getDisplayDateFormat(year, month, day); // Display
                mBeginDateApiFormat = getApiDateFormat(year, month, day); // API
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

    public String getDisplayDateFormat(int year, int month, int day) {
        month = month + 1;
        return String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
    }

    public String getApiDateFormat(int year, int month, int day) {
        month = month + 1;
        return year + String.format("%02d", month) + String.format("%02d", day);
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
                String endDate = getDisplayDateFormat(year, month, day); // Display
                mEndDateApiFormat = getApiDateFormat(year, month, day); // API
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
        Log.i("", "");
        // FIXME: 15/07/2019 change prototype of onFailure for get in parameter exception throw the error
    }
}