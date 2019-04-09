package project1.mobile.cs.fsu.edu.project1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

public class NotificationHelper extends ContextWrapper {
    private NotificationManager manager;
    public static final String PRIMARY_CHANNEL = "default";


    public NotificationHelper(Context base) {
        super(base);

        NotificationChannel chan1 = new NotificationChannel(PRIMARY_CHANNEL, "Primary Channel", NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(chan1);

    }

    public Notification.Builder getNotification1(String title, String body) {
        return new Notification.Builder(getApplicationContext(), PRIMARY_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setAutoCancel(true);


    }

    public void notify(int id, Notification.Builder notification) {
        getManager().notify(id, notification.build());
    }

    private NotificationManager getManager(){
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return manager;
    }
}
