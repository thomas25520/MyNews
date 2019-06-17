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

    // Setter
    public void setQueryTerm(String queryTerm) {
        mQueryTerm = queryTerm;
    }

    public String getBeginDate() {
        return mBeginDate;
    }

    public void setBeginDate(String beginDate) {
        this.mBeginDate = beginDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public boolean isCheckboxArts() {
        return mCheckboxArts;
    }

    public void setCheckboxArts(boolean checkboxArts) {
        mCheckboxArts = checkboxArts;
    }

    public boolean isCheckboxPolitics() {
        return mCheckboxPolitics;
    }

    public void setCheckboxPolitics(boolean checkboxPolitics) {
        mCheckboxPolitics = checkboxPolitics;
    }

    public boolean isCheckboxBusiness() {
        return mCheckboxBusiness;
    }

    public void setCheckboxBusiness(boolean checkboxBusiness) {
        mCheckboxBusiness = checkboxBusiness;
    }

    public boolean isCheckboxSports() {
        return mCheckboxSports;
    }

    public void setCheckboxSports(boolean checkboxSports) {
        mCheckboxSports = checkboxSports;
    }

    public boolean isCheckboxEntrepreneurs() {
        return mCheckboxEntrepreneurs;
    }

    public void setCheckboxEntrepreneurs(boolean checkboxEntrepreneurs) {
        mCheckboxEntrepreneurs = checkboxEntrepreneurs;
    }

    public boolean isCheckboxTravels() {
        return mCheckboxTravels;
    }

    public void setCheckboxTravels(boolean checkboxTravels) {
        mCheckboxTravels = checkboxTravels;
    }

    /**
     * Transform mood object to Json
     *
     * @return mood as json string
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