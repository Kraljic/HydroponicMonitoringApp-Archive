package hr.kraljic.hydroponicmonitoringapp.firebase;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import hr.kraljic.hydroponicmonitoringapp.R;
import hr.kraljic.hydroponicmonitoringapp.models.TopicKeyDto;
import hr.kraljic.hydroponicmonitoringapp.topic.TopicKeyService;
import hr.kraljic.hydroponicmonitoringapp.topic.TopicKeyServiceImpl;
import hr.kraljic.hydroponicmonitoringapp.ui.navigation.view.NavigationActivity;
import hr.kraljic.hydroponicmonitoringapp.util.TokenManager;

public class NotificationService extends FirebaseMessagingService {
    public static final String TOPIC_KEY_UPDATE_EVENT = "TOPIC_KEY_UPDATE_EVENT";
    private static final String TAG = "FCMService";
    private final TopicKeyService topicKeyService;

    public NotificationService() {
        this.topicKeyService = new TopicKeyServiceImpl();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        Context applicationContext = getApplicationContext();

        if (remoteMessage.getData().containsKey(TOPIC_KEY_UPDATE_EVENT)) {
            topicKeyService.loadTopics(TokenManager.getToken(applicationContext), applicationContext, new TopicKeyService.TopicsLoadCallback() {
                @Override
                public void onError(String errMsg) {
                    Log.d(TAG, "Error topics!");
                    showNotification("Topics update", "Error on updating topics.");
                }

                @Override
                public void onSuccess(List<TopicKeyDto> topicKeyDtoList) {
                    Log.d(TAG, "Updating topics!");
                    Log.d(TAG, topicKeyDtoList.toString());

                    showNotification("Topics update", "Topics update was successful.");
                }
            });
        } else if (remoteMessage.getData().containsKey(TokenManager.getToken(applicationContext))) {

            topicKeyService.loadTopics(TokenManager.getToken(applicationContext), applicationContext, new TopicKeyService.TopicsLoadCallback() {
                @Override
                public void onError(String errMsg) {
                    Log.d(TAG, "Error topics!");
                    showNotification("Topics update", "Error on updating user topics.");
                }

                @Override
                public void onSuccess(List<TopicKeyDto> topicKeyDtoList) {
                    Log.d(TAG, "Updating topics!");
                    Log.d(TAG, topicKeyDtoList.toString());

                    showNotification("Topics update", "User topics update was successful.");
                }
            });
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }


    private void showNotification(String title, String message) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NavigationActivity.NOTIFICATION_CHANNEL)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
