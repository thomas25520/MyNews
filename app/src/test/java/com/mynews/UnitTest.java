package com.mynews;

import com.mynews.utils.DateFormatter;
import com.mynews.utils.MyNotificationReceiver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitTest {
    private DateFormatter dateFormatter = new DateFormatter();

    @Test
    public void getDisplayDateFormatTest() {
        String actual = dateFormatter.getDisplayDateFormat(2019, 0, 6);
        String expected = "06/01/2019";
        assertEquals(expected, actual);
    }

    @Test
    public void getApiDateFormatTest() {
        String actual = dateFormatter.getApiDateFormat(2019, 0, 6);
        String expected = "20190106";
        assertEquals(expected, actual);
    }

    @Test
    public void getNotificationTitle() {
        MyNotificationReceiver receiver = new MyNotificationReceiver();
        String actual = receiver.getNotificationTitle(0);
        String expected = "Aucun article disponible";
        assertEquals(expected, actual);

        actual = receiver.getNotificationTitle(1);
        expected = "1 nouvel article disponible";
        assertEquals(expected, actual);

        actual = receiver.getNotificationTitle(2);
        expected = "2 nouveaux articles disponibles";
        assertEquals(expected, actual);
    }
}