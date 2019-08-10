package com.mynews;

import android.view.View;
import android.widget.Checkable;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;

import com.mynews.controller.activities.NotificationActivity;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.core.IsNot.not;

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

    // Used to change checkbox states
    public static ViewAction setChecked(final boolean checked) {
        return new ViewAction() {
            @Override
            public BaseMatcher<View> getConstraints() {
                return new BaseMatcher<View>() {
                    @Override
                    public boolean matches(Object item) {
                        return isA(Checkable.class).matches(item);
                    }

                    @Override
                    public void describeMismatch(Object item, Description mismatchDescription) {
                    }

                    @Override
                    public void describeTo(Description description) {
                    }
                };
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View view) {
                Checkable checkableView = (Checkable) view;
                checkableView.setChecked(checked);
            }
        };
    }

    @Test
    public void checkIfNotificationCanBeEnabledDependingOnQuery() {
        // Init random categories
        onView(withId(R.id.fragment_notification_checkBox_politics)).perform(setChecked(true));
        // Query empty
        // Init query to empty
        // Verify switch button is not checked
        onView(withId(R.id.fragment_notification_query_term)).perform(click(), replaceText(""));
        onView(withId(R.id.fragment_notification_oncePerDayBtn)).perform(click());
        onView(withId(R.id.fragment_notification_oncePerDayBtn)).check(matches(not(isChecked())));

        // Query not empty
        // Verify switch button is checked
        onView(withId(R.id.fragment_notification_query_term)).perform(click(), replaceText("Engineer"));
        onView(withId(R.id.fragment_notification_oncePerDayBtn)).perform(click());
        onView(withId(R.id.fragment_notification_oncePerDayBtn)).check(matches(isChecked()));
    }
}