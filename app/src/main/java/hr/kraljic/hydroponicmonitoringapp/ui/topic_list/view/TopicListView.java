package hr.kraljic.hydroponicmonitoringapp.ui.topic_list.view;

import java.util.Set;

public interface TopicListView {
    void showError(String errMsg);

    void updateTopicList(Set<String> topic);
}
