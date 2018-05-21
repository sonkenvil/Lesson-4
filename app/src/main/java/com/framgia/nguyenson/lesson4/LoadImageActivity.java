package com.framgia.nguyenson.lesson4;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LoadImageActivity extends AppCompatActivity{
    private static final int NUMBER_COLUMN = 2;
    LoadImageAsyncTask mLoadImageAsyncTask;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        mRecyclerView = findViewById(R.id.recyclerview_load_image);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,NUMBER_COLUMN);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mLoadImageAsyncTask = new LoadImageAsyncTask(this, new LoadImageAsyncTask.CallBack() {
            @Override
            public void onData(ArrayList<Bitmap> list) {
                AdapterItemGallery adapterItemGallery = new AdapterItemGallery(list);
                mRecyclerView.setAdapter(adapterItemGallery);
            }
        });
        mLoadImageAsyncTask.execute();
    }

}
