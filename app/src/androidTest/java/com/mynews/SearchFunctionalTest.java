package com.mynews;

import androidx.test.rule.ActivityTestRule;

import com.mynews.controller.activities.SearchActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SearchFunctionalTest {
    // Initialisation activity for test
    @Rule
    public ActivityTestRule<SearchActivity> mActivityTestRule =
            new ActivityTestRule<>(SearchActivity.class, false, true);

    @Test
    public void checkIfFragmentSearchIsLoaded() {
        onView(withId(R.id.fragment_search_search_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_notification_oncePerDayBtn)).check(doesNotExist());
    }
}