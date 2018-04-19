package com.materialprogress.app;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.materialprogressdemo.R;
import com.paging.pagingadapter.PagingListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements PagingListener {


    RecyclerView recyclerView;
    SampleAdapter adapter;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SampleAdapter();

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!adapter.isLoading()) return;
                List<String> subData = new ArrayList<>();
                for (int i = 0; i<10; i++) {
                    subData.add(Integer.toString(adapter.getPagedItemCount() + i + 1));
                }
                adapter.addAll(subData);
                adapter.setHasMoreData(adapter.getPagedItemCount() < 100);
            }
        }, 5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                adapter.add(String.valueOf(adapter.getPagedItemCount() + 1));
                break;
            case R.id.remove:
                adapter.remove(0);
                break;
            case R.id.replace:
                adapter.replace(adapter.getItems().indexOf("4"), String.valueOf(adapter.getPagedItemCount()));
                break;
            case R.id.clear:
                adapter.clear();
                break;
            case R.id.insert:
                adapter.insert(1, String.valueOf(adapter.getPagedItemCount() + 1));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void start(View v) {
        adapter.setPagingListener(this);
    }

    public void stop(View v) {
        adapter.setPagingListener(null);
    }
}
