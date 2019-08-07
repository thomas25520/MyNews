package com.mynews;

import androidx.test.rule.ActivityTestRule;

import com.mynews.controller.activities.NotificationActivity;

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
public class NotificationFunctionalTest {
    // Initialisation activity for test
    @Rule
    public ActivityTestRule<NotificationActivity> mActivityTestRule =
            new ActivityTestRule<>(NotificationActivity.class, false, true);

    @Test
    public void checkIfFragmentNotificationIsLoaded() {
        onView(withId(R.id.fragment_notification_oncePerDayBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_search_search_btn)).check(doesNotExist());
    }

    @Test
    public void checkIfNotificationCanBeEnabled() {
        onView(withId(R.id.fragment_notification_oncePerDayBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_search_search_btn)).check(doesNotExist());
    }
}

// todo faire 3eme test d'intégration
// onView(withId(R.id.button)).perform(click()) verifier le click
// onView(withId(R.id.checkbox)).check(matches(isChecked()))
// onView(withId(R.id.checkbox)).check(matches(not(isChecked())))
