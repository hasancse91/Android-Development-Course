package com.hellohasan.seventhclass.ListView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hellohasan.seventhclass.R;
import com.hellohasan.seventhclass.RecyclerView.MovieListActivity;
import com.hellohasan.seventhclass.Utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidTopicListActivity extends AppCompatActivity {

    @BindView(R.id.topicListView)
    ListView listView;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_topic_list);
        ButterKnife.bind(this);

        final String[] topicList = getResources().getStringArray(R.array.topic_list);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topicList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), topicList[i], Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Long: " + topicList[i], Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    public void showMovieList(View view) {
        //check the network state
        if(Util.isInternetAvailable(this))
            startActivity(new Intent(this, MovieListActivity.class));
        else
            Toast.makeText(getApplicationContext(), "No internet available", Toast.LENGTH_SHORT).show();
    }

    /*
    Need double click to close the App
     */
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }

}
