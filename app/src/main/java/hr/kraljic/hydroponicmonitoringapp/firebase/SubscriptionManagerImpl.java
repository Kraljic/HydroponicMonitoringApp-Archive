package hr.kraljic.hydroponicmonitoringapp.firebase;

import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Set;

import hr.kraljic.hydroponicmonitoringapp.util.TokenManager;

public class SubscriptionManagerImpl {
    public static void subscribeToNotifications(Set<String> newTopics, Context context) {
        subscribeToDefaultNotifications(context);

        if (newTopics != null) {
            for (String topic : newTopics) {
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
            }
        }
    }

    public static void unsubscribeFromAllNotifications(Set<String> oldTopics, Context context) {
        unsubscribeToDefaultNotifications(context);

        if (oldTopics != null) {
            for (String topic : oldTopics) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
            }
        }
    }

    private static void subscribeToDefaultNotifications(Context context) {
        FirebaseMessaging.getInstance().subscribeToTopic(TokenManager.getToken(context));

        FirebaseMessaging.getInstance().subscribeToTopic(NotificationService.TOPIC_KEY_UPDATE_EVENT);
    }

    private static void unsubscribeToDefaultNotifications(Context context) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(TokenManager.getToken(context));

        FirebaseMessaging.getInstance().unsubscribeFromTopic(NotificationService.TOPIC_KEY_UPDATE_EVENT);
    }
}
