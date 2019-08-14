package com.mynews.utils;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.mynews.R;
import com.mynews.callbacks_interfaces.SearchResponseCallBack;
import com.mynews.data.entities.search.SearchResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import static com.mynews.utils.Constants.USER_CATEGORIES;
import static com.mynews.utils.Constants.USER_QUERY;

/**
 * Created by Dutru Thomas on 14/08/2019.
 */
public class NotificationManager {
    private static final String CHANNEL_ID = "chanel_1";
    private static final int NOTIFICATION_ID = 1;
    Context mContext;
    // TODO: 14/08/2019 get in SP value of mAlreadyDisplay
    boolean mAlreadyDisplay;
    SimpleDateFormat mSdf = new SimpleDateFormat("yyyyMMdd");

    public NotificationManager(Context context) {
        mContext = context;
        mAlreadyDisplay = SharedPreferencesManager.getBoolean(context, "already_display");
    }

    public Context getContext() {
        return mContext;
    }

    public void displayNotification() {

        int day = SharedPreferencesManager.getDay(getContext(), "day");
        int month = SharedPreferencesManager.getMonth(getContext(), "month");
        // TODO: 14/08/2019 check if the SP for day & month is empty & set by default the current day & mont

        if (!isDateHasPassed(day, month) && mAlreadyDisplay) {
            // TODO: 14/08/2019 recuperer share preferences
            SharedPreferencesManager.getDay(getContext(), "day");
            SharedPreferencesManager.getMonth(getContext(), "month");

            mAlreadyDisplay = false; // reset le flag a false si les dates sont différentes
            // TODO: 14/08/2019 store in SP  value of mAlreadyDisplay
            SharedPreferencesManager.putBoolean(getContext(), "already_display", mAlreadyDisplay);
        }

        if (isHourHasPassed(9, 30) && !mAlreadyDisplay) {
            // TODO: 14/08/2019 appeler method pour afficher notification creatAndSearchNotification
            onReceive(mContext);
            mAlreadyDisplay = true;
            // TODO: 14/08/2019 store in SP  value of mAlreadyDisplay
            SharedPreferencesManager.putBoolean(getContext(), "already_display", mAlreadyDisplay);
        }
    }

    private boolean isDateHasPassed(int day, int month) {

        Calendar currentDate = Calendar.getInstance();
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int currentMonth = currentDate.get(Calendar.MONTH);

        if (month == 0 && day == 0) {
            SharedPreferencesManager.putDay(getContext(), "day", currentDay);
            SharedPreferencesManager.putMonth(getContext(), "month", currentMonth);
            return true;
        }

        if (currentDay == day && currentMonth == month) { // if is the date of the day
            return true;
        } else {
            // TODO: 14/08/2019 store in SP  value of day & month
            SharedPreferencesManager.putDay(getContext(), "day", currentDay);
            SharedPreferencesManager.putMonth(getContext(), "month", currentMonth);
            return false;
        }
    }

    private boolean isHourHasPassed(int hour, int minutes) {

        Calendar currentDate = Calendar.getInstance();
        int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
        int currentMinutes = currentDate.get(Calendar.MINUTE);

        if (currentHour >= hour && currentMinutes >= minutes) {
            return true;
        } else return currentHour >= hour;
    }

    public void onReceive(Context context) {
        mContext = context;
        String q = SharedPreferencesManager.getString(context, USER_QUERY);
        String fq = SharedPreferencesManager.getString(context, USER_CATEGORIES);
        new SearchCall().search(new SearchResponseCallBack() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                String title = getNotificationTitle(searchResponse.getMeta().getNumberOfArticles());

                createAndShowNotification(R.drawable.ic_news_logo, title, "", NotificationCompat.PRIORITY_DEFAULT, CHANNEL_ID, NOTIFICATION_ID, mContext);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("MyNotificationReceiver", throwable.toString());
            }
        }, q, fq, mSdf.format(Calendar.getInstance().getTime()), mSdf.format(Calendar.getInstance().getTime()));
    }

    // Check the spelling to display in function of nb article return by the server.
    public String getNotificationTitle(int nbArticle) {
        return nbArticle == 0 ? "Aucun article disponible" : nbArticle + ((nbArticle == 1) ? " nouvel article disponible" : " nouveaux articles disponibles");
    }

    public void createAndShowNotification(int icon, String title, String content, int notificationCompat, String channelId, int notificationId, Context context) {
        createNotificationChannel(context, channelId);
        NotificationCompat.Builder notificationBuilder = createNotification(icon, title, content, notificationCompat, channelId, context);
        showNotification(notificationId, context, notificationBuilder);
    }

    // Step 1 : Create a channel with importance by default.
    private void createNotificationChannel(Context context, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel name";
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            android.app.NotificationManager notificationManager = context.getSystemService(android.app.NotificationManager.class);

            channel.setDescription("Notification channel description");
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    // Step 2 : create the notification.
    public NotificationCompat.Builder createNotification(int icon, String title, String content, int notificationCompat, String channelId, Context context) {
        return new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setPriority(notificationCompat);
    }

    // Step 3 : Show the notification.
    public void showNotification(int notificationId, Context context, NotificationCompat.Builder notificationBuilder) {
        NotificationManagerCompat.from(context).notify(notificationId, notificationBuilder.build());
    }
}