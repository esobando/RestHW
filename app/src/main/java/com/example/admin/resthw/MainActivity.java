package com.example.admin.resthw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.resthw.model.Example;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/users/octocat";
    public static final String TAG = "Main";
    Example EE;
    ListView listView;
    Button btnGit;
    TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGit = (Button) findViewById(R.id.btnOkHTTPAsynk);
        text1 = (TextView) findViewById(R.id.text1);

    }

    String resultresponse = "";


    public void makingRestCalls(View view) throws IOException {
        final OkHttpClient client = new OkHttpClient();


        final Request request = new Request.Builder().url(BASE_URL).build();

        switch (view.getId()) {
            case R.id.btnOkHTTPAsynk:

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        resultresponse = response.body().string();
                        Gson gson = new Gson();
                        EE = gson.fromJson(resultresponse, Example.class);
                        Log.d(TAG, "Name: " + EE.getName());
                        Log.d(TAG, "Comapny: " + EE.getCompany());
                        Log.d(TAG, "LogID: " + EE.getId());
                        Log.d(TAG, "Type: " + EE.getType());
                        Log.d(TAG, "URL: " + EE.getHtmlUrl());
                        Log.d(TAG, "Avatar " + EE.getAvatarUrl());
                        Log.d(TAG, "FollowersURL " + EE.getFollowersUrl());
                        Log.d(TAG, "Followers " + EE.getFollowers());

                    }
                });


        }

    }

}