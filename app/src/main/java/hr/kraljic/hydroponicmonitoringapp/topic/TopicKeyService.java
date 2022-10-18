package hr.kraljic.hydroponicmonitoringapp.topic;

import android.content.Context;

import java.util.List;

import hr.kraljic.hydroponicmonitoringapp.models.TopicKeyDto;

public interface TopicKeyService {

    void loadTopics(String token, Context context, TopicsLoadCallback topicsLoadCallback);

    interface TopicsLoadCallback {
        void onError(String errMsg);

        void onSuccess(List<TopicKeyDto> topicKeyDtoList);
    }
}
