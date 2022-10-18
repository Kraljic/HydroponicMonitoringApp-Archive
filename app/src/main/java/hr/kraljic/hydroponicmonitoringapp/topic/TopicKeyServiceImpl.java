package hr.kraljic.hydroponicmonitoringapp.topic;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hr.kraljic.hydroponicmonitoringapp.api.API;
import hr.kraljic.hydroponicmonitoringapp.api.ServiceGenerator;
import hr.kraljic.hydroponicmonitoringapp.api.TopicKeyServiceApi;
import hr.kraljic.hydroponicmonitoringapp.firebase.SubscriptionManagerImpl;
import hr.kraljic.hydroponicmonitoringapp.models.TopicKeyDto;
import hr.kraljic.hydroponicmonitoringapp.util.TopicKeyManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicKeyServiceImpl implements TopicKeyService {
    @Override
    public void loadTopics(String token, Context context, TopicsLoadCallback topicsLoadCallback) {
        if (TextUtils.isEmpty(token)) {
            topicsLoadCallback.onError("Please enter token");
            return;
        }

        TopicKeyServiceApi topicKeyServiceApi = ServiceGenerator.createService(TopicKeyServiceApi.class, API.baseUrl);

        Call<List<TopicKeyDto>> call = topicKeyServiceApi.getTopics(token);

        call.enqueue(new Callback<List<TopicKeyDto>>() {
            @Override
            public void onResponse(Call<List<TopicKeyDto>> call, Response<List<TopicKeyDto>> response) {
                if (response.isSuccessful()) {
                    updateTopics(response.body(), context);
                    topicsLoadCallback.onSuccess(response.body());
                } else {
                    try {
                        topicsLoadCallback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        topicsLoadCallback.onError("onResponse: Whoops, something went wrong!");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TopicKeyDto>> call, Throwable t) {
                topicsLoadCallback.onError("onFailure: Whoops, something went wrong!");
            }
        });
    }

    private void updateTopics(List<TopicKeyDto> topics, Context context) {
        // Remove old keys
        SubscriptionManagerImpl.unsubscribeFromAllNotifications(
                TopicKeyManager.getTopicKeys(context),
                context
        );

        // Add new keys
        Set<String> topicNames = topics.stream().map(t -> t.getName()).collect(Collectors.toSet());
        Set<String> topicKeys = topics.stream().map(t -> t.getTopicKey()).collect(Collectors.toSet());

        SubscriptionManagerImpl.subscribeToNotifications(topicKeys, context);

        // Preserve new keys
        TopicKeyManager.setTopics(context, topicNames);
        TopicKeyManager.setTopicKeys(context, topicKeys);
    }
}
