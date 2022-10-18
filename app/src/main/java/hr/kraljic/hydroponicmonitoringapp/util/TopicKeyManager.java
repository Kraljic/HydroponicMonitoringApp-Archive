package hr.kraljic.hydroponicmonitoringapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class TopicKeyManager {
    private static final String TOPICS = " hr.kraljic.hydroponicmonitoringapp.topics";

    public static void setTopics(Context context, Set<String> topics) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(TOPICS, Context.MODE_PRIVATE);

        sharedpreferences.edit()
                .putStringSet("topics", topics)
                .apply();
    }

    public static Set<String> getTopics(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(TOPICS, Context.MODE_PRIVATE);

        Set<String> topics = sharedpreferences.getStringSet("topics", null);

        return topics;
    }

    public static void setTopicKeys(Context context, Set<String> keys) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(TOPICS, Context.MODE_PRIVATE);

        sharedpreferences.edit()
                .putStringSet("keys", keys)
                .apply();
    }

    public static Set<String> getTopicKeys(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(TOPICS, Context.MODE_PRIVATE);

        Set<String> topics = sharedpreferences.getStringSet("keys", null);

        return topics;
    }

    public static void clear(Context context) {
        context.deleteSharedPreferences(TOPICS);
    }

}
