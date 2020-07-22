package com.softgen.jinwar;

import android.content.Intent;
import android.os.Bundle;

import com.softgen.jinwar.recyclerviews.AvailableRoomsRecyclerViewAdapter;
import com.softgen.jinwar.recyclerviews.PostsRecyclerViewAdapter;
import com.softgen.jinwar.beans.Dharmashala;
import com.softgen.jinwar.beans.DharmashalaRoom;
import com.softgen.jinwar.ui.dharmashala.DharmashalaFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

import static com.softgen.jinwar.utils.Constants.fromTag;

public class BookDharmashalaActivity extends AppCompatActivity {
    private List<DharmashalaRoom> availableRoomsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_dharmashala);

        Intent intent = getIntent();

        if(intent.getStringExtra(fromTag).equals(FeedActivity.BOOK_DHARMASHALA_INTENT_TAG)){
            String dharmashalaID = intent.getStringExtra("dharmashala_id");
            Dharmashala selectedDharmashala = DharmashalaFragment.dharmashalaIDToDharmashala.get(dharmashalaID);

            availableRoomsList = selectedDharmashala.getDharmashalaRoomList();

            View dharmashalaView = findViewById(R.id.availableRoomsPage);

            initRecyclerView(dharmashalaView);
        }
    }

    private void initRecyclerView(View view){
        RecyclerView availableRooms = view.findViewById(R.id.availableRooms);
        AvailableRoomsRecyclerViewAdapter postsRecyclerViewAdapter = new AvailableRoomsRecyclerViewAdapter(view.getContext(), availableRoomsList);
        availableRooms.setAdapter(postsRecyclerViewAdapter);
        availableRooms.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        System.out.println("Tussi jaa rahe ho ?");
    }
}