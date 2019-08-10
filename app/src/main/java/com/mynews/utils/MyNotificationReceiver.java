package com.mynews.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
 * Created by Dutru Thomas on 27/06/2019.
 */

public class MyNotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "chanel_1";
    private static final int NOTIFICATION_ID = 1;

    Context mContext;
    SimpleDateFormat mSdf = new SimpleDateFormat("yyyyMMdd");

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        String q = SharedPreferencesManager.getString(context, USER_QUERY);
        String fq = SharedPreferencesManager.getString(context, USER_CATEGORIES);
        new SearchCall().search(new SearchResponseCallBack() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                callNotification(searchResponse.getMeta().getNumberOfArticles());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("MyNotificationReceiver", throwable.toString());
            }
        }, q, fq, mSdf.format(Calendar.getInstance().getTime()), mSdf.format(Calendar.getInstance().getTime()));
    }

    // Check the spelling to display in function of nb article return by the server.
    private void callNotification(int nbArticle) {
// Test ternaire
//        String tmp1, tmp2 = "";
//        if (nbArticle == 0) {
//            tmp1 = "aucun article";
//        } else {
//            tmp1 = "" + nbArticle;
//            if (nbArticle == 1) {
//                tmp2 = " nouveau article disponible";
//            } else {
//                tmp2 = " nouveaux articles disponibles";
//            }
//        }
//        String tmp = tmp1 + tmp2;

        String title = nbArticle == 0 ? "aucun article" : nbArticle + ((nbArticle == 1) ? " nouveau article disponible" : " nouveaux articles disponibles");
        createAndShowNotification(R.drawable.ic_news_logo, title, "", NotificationCompat.PRIORITY_DEFAULT, CHANNEL_ID, NOTIFICATION_ID, mContext);
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