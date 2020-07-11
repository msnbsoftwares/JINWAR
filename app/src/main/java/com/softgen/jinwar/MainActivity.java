package com.softgen.jinwar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.softgen.jinwar.beans.AccountOwner;
import com.softgen.jinwar.beans.User;
import com.softgen.jinwar.network.RequestManager;
import com.softgen.jinwar.network.URLPaths;
import com.softgen.jinwar.network.VolleyResponseListener;

import org.json.JSONException;
import org.json.JSONObject;
import static com.softgen.jinwar.utils.Constants.fromTag;

public class MainActivity extends AppCompatActivity {
    public static String INTENT_TAG = "MainActivity";
    Intent intentToFeedActivity;

    public void loginIntoApp(View view){
        intentToFeedActivity = new Intent(this, FeedActivity.class);;
        final TextView username = findViewById(R.id.username);
        final TextView password = findViewById(R.id.password);

        System.out.println(username.getText()+" "+password.getText());

        JSONObject headers = new JSONObject();

        try {
            headers.put("user", username.getText());
            headers.put("pass", password.getText());

            System.out.println(username.getText()+" : "+password.getText());

            RequestManager.getInstance(this).executeSyncPostRequest(URLPaths.USERAUTHENTICATION, headers, new VolleyResponseListener() {
                @Override
                public void onResponse(JSONObject response) {
                    if(response!=null){
                        JSONObject userDetails = new JSONObject();
                        try {
                            userDetails = new JSONObject(response.getString("login_result"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                        User user = AccountOwner.getAccountOwner(userDetails);

                        System.out.println(user.getUserDetailsJson());

                        if(user.isValidUser()){
                            intentToFeedActivity.putExtra(fromTag, INTENT_TAG);
                            startActivity(intentToFeedActivity);
                        }else{
                            TextView invalidCredentials = (TextView) findViewById(R.id.incorrect_credentials);
                            invalidCredentials.setText("Invalid Credentials...");
                        }
                    }else{

                    }
                }

                @Override
                public void onError(String message) {
                    System.out.println("Error aaya hai babu..");
                }
            });

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
