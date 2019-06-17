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
import com.mynews.controller.model.Search;
import com.mynews.utils.Constants;
import com.mynews.utils.SharedPreferencesManager;

import java.util.Calendar;
import java.util.Objects;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchActivity extends AppCompatActivity {
    public EditText mQueryTerm;
    private TextView mDisplayEndDate;
    private TextView mDisplayBeginDate;
    private DatePickerDialog.OnDateSetListener mDateSetListenerEnd;
    private DatePickerDialog.OnDateSetListener mDateSetListenerBegin;

    TextView mSearchBtn;
    Search mSearch;
    private CheckBox mArts;
    private CheckBox mPolitics;
    private CheckBox mBusiness;
    private CheckBox mSports;
    private CheckBox mEntrepreneurs;
    private CheckBox mTravels;

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
                String queryTerm = mQueryTerm.getText().toString();
                String displayBeginDate = mDisplayBeginDate.getText().toString();
                String displayEndDate = mDisplayEndDate.getText().toString();

                boolean arts = mArts.isChecked();
                boolean politics = mPolitics.isChecked();
                boolean business = mBusiness.isChecked();
                boolean sports = mSports.isChecked();
                boolean entrepreneurs = mEntrepreneurs.isChecked();
                boolean travels = mTravels.isChecked();

                mSearch = new Search(queryTerm, displayBeginDate, displayEndDate, arts, politics, business, sports, entrepreneurs, travels);
                SharedPreferencesManager.putSearch(getBaseContext(), Constants.SEARCH_OBJECT, mSearch);

//                Log.i("TEST queryTerm", queryTerm);
//                Log.i("TEST beginDate", displayBeginDate);
//                Log.i("TEST endDate", displayEndDate);
//
//                if (arts)
//                System.out.println("TEST arts == True");
//                else
//                System.out.println("TEST arts == False");
            }
        });
    }

    public void setQueryTerm() {
        mQueryTerm = findViewById(R.id.activity_search_query_term);
        mQueryTerm.setHint("search query term");
    }

    public void setToolbar() {
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

    public void setBeginDate() {
        mDisplayBeginDate = findViewById(R.id.activity_search_start_date_edit);

        mDisplayBeginDate.setOnClickListener(new View.OnClickListener() {
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
                String date = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
                mDisplayBeginDate.setText(date);
            }
        };
    }

    public void setEndDate() {
        mDisplayEndDate = findViewById(R.id.activity_search_end_date_edit);

        mDisplayEndDate.setOnClickListener(new View.OnClickListener() {
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
                String date = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
                mDisplayEndDate.setText(date);
            }
        };
    }
}