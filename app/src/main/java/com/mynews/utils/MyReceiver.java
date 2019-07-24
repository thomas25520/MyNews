package com.mynews.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.data.entities.search.SearchResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.mynews.utils.Constants.USER_CATEGORIES;
import static com.mynews.utils.Constants.USER_QUERY;

/**
 * Created by Dutru Thomas on 27/06/2019.
 */
public class MyReceiver extends BroadcastReceiver {
    Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        String q = SharedPreferencesManager.getString(context, USER_QUERY);
        String fq = SharedPreferencesManager.getString(context, USER_CATEGORIES);
        new SearchCall().search(new RootSearchCallBack() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                createNotification(searchResponse.getMeta().getNumberOfArticles() + " nouveaux articles disponibles", "");
            }

            @Override
            public void onFailure() {

            }
        }, q, fq, sdf.format(Calendar.getInstance().getTime()), sdf.format(Calendar.getInstance().getTime()));

        // todo faire test unitaire la dessus
    }

    private void createNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "chanel_1")
                .setSmallIcon(R.drawable.ic_news_logo)
                .setContentTitle(title) // Set notification title
                .setContentText(message) // Set notification message
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        CharSequence name = "chanel_1";
        String description = "Number of articles available";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("chanel_1", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        // Display notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build()); // Id is unique for each notification
    }
}