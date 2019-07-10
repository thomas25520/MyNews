package com.mynews.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.controller.fragment.SearchFragment;
import com.mynews.data.entities.search.Docs;
import com.mynews.data.entities.search.Meta;
import com.mynews.data.entities.search.SearchResponse;
import com.mynews.data.entities.top_stories_most_popular_other.Result;
import com.mynews.utils.SearchCall;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Dutru Thomas on 28/06/2019.
 */
public class NotificationActivity extends AppCompatActivity implements RootSearchCallBack {

    private List<Meta> mMetaList = null;

    public EditText mQuery;
    public Switch mOncePerDayBtn;
    private CheckBox mArts;
    private CheckBox mPolitics;
    private CheckBox mBusiness;
    private CheckBox mSports;
    private CheckBox mEntrepreneurs;
    private CheckBox mTravels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification); // While fragment no view
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true); // active arrow back

        SearchFragment searchFragment = SearchFragment.newInstance("NotificationActivity");
        getSupportFragmentManager().beginTransaction().add(R.id.activity_search_notification_frame_layout, searchFragment).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Action when arrow back button is click
                getUserInput();

                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initView() {
        mQuery = findViewById(R.id.fragment_notification_query_term);
        mOncePerDayBtn = findViewById(R.id.fragment_notification_oncePerDayBtn);

        mArts = findViewById(R.id.fragment_notification_checkBox_arts);
        mPolitics = findViewById(R.id.fragment_notification_checkBox_politics);
        mBusiness = findViewById(R.id.fragment_notification_checkBox_business);
        mSports = findViewById(R.id.fragment_notification_checkBox_sports);
        mEntrepreneurs = findViewById(R.id.fragment_notification_checkBox_entrepreneurs);
        mTravels = findViewById(R.id.fragment_notification_checkBox_travels);
    }

    public void getUserInput() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        mQuery.getText().toString();
        getSection();
        mOncePerDayBtn.isChecked();

        // User Error handling
        if (mQuery.getText().toString().isEmpty()) // Check query
            Toast.makeText(getBaseContext(), "Merci d'entrer un mot-clé", Toast.LENGTH_LONG).show();
        else if (!mArts.isChecked() && !mPolitics.isChecked() && !mBusiness.isChecked() && !mSports.isChecked() && !mEntrepreneurs.isChecked() && !mTravels.isChecked()) { // Checks that at least one category is checked
            Toast.makeText(getBaseContext(), "Merci de cocher au moins une catégorie.", Toast.LENGTH_LONG).show();
        }

        final Meta meta = mMetaList.get(0); // number of article available
        meta.getHits(); // todo pas de callback, list null crash !!

//        else
//            new SearchCall().search(this, mQuery.getText().toString(), getSection(), Integer.valueOf(sdf.format(Calendar.getInstance().getTime())).toString(), Integer.valueOf(sdf.format(Calendar.getInstance().getTime())).toString());

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

    @Override
    public void onResponse(SearchResponse searchResponse) {
//        Intent intent = new Intent(this, DisplaySearchActivity.class);
//        intent.putExtra("searchResponse", searchResponse.toJson()); // put string object converted with json
//        startActivity(intent);
    }

    @Override
    public void onFailure() {
        // todo : throw the error
    }
}