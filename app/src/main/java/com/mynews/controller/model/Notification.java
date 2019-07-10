package com.mynews.controller.model;

import com.google.gson.Gson;

/**
 * Created by Dutru Thomas on 08/07/2019.
 */
public class Notification {
    private String mQueryTerm;
    private boolean mCheckboxArts, mCheckboxPolitics, mCheckboxBusiness, mCheckboxSports, mCheckboxEntrepreneurs, mCheckboxTravels;

    // Constructors
    private Notification(Notification notification) {

        this.mQueryTerm = notification.getQueryTerm();
        this.mCheckboxArts = notification.isCheckboxArts();
        this.mCheckboxPolitics = notification.isCheckboxPolitics();
        this.mCheckboxBusiness = notification.isCheckboxBusiness();
        this.mCheckboxSports = notification.isCheckboxSports();
        this.mCheckboxEntrepreneurs = notification.isCheckboxEntrepreneurs();
        this.mCheckboxTravels = notification.isCheckboxTravels();
    }

    public Notification(String queryTerm, boolean arts, boolean politics, boolean business, boolean sports, boolean entrepreneurs, boolean travels) {
        mQueryTerm = queryTerm;
        mCheckboxArts = arts;
        mCheckboxPolitics = politics;
        mCheckboxBusiness = business;
        mCheckboxSports = sports;
        mCheckboxEntrepreneurs = entrepreneurs;
        mCheckboxTravels = travels;
    }

    // json constructor
    public Notification(String json) {
        Gson gson = new Gson();
        new Notification(gson.fromJson(json, Notification.class));
    }

    public Notification() {
    }

    // Getter
    public String getQueryTerm() {
        return mQueryTerm;
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
    public Notification jsonToNotification(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Notification.class);
    }
}