package com.gurkan.socialapp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.gurkan.socialapp.base.BaseActivity;

public class TimelineActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
    }
}
