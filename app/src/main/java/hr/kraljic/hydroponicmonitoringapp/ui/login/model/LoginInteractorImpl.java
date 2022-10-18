package hr.kraljic.hydroponicmonitoringapp.ui.login.model;

import android.content.Context;

import hr.kraljic.hydroponicmonitoringapp.topic.TopicKeyService;
import hr.kraljic.hydroponicmonitoringapp.topic.TopicKeyServiceImpl;

public class LoginInteractorImpl implements LoginInteractor {

    public final TopicKeyService topicKeyService;

    public LoginInteractorImpl() {
        this.topicKeyService = new TopicKeyServiceImpl();
    }

    @Override
    public void login(final String token, Context context, final TopicKeyService.TopicsLoadCallback listener) {
        topicKeyService.loadTopics(token, context, listener);
    }

}
