package com.mynews.controller.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.controller.fragment.SearchFragment;
import com.mynews.data.entities.search.Meta;
import com.mynews.data.entities.search.SearchResponse;

import java.util.List;

/**
 * Created by Dutru Thomas on 28/06/2019.
 */
public class NotificationActivity extends AppCompatActivity implements RootSearchCallBack {
    public EditText mQuery;
    public Switch mOncePerDayBtn;
    private List<Meta> mMetaList = null;
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
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_notification, searchFragment).commit();

//        mQuery = findViewById(R.id.notification_search_query_term);
//        mArts = findViewById(R.id.notification_search_checkBox_arts);
//        mPolitics = findViewById(R.id.notification_search_checkBox_politics);
//        mBusiness = findViewById(R.id.notification_search_checkBox_business);
//        mSports = findViewById(R.id.notification_search_checkBox_sports);
//        mEntrepreneurs = findViewById(R.id.notification_search_checkBox_entrepreneurs);
//        mTravels = findViewById(R.id.notification_search_checkBox_travels);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Action when arrow back button is click

                getUserInput();

//                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getUserInput() { // todo : tout est null rien n'est récupérer FAIL TOTAL
        String queryNotif = mQuery.getText().toString(); // get user query notification
        String section = getSection(); // get user section checked in string format
        mOncePerDayBtn.isChecked(); // user enable notification with oncePerDayBtn

//        if (queryNotif.isEmpty()) // Check query is set
//            Toast.makeText(getBaseContext(), "Merci d'entrer un mot-clé", Toast.LENGTH_LONG).show();

        // todo : la partie récupération nombre de page ne doit pas etre dans getUserInput pas logique
//        final Meta meta = mMetaList.get(0); // number of article available
//        meta.getHits(); // get number of article available
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