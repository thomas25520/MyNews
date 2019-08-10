package com.mynews.data.enums;

/**
 * Created by Dutru Thomas on 10/06/2019.
 */
public enum Section {
    arts("arts"),
    automobiles("automobiles"),
    books("books"),
    business("business"),
    fashion("fashion"),
    food("food"),
    health("health"),
    home("home"),
    insider("insider"),
    magazine("magazine"),
    movies("movies"),
    national("national"),
    nyregion("nyregion"),
    obituaries("obituaries"),
    opinion("opinion"),
    politics("politics"),
    realestate("realestate"),
    science("science"),
    sports("sports"),
    sundayreview("sundayreview"),
    technology("technology"),
    theater("theater"),
    tmagazine("tmagazine"),
    travel("travel"),
    upshot("upshot"),
    world("world");

    private String name;

    Section(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}