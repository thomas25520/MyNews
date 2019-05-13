package com.MyNews.controller.activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.MyNews.R;

import java.util.Calendar;
import java.util.Objects;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchActivity extends AppCompatActivity {
    EditText mQueryTerm;

    private TextView mDisplayEndDate;
    private TextView mDisplayBeginDate;

    private DatePickerDialog.OnDateSetListener mDateSetListenerEnd;
    private DatePickerDialog.OnDateSetListener mDateSetListenerBegin;

    private String monthToDisplay = "";
    private String dayToDisplay = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        initViews();
        setBeginDate();
        setEndDate();
    }

    public void initViews() {
        setToolbar();
        setQueryTerm();
    }

    public void setQueryTerm() {
        mQueryTerm = findViewById(R.id.query_term);
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
        mDisplayBeginDate = findViewById(R.id.activity_search_begin_date);


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

                // Display date to format mm/dd/yyyy
                if (month < 10) {
                    monthToDisplay = "0";
                }
                if (day < 10) {
                    dayToDisplay = "0";
                }

                String date = monthToDisplay + month + "/" + dayToDisplay + day + "/" + year;
                mDisplayBeginDate.setText(date);

                // Erase "0" after the day has displayed
                monthToDisplay = "";
                dayToDisplay = "";
            }
        };
    }

    public void setEndDate() {
        mDisplayEndDate = findViewById(R.id.activity_search_end_date);

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
                // Display date to format mm/dd/yyyy
                if (month < 10) {
                    monthToDisplay = "0";
                }
                if (day < 10) {
                    dayToDisplay = "0";
                }

                String date = monthToDisplay + month + "/" + dayToDisplay + day + "/" + year;
                mDisplayEndDate.setText(date);

                // Erase "0" after the day has displayed
                monthToDisplay = "";
                dayToDisplay = "";
            }
        };
    }
}