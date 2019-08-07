package com.mynews.controller.fragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.controller.activities.DisplaySearchActivity;
import com.mynews.data.entities.search.SearchResponse;
import com.mynews.utils.DateFormatter;
import com.mynews.utils.MyNotificationReceiver;
import com.mynews.utils.SearchCall;
import com.mynews.utils.SharedPreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.mynews.utils.Constants.USER_CATEGORIES;
import static com.mynews.utils.Constants.USER_NOTIFICATION_BTN;
import static com.mynews.utils.Constants.USER_QUERY;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchAndNotificationFragment extends Fragment implements RootSearchCallBack { // while implement interface, should implement method
    private static final String SEARCH_ACTIVITY = "SearchActivity";
    private static final String NOTIFICATION_ACTIVITY = "NotificationActivity";
    SimpleDateFormat mSdfToApi = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat mSdfToDisplay = new SimpleDateFormat("dd/MM/yyyy");
    private EditText mQuery;
    private TextView mBeginDateTextView;
    private TextView mEndDateTextView;
    private TextView mSearchBtn;
    private Switch mNotificationBtn;
    private String mBeginDateApiFormat;
    private String mEndDateApiFormat;
    private CheckBox mArts;
    private CheckBox mPolitics;
    private CheckBox mBusiness;
    private CheckBox mSports;
    private CheckBox mEntrepreneurs;
    private CheckBox mTravels;
    private String mActivityName;
    private View mView;
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    private DateFormatter mDateFormatter = new DateFormatter();

    public static SearchAndNotificationFragment newInstance(String activityName) {
        SearchAndNotificationFragment searchAndNotificationFragment = new SearchAndNotificationFragment();
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
                initNotificationActivity();
                break;
            default:
                break;
        }
        return mView;
    }

    // SEARCH PART
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

    private void initBeginDate() {
        // Default date is current date
        Date currentDate = Calendar.getInstance().getTime();
        mBeginDateApiFormat = mSdfToApi.format(currentDate);

        mBeginDateTextView.setText(mSdfToDisplay.format(currentDate));
    }

    private void setBeginDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        // Display user choice on TextView
        DatePickerDialog.OnDateSetListener dateSetListenerBegin = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String beginDate = mDateFormatter.getDisplayDateFormat(year, month, day);
                mBeginDateApiFormat = mDateFormatter.getApiDateFormat(year, month, day);
                mBeginDateTextView.setText(beginDate);
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(
                Objects.requireNonNull(getContext()),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListenerBegin,
                year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void initEndDate() {
        // Default date is current date
        Date currentDate = Calendar.getInstance().getTime();
        mEndDateApiFormat = mSdfToApi.format(currentDate);

        mEndDateTextView.setText(mSdfToDisplay.format(currentDate));
    }

    private void setEndDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        // Display user choice on TextView
        DatePickerDialog.OnDateSetListener dateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String endDate = mDateFormatter.getDisplayDateFormat(year, month, day);

                mEndDateApiFormat = mDateFormatter.getApiDateFormat(year, month, day);
                mEndDateTextView.setText(endDate);
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(
                Objects.requireNonNull(getContext()),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListenerEnd,
                year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // User press SEARCH button
    private void actionSearch() {
        // User Error handling
        if (mQuery.getText().toString().isEmpty()) // Check query
            Toast.makeText(getContext(), "Merci d'entrer un mot-clé", Toast.LENGTH_LONG).show();
        else if (Integer.valueOf(mBeginDateApiFormat) > Integer.valueOf(mEndDateApiFormat)) { // Verify beginDate < endDate
            Toast.makeText(getContext(), "Merci d'entrer une date de début inférieur à la date de fin.", Toast.LENGTH_LONG).show();
            if (Integer.valueOf(mBeginDateApiFormat) > Integer.valueOf(mSdfToApi.format(Calendar.getInstance().getTime())) || Integer.valueOf(mEndDateApiFormat) > Integer.valueOf(mSdfToApi.format(Calendar.getInstance().getTime()))) // Verify (beginDate & endDate) < current date
                Toast.makeText(getContext(), "Merci d'entrer une date inférieure à la date actuelle", Toast.LENGTH_LONG).show();
        } else if (!mArts.isChecked() && !mPolitics.isChecked() && !mBusiness.isChecked() && !mSports.isChecked() && !mEntrepreneurs.isChecked() && !mTravels.isChecked()) // Checks that at least one category is checked
            Toast.makeText(getContext(), "Merci de cocher au moins une catégorie.", Toast.LENGTH_LONG).show();
        else
            new SearchCall().search(this, mQuery.getText().toString(), getSection(), mBeginDateApiFormat, mEndDateApiFormat);
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

    // NOTIFICATION PART
    private void initNotificationActivity() {
        queryUserPreferences();
        categoriesPosition();
        setCategoriesPreferences();
        oncePerDayBtnPosition();
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

    private void queryUserPreferences() {
        if (!SharedPreferencesManager.getString(getContext(), USER_QUERY).isEmpty())
            mQuery.setText(SharedPreferencesManager.getString(getContext(), USER_QUERY));

        mQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferencesManager.putString(getContext(), USER_QUERY, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void categoriesPosition() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferencesManager.putString(getContext(), USER_CATEGORIES, getSection());
            }
        };
        mArts.setOnCheckedChangeListener(listener);
        mPolitics.setOnCheckedChangeListener(listener);
        mBusiness.setOnCheckedChangeListener(listener);
        mSports.setOnCheckedChangeListener(listener);
        mEntrepreneurs.setOnCheckedChangeListener(listener);
        mTravels.setOnCheckedChangeListener(listener);
    }

    private void enableAlarm() {
        scheduleNotification(9, 30, 0, AlarmManager.INTERVAL_DAY);
    }

    // Change time to display the notification
    private void scheduleNotification(int hour, int minute, int second, long intervalDay) {
        Intent notifyIntent = new Intent(getContext(), MyNotificationReceiver.class);
        mPendingIntent = PendingIntent.getBroadcast(getContext(), 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalDay, mPendingIntent);
    }

    private void disableAlarm() {
        if (mAlarmManager != null)
            mAlarmManager.cancel(mPendingIntent);
    }

    private void setCategoriesPreferences() {
        String userCategoriesPreferences = SharedPreferencesManager.getString(getContext(), USER_CATEGORIES);

        mArts.setChecked(userCategoriesPreferences.contains("Arts"));
        mSports.setChecked(userCategoriesPreferences.contains("Sports"));
        mTravels.setChecked(userCategoriesPreferences.contains("Travels"));
        mPolitics.setChecked(userCategoriesPreferences.contains("Politics"));
        mBusiness.setChecked(userCategoriesPreferences.contains("Business"));
        mEntrepreneurs.setChecked(userCategoriesPreferences.contains("Entrepreneurs"));
    }

    private void oncePerDayBtnPosition() {
        // Set notification btn position when user open notification menu
        mNotificationBtn.setChecked(SharedPreferencesManager.getBoolean(getContext(), USER_NOTIFICATION_BTN));

        final CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mQuery.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(), "Merci d'entrer un mot clé", Toast.LENGTH_SHORT).show();
                        mNotificationBtn.setChecked(false);

                    } else if (getSection().isEmpty()) {
                        Toast.makeText(getContext(), "Merci de cocher au moins une catégorie", Toast.LENGTH_SHORT).show();
                        mNotificationBtn.setChecked(false);

                    } else {
                        // turn alarm ON
                        enableAlarm();
                        Toast.makeText(getContext(), "Notifications activées", Toast.LENGTH_SHORT).show();
                        SharedPreferencesManager.putBoolean(getContext(), USER_NOTIFICATION_BTN, true);
                    }

                } else {
                    // turn alarm OFF
                    disableAlarm();
                    Toast.makeText(getContext(), "Notifications désactivées", Toast.LENGTH_SHORT).show();
                    SharedPreferencesManager.putBoolean(getContext(), USER_NOTIFICATION_BTN, false);
                }
            }
        };
        mNotificationBtn.setOnCheckedChangeListener(listener);
    }

    // BOTH PART
    private String getSection() {
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
}