package com.amarpreetsinghprojects.twitterapi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String urlappend=null;
    ArrayList<Statuses> statusesArrayList = new ArrayList<>();
    ResultRecyclerViewAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new ResultRecyclerViewAdapter(statusesArrayList, new RecyclerViewItemClickListner() {
            @Override
            public void OnItemClick(Statuses user) {

            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        final CardView l = (CardView) getLayoutInflater().inflate(R.layout.search_dialog,null);

        FloatingActionButton searchButton = (FloatingActionButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Search").setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText serachEdit = (EditText)l.findViewById(R.id.serachETV);
                               urlappend = serachEdit.getText().toString();
                                networkCall();
                                progressDialog = ProgressDialog.show(MainActivity.this,"Searching...","Please wait while searching...",true,false);

                            }
                        })
                        .setView(l)
                        .show();


            }
        });



    }

    public void networkCall(){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://loklak.org/api/search.json?q="+urlappend)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                Gson gson = new Gson();
                final TwitterData twitterData = gson.fromJson(result,TwitterData.class);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        statusesArrayList.addAll(twitterData.getStatuses());
                        progressDialog.hide();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }
}
