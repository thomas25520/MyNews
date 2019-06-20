package com.mynews.data.enums;

/**
 * Created by Dutru Thomas on 10/06/2019.
 */
public enum ArticleTypes {
    emails("emailed"),
    shares("shared"),
    views("viewed");

    private String name = "";

    ArticleTypes(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
