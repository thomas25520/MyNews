package com.mynews.controller.activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.controller.model.Search;
import com.mynews.data.entities.search.SearchResponse;
import com.mynews.utils.SearchCall;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchActivity extends AppCompatActivity implements RootSearchCallBack { // while implement interface, should implement method

    private final SearchActivity mThis = this;

    public EditText mQuery;
    private TextView mBeginDate;
    private TextView mEndDate;
    private static String mSection;
    private String mBeginDateApi;
    private String mEndDateApi;


    private DatePickerDialog.OnDateSetListener mDateSetListenerEnd;
    private DatePickerDialog.OnDateSetListener mDateSetListenerBegin;

    TextView mSearchBtn;
    Search mSearch;
    private static CheckBox mArts;
    private static CheckBox mPolitics;
    private static CheckBox mBusiness;
    private static CheckBox mSports;
    private static CheckBox mEntrepreneurs;
    private static CheckBox mTravels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
    }

    public void initViews() {
        mSearchBtn = findViewById(R.id.activity_search_search_btn);
        mArts = findViewById(R.id.activity_search_checkBox_arts);
        mPolitics = findViewById(R.id.activity_search_checkBox_politics);
        mBusiness = findViewById(R.id.activity_search_checkBox_business);
        mSports = findViewById(R.id.activity_search_checkBox_sports);
        mEntrepreneurs = findViewById(R.id.activity_search_checkBox_entrepreneurs);
        mTravels = findViewById(R.id.activity_search_checkBox_travels);

        setToolbar();
        setQueryTerm();
        setBeginDate();
        setEndDate();
        onBtnSearchClickListener();
    }

    private void onBtnSearchClickListener() {
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchCall searchCall = new SearchCall();
                searchCall.search(mThis, mQuery, mBeginDateApi, mEndDateApi);
            }
        });
    }

    public static String getSection() {
        // Checkbox is checked or not return string for api
        mSection = "";

        if (mArts.isChecked())
            mSection += "Arts+";
        if (mPolitics.isChecked())
            mSection += "Politics+";
        if (mBusiness.isChecked())
            mSection += "Business+";
        if (mSports.isChecked())
            mSection += "Sports+";
        if (mEntrepreneurs.isChecked())
            mSection += "Entrepreneurs+";
        if (mTravels.isChecked())
            mSection += "Travels+";

        return mSection;
    }

    private void setQueryTerm() {
        mQuery = findViewById(R.id.activity_search_query_term);
        mQuery.setHint("search query term");
    }

    private void setToolbar() {
        Toolbar mToolbar = findViewById(R.id.activity_search_toolbar);
        mToolbar.setTitle("Search Articles");
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24px);

        // Return to main activity when click on arrow back button
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setBeginDate() {
        mBeginDate = findViewById(R.id.activity_search_start_date_edit);

        // Default date is current date
        SimpleDateFormat sdfToApi = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = Calendar.getInstance().getTime();
        mBeginDateApi = sdfToApi.format(currentDate);

        SimpleDateFormat sdfToDisplay = new SimpleDateFormat("dd/MM/yyyy");
        mBeginDate.setText(sdfToDisplay.format(currentDate));


        mBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SearchActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerBegin,
                        year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        // Display user choice on TextView
        mDateSetListenerBegin = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String beginDate = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year; // Display
                mBeginDateApi = year + String.format("%02d", month) + String.format("%02d", day); // API
                mBeginDate.setText(beginDate);
            }
        };
    }

    private void setEndDate() {
        mEndDate = findViewById(R.id.activity_search_end_date_edit);

        // Default date is current date
        SimpleDateFormat sdfToApi = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = Calendar.getInstance().getTime();
        mEndDateApi = sdfToApi.format(currentDate);

        SimpleDateFormat sdfToDisplay = new SimpleDateFormat("dd/MM/yyyy");
        mEndDate.setText(sdfToDisplay.format(currentDate));

        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SearchActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerEnd,
                        year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        // Display user choice on TextView
        mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String endDate = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year; // Display
                mEndDateApi = year + String.format("%02d", month) + String.format("%02d", day); // API

                mEndDate.setText(endDate);
            }
        };
    }

    @Override
    public void onResponse(SearchResponse searchResponse) {

    }

    @Override
    public void onFailure() {

    }
}