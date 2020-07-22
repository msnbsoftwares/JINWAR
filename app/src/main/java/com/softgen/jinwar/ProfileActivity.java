package com.softgen.jinwar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softgen.jinwar.beans.AccountOwner;
import com.softgen.jinwar.recyclerviews.PostsRecyclerViewAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.softgen.jinwar.utils.Constants.empty;
import static com.softgen.jinwar.utils.Constants.fromTag;
import static com.softgen.jinwar.utils.Constants.singleSpace;

public class ProfileActivity extends AppCompatActivity {
    private String firstName;
    private String lastName;

    private List<String> postOwnersNames = new ArrayList<>();
    private List<String> postOwnersPictures = new ArrayList<>();
    private List<String> postContents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();

        // Only when we click on Account Owner pic
        if(intent.getStringExtra(fromTag).equals(FeedActivity.ACCOUNT_OWNER_INTENT_TAG)){
            JSONObject accountOwnerDetails = AccountOwner.getAccountOwner().getUserDetailsJson();

            firstName = accountOwnerDetails.optString("first_name",empty);
            lastName = accountOwnerDetails.optString("last_name",empty);

            ImageView userProfilePicture = findViewById(R.id.userProfilePicture);
            userProfilePicture.setImageResource(R.drawable.naman);

            TextView userFullName = findViewById(R.id.userFullName);
            userFullName.setText(firstName+ singleSpace +lastName);
        }else if(intent.getStringExtra(fromTag).equals(FeedActivity.USER_INTENT_TAG)){
            firstName = "Chaman";
            lastName = "Singh";

            TextView userFullName = findViewById(R.id.userFullName);
            userFullName.setText(firstName+ singleSpace +lastName);
        }

        View view = findViewById(R.id.userProfilePage);

        initUserPosts(view);
    }

    private void initUserPosts(View view){
        String post = "I'm simply saying that there is a way to be sane. I'm saying that you can get rid of all this insanity created by the past in you. Just by being a simple witness of your thought processes.\n" +
                "\n" +
                "It is simply sitting silently, witnessing the thoughts, passing before you. Just witnessing, not interfering not even judging, because the moment you judge you have lost the pure witness. The moment you say “this is good, this is bad,” you have already jumped onto the thought process.\n" +
                "\n" +
                "It takes a little time to create a gap between the witness and the mind. Once the gap is there, you are in for a great surprise, that you are not the mind, that you are the witness, a watcher.\n" +
                "\n" +
                "And this process of watching is the very alchemy of real religion. Because as you become more and more deeply rooted in witnessing, thoughts start disappearing. You are, but the mind is utterly empty.\n" +
                "\n" +
                "That’s the moment of enlightenment. That is the moment that you become for the first time an unconditioned, sane, really free human being.";

        post = "क्योंकि कोई आपको नफरत के बारे में पढ़ाता नहीं है, इसलिए नफरत एकदम शुद्ध, बिना मिलावट के रह गयी है. जब कोई आपसे नफरत करता है, आप भरोसा कर सकते हैं कि वो आपसे नफरत करता है.";

        for(int i=0;i<20;i++){
            postOwnersNames.add(firstName + singleSpace + lastName);
            postContents.add(post);
            postOwnersPictures.add("https://i.redd.it/csqq92b0jq951.jpg");
        }

        initRecyclerView(view);
    }

    private void initRecyclerView(View view){
        RecyclerView newsFeed = view.findViewById(R.id.userPosts);
        PostsRecyclerViewAdapter postsRecyclerViewAdapter = new PostsRecyclerViewAdapter(view.getContext(), postOwnersNames, postOwnersPictures, postContents);
        newsFeed.setAdapter(postsRecyclerViewAdapter);
        newsFeed.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public void getUserAbout(View view){

    }
}
