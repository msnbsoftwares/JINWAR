package com.softgen.jinwar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.softgen.jinwar.beans.AccountOwner;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONObject;
import static com.softgen.jinwar.utils.Constants.empty;
import static com.softgen.jinwar.utils.Constants.fromTag;
import static com.softgen.jinwar.utils.Constants.singleSpace;

public class FeedActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    public static String INTENT_TAG = "FeedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Setting profile picture
        View headerView = navigationView.getHeaderView(0);
        ImageView profilePic = (ImageView)headerView.findViewById(R.id.profilePicOfAccountOwner);
        profilePic.setImageResource(R.drawable.naman);

        Intent intent = getIntent();

        if(intent.getStringExtra(fromTag).equals(MainActivity.INTENT_TAG)){
            JSONObject userDetails;
            String firstName, lastName, memberid;

            userDetails = AccountOwner.getAccountOwner().getUserDetailsJson();

            firstName = userDetails.optString("first_name",empty);
            lastName = userDetails.optString("last_name", empty);
            memberid = userDetails.optString("member_id", empty);


            TextView name = (TextView) headerView.findViewById(R.id.nameOfAccountOwner);
            name.setText(firstName+singleSpace+lastName);

            TextView positionOfUser = (TextView) headerView.findViewById(R.id.typeOfAccountOwner);
            positionOfUser.setText("Member ID : "+memberid);
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void getProfilePage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(fromTag, INTENT_TAG);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
