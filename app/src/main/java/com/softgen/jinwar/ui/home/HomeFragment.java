package com.softgen.jinwar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softgen.jinwar.recyclerviews.PostsRecyclerViewAdapter;
import com.softgen.jinwar.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<String> postOwnersNames = new ArrayList<>();
    private List<String> postOwnersPictures = new ArrayList<>();
    private List<String> postContents = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initPosts(root);

        return root;
    }

    private void initPosts(View view){
        for(int i=0;i<20;i++){
            postOwnersNames.add("Naman Jain");
            postContents.add("This is my post number fjlksdjfkldsjfkdjslfkjdskffdfdsfdsfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdndsk hjjdsfkds jkdjf jkdjfd jkdjfkdj: "+(i+1));
            postOwnersPictures.add("https://i.redd.it/csqq92b0jq951.jpg");
        }

        initRecyclerView(view);
    }

    private void initRecyclerView(View view){
        RecyclerView newsFeed = view.findViewById(R.id.newsFeed);
        PostsRecyclerViewAdapter postsRecyclerViewAdapter = new PostsRecyclerViewAdapter(view.getContext(), postOwnersNames, postOwnersPictures, postContents);
        newsFeed.setAdapter(postsRecyclerViewAdapter);
        newsFeed.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}
