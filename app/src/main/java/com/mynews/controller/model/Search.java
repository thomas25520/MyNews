package com.mynews.controller.model;

import com.google.gson.Gson;

/**
 * Created by Dutru Thomas on 14/06/2019.
 */
public class Search {
    private String mQueryTerm, mBeginDate, mEndDate;
    private boolean mCheckboxArts, mCheckboxPolitics, mCheckboxBusiness, mCheckboxSports, mCheckboxEntrepreneurs, mCheckboxTravels;

    // Constructors
    private Search(Search search) {
        this.mQueryTerm = search.getQueryTerm();
        this.mBeginDate = search.getBeginDate();
        this.mEndDate = search.getEndDate();
        this.mCheckboxArts = search.isCheckboxArts();
        this.mCheckboxPolitics = search.isCheckboxPolitics();
        this.mCheckboxBusiness = search.isCheckboxBusiness();
        this.mCheckboxSports = search.isCheckboxSports();
        this.mCheckboxEntrepreneurs = search.isCheckboxEntrepreneurs();
        this.mCheckboxTravels = search.isCheckboxTravels();
    }

    public Search(String queryTerm, String beginDate, String endDate, boolean arts, boolean politics, boolean business, boolean sports, boolean entrepreneurs, boolean travels) {
        mQueryTerm = queryTerm;
        mBeginDate = beginDate;
        mEndDate = endDate;
        mCheckboxArts = arts;
        mCheckboxPolitics = politics;
        mCheckboxBusiness = business;
        mCheckboxSports = sports;
        mCheckboxEntrepreneurs = entrepreneurs;
        mCheckboxTravels = travels;
    }

    // json constructor
    public Search(String json) {
        Gson gson = new Gson();
        new Search(gson.fromJson(json, Search.class));
    }

    public Search() {
    }

    // Getter
    public String getQueryTerm() {
        return mQueryTerm;
    }

    public String getBeginDate() {
        return mBeginDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public boolean isCheckboxArts() {
        return mCheckboxArts;
    }

    public boolean isCheckboxPolitics() {
        return mCheckboxPolitics;
    }

    public boolean isCheckboxBusiness() {
        return mCheckboxBusiness;
    }

    public boolean isCheckboxSports() {
        return mCheckboxSports;
    }

    public boolean isCheckboxEntrepreneurs() {
        return mCheckboxEntrepreneurs;
    }

    public boolean isCheckboxTravels() {
        return mCheckboxTravels;
    }

    // Setter
    public void setQueryTerm(String queryTerm) {
        mQueryTerm = queryTerm;
    }
    public void setBeginDate(String beginDate) {
        this.mBeginDate = beginDate;
    }
    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }
    public void setCheckboxArts(boolean checkboxArts) {
        mCheckboxArts = checkboxArts;
    }
    public void setCheckboxPolitics(boolean checkboxPolitics) {
        mCheckboxPolitics = checkboxPolitics;
    }
    public void setCheckboxBusiness(boolean checkboxBusiness) {
        mCheckboxBusiness = checkboxBusiness;
    }
    public void setCheckboxSports(boolean checkboxSports) {
        mCheckboxSports = checkboxSports;
    }
    public void setCheckboxEntrepreneurs(boolean checkboxEntrepreneurs) {
        mCheckboxEntrepreneurs = checkboxEntrepreneurs;
    }
    public void setCheckboxTravels(boolean checkboxTravels) {
        mCheckboxTravels = checkboxTravels;
    }

    /**
     * Transform object to Json
     *
     * @return object as json string
     */
    public String formatToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Transform json string to object
     *
     * @param json
     * @return json string as object
     */
    public Search jsonToSearch(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Search.class);
    }
}