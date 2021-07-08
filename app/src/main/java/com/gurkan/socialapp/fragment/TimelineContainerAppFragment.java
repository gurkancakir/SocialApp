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
import java.util.Collections;
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

        List<List<String>> imageList = new ArrayList<>();
        imageList.add(new ArrayList<>());
        List<String> tempList = new ArrayList<>();
        tempList.add("https://filesamples.com/samples/image/jpeg/sample_640%C3%97426.jpeg");
        tempList.add("https://rtlimages.apple.com/cmc/dieter/store/16_9/R588.png?resize=1440:806&output-format=jpg&output-quality=85&interpolation=progressive-bicubic");
        imageList.add(tempList);
        imageList.add(Collections.singletonList("https://rtlimages.apple.com/cmc/dieter/store/16_9/R583.png?resize=1440:806&output-format=jpg&output-quality=85&interpolation=progressive-bicubic"));
        imageList.add(Collections.singletonList("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY3L-iWl6Z0egKY0vfVXj83273ij3whP5iwQ&usqp=CAU"));
        imageList.add(Collections.singletonList("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6Ur6Qh6E3oohXCjZwhf271YpeQtGLDrw0OQ&usqp=CAU"));

        for (int i = 0; i < 5; i++) {
            Post post = new Post("Post Content " + i, "08.07.2021", "istanbul", "Title " + i,
                    imageList.get(i), TimelineListViewAdapter.POST);
            exampleList.add(post);
        }

        return exampleList;
    }
}
