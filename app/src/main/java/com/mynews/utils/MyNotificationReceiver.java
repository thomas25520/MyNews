package com.mynews.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.data.entities.search.SearchResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import static com.mynews.utils.Constants.USER_CATEGORIES;
import static com.mynews.utils.Constants.USER_QUERY;

/**
 * Created by Dutru Thomas on 27/06/2019.
 */

public class MyNotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "chanel_1";
    private static final int NOTIFICATION_ID = 1;

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
//                    createNotification(getNotificationTitle(searchResponse.getMeta().getNumberOfArticles()));
                callNotification(searchResponse.getMeta().getNumberOfArticles());
            }

            @Override
            public void onFailure() {
                // FIXME: 06/08/2019 take parameter eg: exception or String with error msg
            }
        }, q, fq, sdf.format(Calendar.getInstance().getTime()), sdf.format(Calendar.getInstance().getTime()));
    }

    // Check the spelling to display in function of nb article return by the server.
    private void callNotification(int nbArticle) {
        String title;
        switch (nbArticle) {
            case 0:
                title = "aucun article";
                break;
            default:
                title = nbArticle + ((nbArticle == 1) ? " nouveau article disponible" : " nouveaux articles disponibles");
                break;
        }
        createAndShowNotification(R.drawable.ic_news_logo, title, "", NotificationCompat.PRIORITY_DEFAULT, CHANNEL_ID, NOTIFICATION_ID, context);
    }

    // Supplementary method for creating and display notification with some of parameters.
    public void createAndShowNotification(int icon, String title, String content, int notificationCompat, String channelId, int notificationId, Context context) {
        createNotificationChannel(context, channelId);
        NotificationCompat.Builder notificationBuilder = createNotification(icon, title, content, notificationCompat, channelId, context);
        showNotification(notificationId, context, notificationBuilder);
    }

    // Step 1 : Create a channel with importance by default.
    private void createNotificationChannel(Context context, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel name";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

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

///////////////////////////// OLD WORK ///////////////////////////////////////////////////////////////////////////////////
//    private void createNotification(String title) {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_news_logo)
//                .setContentTitle(title) // Set notification title
//                .setAutoCancel(true)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        CharSequence name = "chanel_1";
//        String description = "Number of articles available";
//        int importance = NotificationManager.IMPORTANCE_DEFAULT;
//        NotificationChannel channel;
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//
//            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//        // Display notification
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
//        notificationManager.notify(NOTIFICATION_ID, builder.build()); // Id is unique for each notification
//    }
//
//    public String getNotificationTitle(int nbArticles) {
//        String title;
//        if (nbArticles == 0)
//            title = "Aucun article disponible";
//        else if (nbArticles == 1)
//            title = "1 nouvel article disponible";
//        else title = nbArticles + " nouveaux articles disponibles";
//
//        return title;
//    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

