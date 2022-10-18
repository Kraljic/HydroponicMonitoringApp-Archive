package hr.kraljic.hydroponicmonitoringapp.ui.topic_list.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import hr.kraljic.hydroponicmonitoringapp.R;
import hr.kraljic.hydroponicmonitoringapp.ui.topic_list.presenter.TopicListPresenter;
import hr.kraljic.hydroponicmonitoringapp.ui.topic_list.presenter.TopicListPresenterImpl;
import hr.kraljic.hydroponicmonitoringapp.ui.recyclerview_adapter.TopicListAdapter;

public class TopicListFragment extends Fragment implements TopicListView {

    @BindView(R.id.txtErrorMsg)
    TextView txtErrorMsg;
    @BindView(R.id.listTopics)
    RecyclerView listTopics;

    private TopicListPresenter topicListPresenter;
    private List<String> topics = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_list, container, false);

        ButterKnife.bind(this, view);

        TopicListAdapter adapter = new TopicListAdapter(this.getContext(), this.topics);
        listTopics.setAdapter(adapter);
        listTopics.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topicListPresenter = new TopicListPresenterImpl(this.getContext(), this);
        topicListPresenter.getTopics();
    }

    @Override
    public void updateTopicList(Set<String> topics) {
        this.topics.clear();
        this.topics.addAll(topics);

        this.listTopics.getAdapter().notifyDataSetChanged();

        txtErrorMsg.setVisibility(View.GONE);
        listTopics.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String errMsg) {
        txtErrorMsg.setText(errMsg);
        txtErrorMsg.setVisibility(View.VISIBLE);
        listTopics.setVisibility(View.GONE);
        Log.e("TOPIC LIST", "showError: " + errMsg);
    }


}
