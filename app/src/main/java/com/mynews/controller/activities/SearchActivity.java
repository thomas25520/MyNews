package com.mynews.controller.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.controller.fragment.SearchFragment;
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
    private TextView mBeginDateTextView;
    private TextView mEndDateTextView;
    private String mBeginDateApiFormat;
    private String mEndDateApiFormat;

    private DatePickerDialog.OnDateSetListener mDateSetListenerEnd;
    private DatePickerDialog.OnDateSetListener mDateSetListenerBegin;

    TextView mSearchBtn;
    private static CheckBox mArts;
    private static CheckBox mPolitics;
    private static CheckBox mBusiness;
    private static CheckBox mSports;
    private static CheckBox mEntrepreneurs;
    private static CheckBox mTravels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true); // active arrow back

        SearchFragment searchFragment = SearchFragment.newInstance("SearchActivity");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_search, searchFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //initViews();
        //initBeginDate();
        //initEndDate();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initViews() {
        mBeginDateTextView = findViewById(R.id.activity_search_start_date_edit);
        mEndDateTextView = findViewById(R.id.activity_search_end_date_edit);
        mQuery = findViewById(R.id.notification_search_query_term);
        mSearchBtn = findViewById(R.id.activity_search_search_btn);

        mArts = findViewById(R.id.notification_search_checkBox_arts);
        mPolitics = findViewById(R.id.notification_search_checkBox_politics);
        mBusiness = findViewById(R.id.notification_search_checkBox_business);
        mSports = findViewById(R.id.notification_search_checkBox_sports);
        mEntrepreneurs = findViewById(R.id.notification_search_checkBox_entrepreneurs);
        mTravels = findViewById(R.id.notification_search_checkBox_travels);
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

    public void runSearch(View view) { // Action listener button search on layout
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // User Error handling
        if (mQuery.getText().toString().isEmpty()) // Check query
            Toast.makeText(getBaseContext(), "Merci d'entrer un mot-clé", Toast.LENGTH_LONG).show();
        else if (Integer.valueOf(mBeginDateApiFormat) > Integer.valueOf(mEndDateApiFormat)) { // Verify begiDate < endDate
            Toast.makeText(getBaseContext(), "Merci d'entrer une date de début inférieur à la date de fin.", Toast.LENGTH_LONG).show();
            if (Integer.valueOf(mBeginDateApiFormat) > Integer.valueOf(sdf.format(Calendar.getInstance().getTime())) || Integer.valueOf(mEndDateApiFormat) > Integer.valueOf(sdf.format(Calendar.getInstance().getTime()))) // Verify (beginDate & endDate) < current date
                Toast.makeText(getBaseContext(), "Merci d'entrer une date inférieure à la date actuelle", Toast.LENGTH_LONG).show();
        } else if (!mArts.isChecked() && !mPolitics.isChecked() && !mBusiness.isChecked() && !mSports.isChecked() && !mEntrepreneurs.isChecked() && !mTravels.isChecked()) // Checks that at least one category is checked
            Toast.makeText(getBaseContext(), "Merci de cocher au moins une catégorie.", Toast.LENGTH_LONG).show();
        else
            new SearchCall().search(mThis, mQuery.getText().toString(), getSection(), mBeginDateApiFormat, mEndDateApiFormat);
    }

    private void initBeginDate() {
        // Default date is current date
        SimpleDateFormat sdfToApi = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = Calendar.getInstance().getTime();
        mBeginDateApiFormat = sdfToApi.format(currentDate);

        SimpleDateFormat sdfToDisplay = new SimpleDateFormat("dd/MM/yyyy");
        mBeginDateTextView.setText(sdfToDisplay.format(currentDate));
    }

    public void setBeginDate(View view) {
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
                SearchActivity.this,
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

    public void setEndDate(View view) {
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
                SearchActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListenerEnd,
                year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onResponse(SearchResponse searchResponse) {
        Intent intent = new Intent(this, DisplaySearchActivity.class);
        intent.putExtra("searchResponse", searchResponse.toJson()); // put string object converted with json
        startActivity(intent);
    }

    @Override
    public void onFailure() {
        // todo : throw the error
    }
}