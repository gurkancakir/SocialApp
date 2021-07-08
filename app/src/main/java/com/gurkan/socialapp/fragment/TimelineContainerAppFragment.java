package com.gurkan.socialapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gurkan.socialapp.R;
import com.gurkan.socialapp.adapter.TimelineListViewAdapter;
import com.gurkan.socialapp.base.BaseAppFragment;
import com.gurkan.socialapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class TimelineContainerAppFragment extends BaseAppFragment {

    private ListView listViewTimeline;
    private TimelineListViewAdapter timelineListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_timeline_container, container, false);

        listViewTimeline  = rootView.findViewById(R.id.listTimeline);

        List<Post> exampleList = this.createExampleData();
        timelineListViewAdapter = new TimelineListViewAdapter(getActivity(), exampleList);
        listViewTimeline.setAdapter(timelineListViewAdapter);

        return rootView;
    }

    private List<Post> createExampleData() {
        List<Post> exampleList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Post post = new Post("Post Content " + i, "08.07.2021", "istanbul", "Title " + i, new ArrayList<>(), TimelineListViewAdapter.POST);
            exampleList.add(post);
        }

        return exampleList;
    }
}
