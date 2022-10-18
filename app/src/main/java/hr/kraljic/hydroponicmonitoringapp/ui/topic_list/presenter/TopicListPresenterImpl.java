package hr.kraljic.hydroponicmonitoringapp.ui.topic_list.presenter;

import android.content.Context;

import java.util.Set;

import hr.kraljic.hydroponicmonitoringapp.ui.topic_list.view.TopicListView;
import hr.kraljic.hydroponicmonitoringapp.util.TopicKeyManager;

public class TopicListPresenterImpl implements TopicListPresenter {

    private Context context;
    private TopicListView view;

    public TopicListPresenterImpl(Context context, TopicListView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getTopics() {
        Set<String> topics = TopicKeyManager.getTopics(context);
        view.updateTopicList(topics);
    }
}
