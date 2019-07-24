package com.mynews;

import com.mynews.controller.fragment.SearchAndNotificationFragment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    SearchAndNotificationFragment mSearchAndNotificationFragment = new SearchAndNotificationFragment();

    @Test
    public void getDisplayDateFormatTest() {
        String actual = mSearchAndNotificationFragment.getDisplayDateFormat(2019, 0, 6);
        String expected = "06/01/2019";
        assertEquals(expected, actual);
    }

    @Test
    public void getApiDateFormatTest() {
        String actual = mSearchAndNotificationFragment.getApiDateFormat(2019, 0, 6);
        String expected = "20190106";
        assertEquals(expected, actual);
    }
}